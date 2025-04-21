package com.example.pojo.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ProductCategory（商品與分類的關聯表）
 * 對應資料表：product_categories
 * 表示多對多關係的中介表：一個商品可以屬於多個分類；一個分類也可以包含多個商品
 * 使用複合主鍵（product_id + category_id）
 */
@Entity
@Table(name = "product_categories")
@IdClass(ProductCategoryId.class) // 使用複合主鍵
public class ProductCategory implements Serializable {

    /** 商品 ID（複合主鍵之一） */
    @Id
    @Column(name = "product_id")
    private Long productId;

    /** 分類 ID（複合主鍵之一） */
    @Id
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 所屬商品（多對一關聯）
     * insertable = false / updatable = false 是為了避免與主鍵重複維護
     */
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    /**
     * 所屬分類（多對一關聯）
     * 與 categoryId 同步設定，避免欄位衝突
     */
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
