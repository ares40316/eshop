package com.example.pojo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * UserCoupon（使用者擁有的優惠券）實體類別
 * 對應資料表：user_coupons
 * 用於紀錄會員領取的優惠券，包含領取時間與使用時間
 * 使用複合主鍵：user_id + coupon_id
 */
@Entity
@Table(name = "user_coupons")
@IdClass(UserCouponId.class)
public class UserCoupon {

    /** 使用者 ID，複合主鍵之一 */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /** 優惠券 ID，複合主鍵之一 */
    @Id
    @Column(name = "coupon_id")
    private Long couponId;

    /** 領取時間（發放給會員的時間） */
    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;

    /** 使用時間（實際使用優惠券的時間，可為 null） */
    @Column(name = "used_at")
    private LocalDateTime usedAt;

    /**
     * 使用者關聯（多對一）
     * insertable/updatable 設為 false，避免與主鍵衝突
     */
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private com.example.pojo.entity.user.User user;

    /**
     * 優惠券關聯（多對一）
     */
    @ManyToOne
    @JoinColumn(name = "coupon_id", insertable = false, updatable = false)
    private Coupon coupon;

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

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    public LocalDateTime getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(LocalDateTime usedAt) {
        this.usedAt = usedAt;
    }

    public com.example.pojo.entity.user.User getUser() {
        return user;
    }

    public void setUser(com.example.pojo.entity.user.User user) {
        this.user = user;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }
}
