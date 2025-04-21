package com.example.pojo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Recommendation（推薦紀錄）實體類別
 * 對應資料表：recommendations
 * 用於儲存系統根據演算法產生的推薦結果，例如「使用者 A 推薦商品 B，分數為 4.8」
 */
@Entity
@Table(name = "recommendations")
public class Recommendation {

    /** 主鍵 ID，自動遞增 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 所屬使用者（多對一關聯）
     * 表示此推薦是針對哪位會員所產生
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private com.example.pojo.entity.user.User user;

    /**
     * 被推薦的商品（多對一關聯）
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 推薦分數（通常由演算法計算，值越高代表越適合推薦）
     * 可使用 BigDecimal 儲存浮點分數（例：4.85）
     */
    private BigDecimal score;

    /** 建立時間（系統產生推薦紀錄的時間） */
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

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
