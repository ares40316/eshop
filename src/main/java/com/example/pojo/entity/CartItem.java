package com.example.pojo.entity;

import javax.persistence.*;

/**
 * CartItem（購物車項目）實體類別
 * 對應資料表：cart_items
 * 用於紀錄每一筆購物車中的商品，包括商品、數量與加入時間
 * 使用複合主鍵（cart_id + product_id）表示唯一項目
 */
@Entity
@Table(name = "cart_items")
public class CartItem {

    /** 複合主鍵（cart_id + product_id） */
    @EmbeddedId
    private CartItemId id;

    /**
     * 所屬購物車（多對一關聯）
     * 透過 @MapsId 表示關聯同時是主鍵的一部分
     */
    @MapsId("cartId")
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    /**
     * 對應的商品（多對一關聯）
     * 同樣透過 @MapsId 綁定到複合主鍵
     */
    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /** 商品數量（購買數量） */
    private Integer quantity;

    /** 加入購物車的時間 */
    @Column(name = "added_at")
    private java.time.LocalDateTime addedAt;

    // =====================
    // Getter 與 Setter 區塊
    // =====================

    public CartItemId getId() {
        return id;
    }

    public void setId(CartItemId id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public java.time.LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(java.time.LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
}
