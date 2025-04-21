package com.example.pojo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * UserView（使用者瀏覽紀錄）實體類別
 * 對應資料表：user_views
 * 用來儲存使用者每次瀏覽商品的紀錄，包括使用者、商品與瀏覽時間
 */
@Entity
@Table(name = "user_views")
public class UserView {

    /** 主鍵 ID，自動遞增 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 瀏覽的使用者（多對一關聯）
     * 表示是哪一位會員觀看了商品
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private com.example.pojo.entity.user.User user;

    /**
     * 被瀏覽的商品（多對一關聯）
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 瀏覽的時間（點擊商品的時間點）
     */
    @Column(name = "viewed_at")
    private LocalDateTime viewedAt;

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

    public LocalDateTime getViewedAt() {
        return viewedAt;
    }

    public void setViewedAt(LocalDateTime viewedAt) {
        this.viewedAt = viewedAt;
    }
}
