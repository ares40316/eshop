package com.example.pojo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 存放外部應用存取令牌的實體
 */
@Entity
@Table(name = "access_tokens")
public class AccessToken {

    /**
     * 存取令牌字串（主鍵）
     */
    @Id
    private String token;

    /**
     * 所屬客戶端
     */
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ApiClient client;

    /**
     * 授權使用者
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private com.example.pojo.entity.user.User user;

    /**
     * 到期時間
     */
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    /**
     * 紀錄建立時間
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;


    // ------------------- getters & setters -------------------

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ApiClient getClient() {
        return client;
    }

    public void setClient(ApiClient client) {
        this.client = client;
    }

    public com.example.pojo.entity.user.User getUser() {
        return user;
    }

    public void setUser(com.example.pojo.entity.user.User user) {
        this.user = user;
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
