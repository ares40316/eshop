package com.example.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;

import com.example.dao.ProductDao;
import com.example.pojo.entity.Category;
import com.example.pojo.entity.Product;
import com.example.pojo.entity.ProductCategory;
import com.example.pojo.entity.ProductImage;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.stream.Collectors;

/**
 * ProductDao 實作類別，負責商品的資料庫操作。
 */
@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 查詢所有商品資料。
	 */
	@Override
	public List<Product> findAll() {
		String hql = "FROM Product";
		return getSession().createQuery(hql, Product.class).list();
	}

	/**
	 * 以關鍵字搜尋商品名稱、簡介或描述。
	 */
	@Override
	public List<Product> searchByKeyword(String keyword) {
		String hql = "FROM Product WHERE name LIKE :kw OR summary LIKE :kw OR description LIKE :kw";
		Query<Product> query = getSession().createQuery(hql, Product.class);
		query.setParameter("kw", "%" + keyword + "%");
		return query.list();
	}

	/**
	 * 查詢指定分類下的所有商品。
	 */
	@Override
	public List<Product> findByCategory(String categoryId) {
		String hql = "FROM Product WHERE category.id = :cid";
		return getSession().createQuery(hql, Product.class).setParameter("cid", categoryId).list();
	}

	/**
	 * 查詢符合關鍵字且屬於指定分類的商品。
	 */
	@Override
	public List<Product> searchByKeywordAndCategory(String keyword, String categoryId) {
		String hql = "FROM Product WHERE category.id = :cid AND "
				+ "(name LIKE :kw OR summary LIKE :kw OR description LIKE :kw)";
		return getSession().createQuery(hql, Product.class).setParameter("cid", categoryId)
				.setParameter("kw", "%" + keyword + "%").list();
	}

	/**
	 * 查詢屬於多個分類的商品。
	 */
	@Override
	public List<Product> findByCategories(List<String> categoryIds) {
		String hql = "FROM Product WHERE category.id IN (:catIds)";
		return getSession().createQuery(hql, Product.class).setParameterList("catIds", categoryIds).list();
	}

	/**
	 * 查詢符合關鍵字且屬於多個分類的商品。
	 */
	@Override
	public List<Product> searchByKeywordAndCategories(String keyword, List<String> categoryIds) {
		String hql = "FROM Product WHERE category.id IN (:catIds) AND "
				+ "(name LIKE :kw OR summary LIKE :kw OR description LIKE :kw)";
		return getSession().createQuery(hql, Product.class).setParameterList("catIds", categoryIds)
				.setParameter("kw", "%" + keyword + "%").list();
	}

	/**
	 * 根據商品 ID 查詢商品。
	 */
	@Override
	public Product findById(Long id) {
		return getSession().get(Product.class, id);
	}

	/**
	 * 根據商品 ID 查詢商品。
	 */
	@Override
	public List<Product> searchPaged(String keyword, List<String> categoryIds, int offset, int limit) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> root = cq.from(Product.class);
		// 改用批次載入策略[^1]
		root.fetch("images", JoinType.LEFT);
		SetJoin<Product, ProductCategory> categoryJoin = root.joinSet("categories", JoinType.LEFT);
		List<Predicate> preds = new ArrayList<>();
		if (StringUtils.isNotBlank(keyword)) {
			preds.add(cb.like(root.get("name"), "%" + keyword + "%"));
		}
		// 分類過濾（只做 join，不做 fetch）
		if (categoryIds != null && !categoryIds.isEmpty()) {
			Set<Long> validIds = categoryIds.stream().filter(Objects::nonNull).map(id -> {
				try {
					return Long.valueOf(id);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("Invalid category ID: " + id, e);
				}
			}).collect(Collectors.toSet());

			SetJoin<Product, ProductCategory> pcJoin = root.joinSet("categories", JoinType.INNER);
			preds.add(pcJoin.get("categoryId").in(validIds));
		}

		cq.select(root).distinct(true).where(preds.toArray(new Predicate[0]));
		return getSession().createQuery(cq).setFirstResult(offset).setMaxResults(limit)
				.setHint("org.hibernate.cacheable", true) // 啟用快取
				.getResultList();
	}

	
	@Override
	// 添加事務註解
	public void loadImagesAndCategories(List<Product> products) {
		if (products == null || products.isEmpty())
			return;
		List<Long> productIds = products.stream().map(Product::getId).collect(Collectors.toList());
		// 使用 JOIN FETCH 合併查詢
		String hql = "SELECT DISTINCT p FROM Product LEFT JOIN FETCH p.images LEFT JOIN FETCH p.categories WHERE p.id IN :ids";

		List<Product> fullProducts = getSession().createQuery(hql, Product.class).setParameterList("ids", productIds)
				.list();
		// 建立快取映射
		Map<Long, Product> fullProductMap = fullProducts.stream()
				.collect(Collectors.toMap(Product::getId, Function.identity()));
		// 關聯資料注入
		products.forEach(product -> {
			Product fullProduct = fullProductMap.get(product.getId());
			if (fullProduct != null) {
				// 安全替換圖片集合
				product.getImages().clear();
				product.getImages().addAll(fullProduct.getImages());

				// 安全替換分類集合
				product.getCategories().clear();
				product.getCategories().addAll(fullProduct.getCategories());
			}
		});
	}

	@Override
	public int countSearchResults(String keyword, List<String> categoryIds) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Product> root = cq.from(Product.class);
		List<Predicate> preds = new ArrayList<>();
		if (StringUtils.isNotBlank(keyword)) {
			preds.add(cb.like(root.get("name"), "%" + keyword + "%"));
		}
		if (categoryIds != null && !categoryIds.isEmpty()) {
			Set<Long> validIds = categoryIds.stream().filter(Objects::nonNull).map(id -> {
				try {
					return Long.valueOf(id);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("Invalid category ID: " + id);
				}
			}).collect(Collectors.toSet());
			// join ProductCategory 關聯，再用 categoryId 過濾
			SetJoin<Product, ProductCategory> pcJoin = root.joinSet("categories", JoinType.INNER);
			preds.add(pcJoin.get("categoryId").in(validIds));
		}
		cq.select(cb.countDistinct(root.get("id")));
		cq.where(preds.toArray(new Predicate[0]));
		return getSession().createQuery(cq).uniqueResult().intValue();
	}

	@Override
	public void save(Product p) {
		getSession().save(p);
	}

	@Override
	public void update(Product p) {
		getSession().update(p);
	}

	@Override
	public void delete(Long id) {
		Product p = findById(id);
		if (p != null) {
			getSession().delete(p);
		}
	}

	@Override
	public List<Product> findProductsWithDetails(List<Long> productIds, boolean includeImages,
			boolean includeCategories) {
		// TODO Auto-generated method stub
		return null;
	}

}
