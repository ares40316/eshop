package com.example.pojo.entity;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "product_images")
/**
 * 商品圖片實體對象，用於映射資料庫中的 product_images 表
 */
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 主鍵：自動增長的唯一 ID
     */
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    /**
     * 關聯的產品對象，多對一關係，對應 product 表中的 id
     */
    private Product product;

    @Column(name = "image_url", length = 512, nullable = false)
    /**
     * 圖片 URL，長度上限為 512，必填
     */
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "image_type", columnDefinition = "ENUM('MAIN','DETAIL','THUMBNAIL')")
    /**
     * 圖片類型，使用枚舉表示（主圖、詳細圖、縮略圖），預設為 MAIN
     */
    private ImageType imageType = ImageType.MAIN;

    @Column(name = "sort_order", nullable=false)
    /**
     * 排序順序，數值越小顯示越靠前，預設為 0
     */
    private Integer sortOrder = 0;

    /**
     * 枚舉類型：定義圖片的不同用途或展示方式
     */
    public enum ImageType {
        /** 主圖 */
        MAIN,
        /** 詳細圖 */
        DETAIL,
        /** 縮略圖 */
        THUMBNAIL
    }

    // ========== Getter 和 Setter 方法 ==========

    /**
     * 獲取主鍵 ID
     * @return 商品圖片的唯一 ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 設置主鍵 ID
     * @param id 要設置的唯一 ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 獲取關聯的產品對象
     * @return 關聯的 Product 實體
     */
    public Product getProduct() {
        return product;
    }

    /**
     * 設置關聯的產品對象，並保持雙向關聯一致性
     * @param product 要關聯的 Product 實體
     */
    public void setProduct(Product product) {
        if (this.product != null) {
            this.product.getImages().remove(this);
        }
        this.product = product;
        if (product != null && !product.getImages().contains(this)) {
            product.getImages().add(this);
        }
    }

    /**
     * 獲取圖片的 URL
     * @return 圖片的網址字串
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 設置圖片的 URL，不可為空或僅包含空白
     * @param imageUrl 要設置的圖片網址
     * @throws IllegalArgumentException 當 imageUrl 為 null 或空字串時
     */
    public void setImageUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Image URL cannot be empty");
        }
        this.imageUrl = imageUrl;
    }

    /**
     * 獲取圖片類型
     * @return ImageType 枚舉值
     */
    public ImageType getImageType() {
        return imageType;
    }

    /**
     * 設置圖片類型，不可為 null
     * @param imageType 要設置的 ImageType
     * @throws IllegalArgumentException 當 imageType 為 null 時
     */
    public void setImageType(ImageType imageType) {
        if (imageType == null) {
            throw new IllegalArgumentException("ImageType must be specified");
        }
        this.imageType = imageType;
    }

    /**
     * 獲取排序順序
     * @return 排序順序整數，預設為 0
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * 設置排序順序，如傳入 null 則設置為預設值 0
     * @param sortOrder 要設置的排序順序
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = (sortOrder != null ? sortOrder : 0);
    }
}
