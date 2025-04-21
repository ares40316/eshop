package com.example.pojo.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * ProductCategoryId（商品與分類關聯的複合主鍵類別）
 * 用於中介實體 ProductCategory 的主鍵組合
 * 主鍵組成：productId + categoryId
 */
public class ProductCategoryId implements Serializable {

    /** 商品 ID，複合主鍵之一 */
    private Long productId;

    /** 分類 ID，複合主鍵之一 */
    private Long categoryId;

    /** 無參數建構子（Hibernate 反射機制必須） */
    public ProductCategoryId() {}

    /** 有參數建構子，方便程式碼初始化 */
    public ProductCategoryId(Long productId, Long categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }

    // =====================
    // equals() 與 hashCode()
    // =====================

    /**
     * 判斷兩個主鍵物件是否相等（Hibernate 用於辨識同一筆資料）
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductCategoryId)) return false;
        ProductCategoryId that = (ProductCategoryId) o;
        return Objects.equals(productId, that.productId) &&
               Objects.equals(categoryId, that.categoryId);
    }

    /**
     * 回傳雜湊值（與 equals 搭配使用）
     */
    @Override
    public int hashCode() {
        return Objects.hash(productId, categoryId);
    }

    // =====================
    // Getter 與 Setter 區塊
    // =====================

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
