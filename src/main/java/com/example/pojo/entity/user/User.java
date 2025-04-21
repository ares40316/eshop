package com.example.pojo.entity.user;

import java.time.LocalDateTime;

/**
 * User（使用者 / 會員）實體類別
 * 表示平台會員的基本資料，包含帳號、密碼、Email、手機與建立/更新時間
 * 本類別未使用 JPA 註解，適用於純 Java 類別、DTO、或非 ORM 映射用途
 */
public class User {

    /** 使用者 ID（唯一識別碼） */
    private Long id;

    /** 使用者帳號名稱，需唯一（例如用作登入） */
    private String username;

    /** 使用者密碼（應加密儲存） */
    private String password;

    /** 電子郵件（可用於找回密碼、帳號驗證等） */
    private String email;

    /** 聯絡電話（選填） */
    private String phone;

    /** 帳號建立時間（註冊時產生） */
    private LocalDateTime createdAt;

    /** 最後更新時間（修改密碼、資料時更新） */
    private LocalDateTime updatedAt;

    // =====================
    // Getter & Setter 方法
    // =====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
