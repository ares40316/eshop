package com.example.service;

import java.util.List;

import com.example.dto.ProductPageResult;
import com.example.pojo.entity.Product;

/**
 * ProductService 介面，定義商品模組的業務邏輯操作。
 */
public interface ProductService {

    /**
     * 查詢所有商品
     * 
     * @return 商品清單
     */
    public List<Product> findAll();

    /**
     * 依關鍵字搜尋商品（名稱、簡介、描述）
     * 
     * @param keyword 關鍵字
     * @return 商品清單
     */
    public List<Product> searchByKeyword(String keyword);

    /**
     * 根據單一分類查詢商品
     * 
     * @param categoryId 分類 ID
     * @return 商品清單
     */
    public List<Product> findByCategory(String categoryId);

    /**
     * 關鍵字 + 單一分類複合查詢
     * 
     * @param keyword 關鍵字
     * @param categoryId 分類 ID
     * @return 商品清單
     */
    public List<Product> searchByKeywordAndCategory(String keyword, String categoryId);

    /**
     * 多分類查詢
     * 
     * @param categoryIds 多個分類 ID
     * @return 商品清單
     */
    public List<Product> findByCategories(List<String> categoryIds);

    /**
     * 多分類 + 關鍵字查詢
     * 
     * @param keyword 關鍵字
     * @param categoryIds 多個分類 ID
     * @return 商品清單
     */
    public List<Product> searchByKeywordAndCategories(String keyword, List<String> categoryIds);

    /**
     * 根據商品 ID 查詢單一商品
     * 
     * @param id 商品 ID
     * @return 商品物件
     */
    public Product findById(Long id);
    void remove(Long id);
    /**
     * 綜合查詢（分類、多分類、關鍵字）
     * 
     * @param keyword 關鍵字
     * @param categoryIds 多個分類 ID
     * @return 商品清單
     */
    
    void create(Product product);
    void modify(Product product);
    
    
    public List<Product> searchWithFilter(String keyword, List<String> categoryIds);

 // ProductService.java
    ProductPageResult searchWithFilterAndPaging(String keyword, List<String> categoryIds,
            int pageNo, int pageSize);

    
    
}
