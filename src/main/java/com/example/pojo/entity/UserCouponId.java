package com.example.pojo.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * UserCouponId（使用者優惠券關聯主鍵類別）
 * 對應實體類：UserCoupon
 * 組成複合主鍵欄位：userId + couponId
 * 必須實作 Serializable 並實作 equals() 和 hashCode()
 */
public class UserCouponId implements Serializable {

    /** 使用者 ID（複合主鍵之一） */
    private Long userId;

    /** 優惠券 ID（複合主鍵之一） */
    private Long couponId;

    /** 無參建構子（Hibernate 反射需要） */
    public UserCouponId() {}

    /** 有參建構子（方便初始化） */
    public UserCouponId(Long userId, Long couponId) {
        this.userId = userId;
        this.couponId = couponId;
    }

    /**
     * 判斷主鍵是否相等（Hibernate 依此辨識是否為同一筆資料）
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCouponId)) return false;
        UserCouponId that = (UserCouponId) o;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(couponId, that.couponId);
    }

    /**
     * 主鍵雜湊值，用於集合操作（如 Map、Set）
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId, couponId);
    }

    // =====================
    // Getter 與 Setter 區塊
    // =====================

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }
}
