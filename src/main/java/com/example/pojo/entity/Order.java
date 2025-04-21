package com.example.pojo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Order（訂單）實體類別
 * 對應資料表：orders
 * 用來記錄會員的購買行為，包括收件地址、商品項目、使用的優惠券與付款狀態
 */
@Entity
@Table(name = "orders")
public class Order {

    /** 訂單 ID，主鍵，自動遞增 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 下單使用者（多對一）
     * 一位會員可擁有多筆訂單
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private com.example.pojo.entity.user.User user;

    /**
     * 收件地址（多對一）
     * 每筆訂單對應一個地址（地址快照）
     */
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    /**
     * 訂單總金額（不含稅或含稅視情況）
     */
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 訂單狀態（列舉）
     * PENDING：待付款
     * PAID：已付款
     * SHIPPED：已出貨
     * CANCELLED：已取消
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    /**
     * 使用的優惠券（可為 null）
     */
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    /** 訂單建立時間 */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /** 最後更新時間（如付款、取消、出貨） */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * 訂單項目（商品明細清單）
     * 一對多關聯：每筆訂單包含多個商品項目
     */
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items;

    /**
     * 訂單狀態列舉定義
     */
    public enum OrderStatus {
        PENDING,
        PAID,
        SHIPPED,
        CANCELLED
    }

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
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

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }
}
