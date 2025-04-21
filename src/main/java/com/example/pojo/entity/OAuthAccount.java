package com.example.pojo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * OAuthAccount（第三方登入帳號）實體類別
 * 對應資料表：oauth_accounts
 * 用來儲存使用者的第三方登入資訊（例如 Google、Facebook 登入）
 */
@Entity
@Table(name = "oauth_accounts")
public class OAuthAccount {

    /** 主鍵 ID，自動遞增 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 所屬使用者（多對一關聯）
     * 表示此 OAuth 帳號屬於哪位本地系統會員
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private com.example.pojo.entity.user.User user;

    /**
     * 第三方登入平台（枚舉）
     * 如：GOOGLE、FACEBOOK
     */
    @Enumerated(EnumType.STRING)
    private Provider provider;

    /**
     * 第三方平台的使用者 ID（例如 Google 帳號的 sub 值）
     */
    private String providerUserId;

    /**
     * OAuth Access Token，可用來請求第三方 API
     */
    private String accessToken;

    /**
     * OAuth Refresh Token，可用來刷新 Access Token
     */
    private String refreshToken;

    /**
     * Access Token 過期時間
     */
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    /**
     * 建立時間（此綁定帳號的建立時間）
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * OAuth 平台枚舉定義
     * GOOGLE：Google 帳號
     * FACEBOOK：Facebook 帳號
     */
    public enum Provider {
        GOOGLE,
        FACEBOOK
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

    public com.example.pojo.entity.user.User getUser() {
        return user;
    }

    public void setUser(com.example.pojo.entity.user.User user) {
        this.user = user;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
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
