package com.example.pojo.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * CartItemId（購物車項目複合主鍵）
 * 對應 cart_items 表中的主鍵欄位組合：cart_id + product_id
 * 必須實作 Serializable，並實作 equals() 與 hashCode()
 */
@Embeddable
public class CartItemId implements Serializable {

    /** 購物車 ID（複合主鍵之一） */
    private Long cartId;

    /** 商品 ID（複合主鍵之一） */
    private Long productId;

    /** 無參數建構子（Hibernate 必須用） */
    public CartItemId() {}

    /** 有參數建構子，方便初始化使用 */
    public CartItemId(Long cartId, Long productId) {
        this.cartId = cartId;
        this.productId = productId;
    }

    /** Getter 與 Setter 區塊 */

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 比較兩筆複合主鍵是否相等（Hibernate 內部依賴此邏輯）
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemId)) return false;
        CartItemId that = (CartItemId) o;
        return Objects.equals(cartId, that.cartId) &&
               Objects.equals(productId, that.productId);
    }

    /**
     * 計算雜湊值（與 equals 一起實作以支援 HashSet / HashMap 運作）
     */
    @Override
    public int hashCode() {
        return Objects.hash(cartId, productId);
    }
}
