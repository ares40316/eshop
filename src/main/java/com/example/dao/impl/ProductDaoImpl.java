package com.example.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

import com.example.dao.ProductDao;
import com.example.pojo.entity.user.Product;

import javax.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * ProductDao 實作類別，負責商品的資料庫操作。
 */
@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

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
        return getSession().createQuery(hql, Product.class)
                           .setParameter("cid", categoryId)
                           .list();
    }

    /**
     * 查詢符合關鍵字且屬於指定分類的商品。
     */
    @Override
    public List<Product> searchByKeywordAndCategory(String keyword, String categoryId) {
        String hql = "FROM Product WHERE category.id = :cid AND " +
                     "(name LIKE :kw OR summary LIKE :kw OR description LIKE :kw)";
        return getSession().createQuery(hql, Product.class)
                           .setParameter("cid", categoryId)
                           .setParameter("kw", "%" + keyword + "%")
                           .list();
    }

    /**
     * 查詢屬於多個分類的商品。
     */
    @Override
    public List<Product> findByCategories(List<String> categoryIds) {
        String hql = "FROM Product WHERE category.id IN (:catIds)";
        return getSession().createQuery(hql, Product.class)
                           .setParameterList("catIds", categoryIds)
                           .list();
    }

    /**
     * 查詢符合關鍵字且屬於多個分類的商品。
     */
    @Override
    public List<Product> searchByKeywordAndCategories(String keyword, List<String> categoryIds) {
        String hql = "FROM Product WHERE category.id IN (:catIds) AND " +
                     "(name LIKE :kw OR summary LIKE :kw OR description LIKE :kw)";
        return getSession().createQuery(hql, Product.class)
                           .setParameterList("catIds", categoryIds)
                           .setParameter("kw", "%" + keyword + "%")
                           .list();
    }

    /**
     * 根據商品 ID 查詢商品。
     */
    @Override
    public Product findById(String id) {
        return getSession().get(Product.class, id);
    }

    /**
     * 取得 Hibernate 的當前 Session。
     */
    private Session getSession() {
        return sessionFactory.getCurrentSession();   
    }
    
    @Override
    public List<Product> searchPaged(String keyword, List<String> categoryIds, int offset, int limit) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        
        if (StringUtils.isNotBlank(keyword)) {
            predicates.add(cb.or(
                cb.like(root.get("name"), "%" + keyword + "%"),
                cb.like(root.get("description"), "%" + keyword + "%")
            ));
        }
        
        if (categoryIds != null && !categoryIds.isEmpty()) {
            predicates.add(root.get("category").get("id").in(categoryIds));
        }
        
        query.where(predicates.toArray(new Predicate[0]));
        
        return getSession().createQuery(query)
                          .setFirstResult(offset)
                          .setMaxResults(limit)
                          .getResultList();
    }



    @Override
    public int countSearchResults(String keyword, List<String> categoryIds) {
        StringBuilder hql = new StringBuilder("select count(*) from Product where 1=1");
        if (keyword != null && !keyword.isBlank()) {
            hql.append(" and name like :keyword");
        }
        if (categoryIds != null && !categoryIds.isEmpty()) {
            hql.append(" and category.id in (:categoryIds)");
        }
        // 除錯輸出最終拼接的 HQL 查詢
        System.out.println("HQL (countSearchResults): " + hql.toString());
        
        Query<Long> query = getSession().createQuery(hql.toString(), Long.class);
        if (keyword != null && !keyword.isBlank()) {
            query.setParameter("keyword", "%" + keyword.trim() + "%");
        }
        if (categoryIds != null && !categoryIds.isEmpty()) {
            query.setParameterList("categoryIds", categoryIds);
        }
        
        Long count = query.uniqueResult();
        return count != null ? count.intValue() : 0;
    }




}
