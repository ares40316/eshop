package com.example.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.example.pojo.entity.Product;

/**
 * ProductDao 介面，定義商品相關的資料存取方法。
 */
public interface ProductDao {

	void save(Product product);
	void update(Product product);
	void delete(Long id);
    /**
     * 查詢所有商品
     * 
     * @return 商品列表
     */
    public List<Product> findAll();

    /**
     * 根據關鍵字模糊搜尋商品（比對名稱、簡介、描述）
     * 
     * @param keyword 關鍵字
     * @return 符合條件的商品清單
     */
    public List<Product> searchByKeyword(String keyword);

    /**
     * 根據單一分類 ID 查詢商品
     * 
     * @param categoryId 分類編號
     * @return 商品清單
     */
    public List<Product> findByCategory(String categoryId);

    /**
     * 根據關鍵字與單一分類查詢商品
     * 
     * @param keyword 關鍵字
     * @param categoryId 分類 ID
     * @return 商品清單
     */
    public List<Product> searchByKeywordAndCategory(String keyword, String categoryId);

    /**
     * 根據多個分類 ID 查詢商品
     * 
     * @param categoryIds 多個分類 ID
     * @return 商品清單
     */
    public List<Product> findByCategories(List<String> categoryIds);

    /**
     * 根據關鍵字與多個分類查詢商品
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
     * @return 商品物件，若找不到則回傳 null
     */
    public Product findById(Long id);
    /**
     商品頁面和排序
     */
    List<Product> searchPaged(String keyword, List<String> categoryIds, int offset, int limit);
    int countSearchResults(String keyword, List<String> categoryIds);
 // 在 ProductDao 新增方法定義
    @Transactional
    List<Product> findProductsWithDetails(List<Long> productIds, boolean includeImages, boolean includeCategories);
    void loadImagesAndCategories(List<Product> products);
	

}
