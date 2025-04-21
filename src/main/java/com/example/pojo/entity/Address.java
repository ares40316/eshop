package com.example.pojo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 收貨地址實體
 */
@Entity
@Table(name = "addresses")
public class Address {

    /**
     * 地址 ID（主鍵，自動遞增）
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 所屬使用者
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private com.example.pojo.entity.user.User user;

    /**
     * 收件人姓名
     */
    @Column(name = "recipient_name", nullable = false)
    private String recipientName;

    /**
     * 收件人電話
     */
    @Column(nullable = false)
    private String phone;

    /**
     * 地址行 1
     */
    @Column(name = "address_line1", nullable = false)
    private String addressLine1;

    /**
     * 地址行 2（可選）
     */
    @Column(name = "address_line2")
    private String addressLine2;

    /**
     * 縣市
     */
    private String city;

    /**
     * 區
     */
    private String district;

    /**
     * 郵遞區號
     */
    @Column(name = "postal_code")
    private String postalCode;

    /**
     * 是否為預設地址
     */
    @Column(name = "is_default")
    private Boolean isDefault;

    /**
     * 建立時間
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * 最後更新時間
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    // ------------------- getters & setters -------------------

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

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
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
}
