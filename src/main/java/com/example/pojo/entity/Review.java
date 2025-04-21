package com.example.pojo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Review（商品評論）實體類別
 * 對應資料表：reviews
 * 用於儲存會員對商品的評價，包括星等、評論內容與時間
 */
@Entity
@Table(name = "reviews")
public class Review {

    /** 主鍵 ID，自動遞增 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 發表評論的使用者（多對一）
     * 表示哪位會員留下這筆評論
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private com.example.pojo.entity.user.User user;

    /**
     * 被評論的商品（多對一）
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 評分（整數，通常為 1～5 顆星）
     */
    private Integer rating;

    /**
     * 評論內容（使用 TEXT 儲存長文字）
     */
    @Column(columnDefinition = "TEXT")
    private String comment;

    /**
     * 發表時間
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // =====================
    // Getter 與 Setter 區塊
    // =====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public com.example.pojo.entity.user.User getUser() {
        return user;
    }

    public void setUser(com.example.pojo.entity.user.User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
