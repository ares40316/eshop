package com.example.pojo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Cart（購物車）實體類別
 * 對應資料表：carts
 * 每筆購物車紀錄對應一位使用者，可包含多個商品項目（CartItem）
 */
@Entity
@Table(name = "carts")
public class Cart {

    /** 主鍵 ID，自動遞增 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 所屬使用者（多對一關聯）
     * 每筆購物車對應一位會員
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private com.example.pojo.entity.user.User user;

    /** 建立時間（建立購物車時記錄） */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /** 最後更新時間（新增或移除商品時更新） */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * 購物車中的商品項目（一對多關聯）
     * 每筆購物車包含多筆 CartItem 紀錄
     */
    @OneToMany(mappedBy = "cart")
    private Set<CartItem> items;

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

    public Set<CartItem> getItems() {
        return items;
    }

    public void setItems(Set<CartItem> items) {
        this.items = items;
    }
}
