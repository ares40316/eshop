package com.example.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ProductDao;
import com.example.dto.ProductPageResult;
import com.example.pojo.entity.Product;
import com.example.service.ProductService;

/**
 * ProductService 的實作類別，負責商品模組的業務邏輯處理。
 */
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public List<Product> searchByKeyword(String keyword) {
		return productDao.searchByKeyword(keyword);
	}

	@Override
	public List<Product> findByCategory(String categoryId) {
		return productDao.findByCategory(categoryId);
	}

	@Override
	public List<Product> searchByKeywordAndCategory(String keyword, String categoryId) {
		return productDao.searchByKeywordAndCategory(keyword, categoryId);
	}

	@Override
	public List<Product> findByCategories(List<String> categoryIds) {
		return productDao.findByCategories(categoryIds);
	}

	@Override
	public List<Product> searchByKeywordAndCategories(String keyword, List<String> categoryIds) {
		return productDao.searchByKeywordAndCategories(keyword, categoryIds);
	}

	@Override
	public Product findById(Long id) {
		Product p = productDao.findById(id);
		if (p != null) {
			// 预初始化延迟集合，避免 JSP LazyInitializationException
			Hibernate.initialize(p.getImages());
			Hibernate.initialize(p.getCategories());
		}
		return p;
	}
	

	@Override
	public void remove(Long id) {
		productDao.delete(id);
	}

	@Transactional(readOnly = false)
	@Override
	public void create(Product product) {
		productDao.save(product);
	}

	@Transactional(readOnly = false)
	@Override
	public void modify(Product product) {
		productDao.update(product);
	}

	@Override
	public List<Product> searchWithFilter(String keyword, List<String> categoryIds) {
		keyword = StringUtils.trimToNull(keyword);
		boolean hasKeyword = keyword != null;
		boolean hasCategories = categoryIds != null && !categoryIds.isEmpty();

		if (!hasKeyword && !hasCategories) {
			return productDao.findAll();
		} else if (hasKeyword && !hasCategories) {
			return productDao.searchByKeyword(keyword);
		} else if (!hasKeyword && hasCategories) {
			return productDao.findByCategories(categoryIds);
		} else {
			return productDao.searchByKeywordAndCategories(keyword, categoryIds);
		}
	}

	@Override
	public ProductPageResult searchWithFilterAndPaging(String keyword, List<String> categoryIds, int pageNo,
			int pageSize) {
		keyword = StringUtils.trimToNull(keyword);
		int totalCount = productDao.countSearchResults(keyword, categoryIds);
		int totalPages = (int) Math.ceil((double) totalCount / pageSize);
		int offset = (pageNo - 1) * pageSize;

		List<Product> pageData = productDao.searchPaged(keyword, categoryIds, offset, pageSize);
		
		return new ProductPageResult(pageData, totalPages, pageNo);
	}

}
