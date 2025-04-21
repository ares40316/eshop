package com.example.pojo.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * OrderItemId（訂單項目複合主鍵類別）
 * 用於訂單明細（OrderItem）的複合主鍵組成
 * 主鍵結構：order_id + product_id
 * 必須實作 Serializable 並覆寫 equals() 與 hashCode()
 */
public class OrderItemId implements Serializable {

    /** 訂單 ID（複合主鍵之一） */
    private Long orderId;

    /** 商品 ID（複合主鍵之一） */
    private Long productId;

    /** 無參建構子（Hibernate 反射機制需要） */
    public OrderItemId() {}

    /** 有參建構子，方便初始化主鍵值 */
    public OrderItemId(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    // =====================
    // equals() 與 hashCode()
    // =====================

    /**
     * 判斷兩個複合主鍵是否相等
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemId)) return false;
        OrderItemId that = (OrderItemId) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(productId, that.productId);
    }

    /**
     * 複合主鍵的雜湊值，需與 equals 搭配使用
     */
    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }

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
}
