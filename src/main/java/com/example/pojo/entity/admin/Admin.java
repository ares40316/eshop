package com.example.pojo.entity.admin;

import java.time.LocalDateTime;

/**
 * Admin（後台管理員）實體類別
 * 用來表示後台系統的登入帳號資料，不使用 JPA 註解（如需映射需自行補上）
 */
public class Admin {

    /** 管理員 ID（主鍵） */
    private Long id;

    /** 登入使用者名稱（帳號） */
    private String username;

    /** 登入密碼（建議使用加密儲存） */
    private String password;

    /** 管理員角色，例如：super_admin、editor、auditor 等 */
    private String role;

    /** 建立時間（記錄帳號建立的時間） */
    private LocalDateTime createdAt;

    /** 最後更新時間（可記錄最後修改密碼或變更資料時間） */
    private LocalDateTime updatedAt;

    // ===== Getter 與 Setter 方法 =====

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
