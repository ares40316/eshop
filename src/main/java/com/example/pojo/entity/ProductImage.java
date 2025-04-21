package com.example.pojo.entity;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(name = "image_url", length = 512, nullable = false)
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    @Column(name = "image_type", columnDefinition = "ENUM('MAIN','DETAIL','THUMBNAIL')")
    private ImageType imageType = ImageType.MAIN;
    @Column(name = "sort_order")
    private Integer sortOrder = 0;
    // 枚举类型定义
    public enum ImageType { MAIN, DETAIL, THUMBNAIL }
    // 省略 getter/setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Product getProduct() {
        return product;
    }
    // 重要：保持雙向關聯一致性（可選，根據業務需求）
    public void setProduct(Product product) {
        if (this.product != null) {
            this.product.getImages().remove(this);
        }
        this.product = product;
        if (product != null && !product.getImages().contains(this)) {
            product.getImages().add(this);
        }
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Image URL cannot be empty");
        }
        this.imageUrl = imageUrl;
    }
    public ImageType getImageType() {
        return imageType;
    }
    public void setImageType(ImageType imageType) {
        if (imageType == null) {
            throw new IllegalArgumentException("ImageType must be specified");
        }
        this.imageType = imageType;
    }
    public Integer getSortOrder() {
        return sortOrder;
    }
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder != null ? sortOrder : 0; // 默認值保護
    }
}