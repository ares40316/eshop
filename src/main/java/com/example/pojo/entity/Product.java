package com.example.pojo.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Product（商品）實體類別
 * 對應資料表：products
 * 用於儲存商城商品的基本資訊，例如名稱、價格、庫存、上下架狀態與分類等
 */
@Entity
@Table(name = "products")
public class Product {

	// 商品編號（主鍵，使用自訂產生器）
		@Id
		@Column(name = "id", length = 20)
		@GeneratedValue(generator = "id-generator")
		@GenericGenerator(name = "id-generator", strategy = "com.example.utils.IdGenerateUtil")
    private Long id;

    /** 商品名稱，必填 */
    @Column(nullable = false)
    private String name;

    /** 商品描述，支援長文字內容，儲存於 TEXT 欄位 */
    @Column(columnDefinition = "TEXT")
    private String description;

    /** 商品售價，使用 BigDecimal 可精確表示金額，必填 */
    @Column(nullable = false)
    private BigDecimal price;

    /** 商品庫存數量，必填 */
    @Column(nullable = false)
    private Integer stock;

    /** 是否上架（true：上架，false：下架） */
    @Column(nullable = false)
    private Boolean isAvailable;

    /** 建立時間（商品建立時產生） */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /** 最後更新時間（修改商品資訊時更新） */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * 商品所屬分類（一對多關聯到 ProductCategory 中介表）
     * 一個商品可對應多個分類（多對多結構）
     */
    @OneToMany(mappedBy = "product")
    private Set<ProductCategory> categories;
    
    // 一对多关联（重点注解配置）
    @OneToMany(
        mappedBy = "product", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true, // 自动删除无关联的子项
        fetch = FetchType.LAZY
    )
    @OrderBy("sortOrder ASC") // 按排序字段升序排列
    private List<ProductImage> images = new ArrayList<>();
    
    // =====================
    // Getter 與 Setter 區塊
    // =====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
    	 if (name == null || name.trim().isEmpty()) {
    	        throw new IllegalArgumentException("Product name cannot be empty");
    	    }
    	    this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<ProductCategory> categories) {
        this.categories = categories;
    }
    
    public List<ProductImage> getImages() {
        return Collections.unmodifiableList(images); // 返回不可修改的副本
    }
    // 核心方法：通過業務方法管理關聯，不建議直接操作集合
    public void addImage(ProductImage image) {
        if (image == null) return;
        image.setProduct(this); // 觸發 ProductImage 中的雙向關聯邏輯
        if (!images.contains(image)) {
            images.add(image);
        }
    }
    // 安全移除方法
    public void removeImage(ProductImage image) {
        if (image != null && images.remove(image)) {
            image.setProduct(null); // 觸發 ProductImage 的關聯清理
        }
    }
    // 防禦性設置集合（僅用於初始化）
    protected void setImages(List<ProductImage> images) {
        this.images.clear();
        if (images != null) {
            images.forEach(this::addImage); // 重用 addImage 邏輯
        }
    }
}
