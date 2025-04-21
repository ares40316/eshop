package com.example.pojo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * OrderItem（訂單項目）實體類別
 * 對應資料表：order_items
 * 表示一筆訂單中購買的某一個商品，使用複合主鍵：order_id + product_id
 */
@Entity
@Table(name = "order_items")
@IdClass(OrderItemId.class) // 定義複合主鍵類別
public class OrderItem implements Serializable {

    /** 所屬訂單 ID（複合主鍵之一） */
    @Id
    @Column(name = "order_id")
    private Long orderId;

    /** 商品 ID（複合主鍵之一） */
    @Id
    @Column(name = "product_id")
    private Long productId;

    /**
     * 所屬訂單（多對一）
     * 使用 insertable = false, updatable = false 避免重複欄位設定
     */
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    /**
     * 所屬商品（多對一）
     * 使用 insertable = false, updatable = false 避免衝突
     */
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    /** 購買數量 */
    private Integer quantity;

    /** 當下購買單價（可保存當時價格，避免商品價格變動） */
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    // =====================
    // Getter 與 Setter 區塊
    // =====================

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
