package com.example.pojo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * ApiClient（API 用戶端）實體類別
 * 對應資料表：api_clients
 * 用於記錄可訪問 API 的第三方應用程式基本資訊，如 client_id、client_secret、redirect_uri 等
 */
@Entity
@Table(name = "api_clients")
public class ApiClient {

    /** 主鍵 ID，自動遞增 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 用戶端名稱（例如：MyApp、ThirdPartyService） */
    private String name;

    /** 用戶端識別碼，唯一（例如：client_abc123） */
    @Column(name = "client_id", unique = true)
    private String clientId;

    /** 用戶端密鑰（例如：client_secret_xyz789） */
    private String clientSecret;

    /** 授權後的回調網址（Redirect URI） */
    private String redirectUri;

    /** 建立時間（可用於審核或紀錄） */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // =====================
    // Getter 與 Setter 區塊
    // =====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
