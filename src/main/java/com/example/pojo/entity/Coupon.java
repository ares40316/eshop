package com.example.pojo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Coupon（優惠券）實體類別
 * 對應資料表：coupons
 * 用於記錄促銷活動的折扣碼、折扣類型、折扣數值、使用上限與到期時間
 */
@Entity
@Table(name = "coupons")
public class Coupon {

    /** 主鍵 ID，自動遞增 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 優惠券代碼，必須唯一（如：WELCOME100） */
    @Column(nullable = false, unique = true)
    private String code;

    /**
     * 折扣類型（ENUM），金額或百分比
     * AMOUNT：固定金額折扣
     * PERCENT：百分比折扣
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", nullable = false)
    private DiscountType discountType;

    /**
     * 折扣數值：
     * - 若為 AMOUNT 表示折扣金額
     * - 若為 PERCENT 表示折扣百分比（如 10 = 10%）
     */
    @Column(name = "discount_value", nullable = false)
    private BigDecimal discountValue;

    /**
     * 使用上限（可為 null，表示不限次數）
     * 例如 100 表示最多可被使用 100 次
     */
    @Column(name = "usage_limit")
    private Integer usageLimit;

    /** 到期時間（過期後無法使用） */
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    /** 優惠券建立時間 */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * 折扣類型列舉
     * AMOUNT：金額折扣（ex：折 100 元）
     * PERCENT：百分比折扣（ex：打 9 折 = 10% off）
     */
    public enum DiscountType {
        AMOUNT,
        PERCENT
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public Integer getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(Integer usageLimit) {
        this.usageLimit = usageLimit;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
