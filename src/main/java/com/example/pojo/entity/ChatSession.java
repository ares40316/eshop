package com.example.pojo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * ChatSession（聊天會話）實體類別
 * 對應資料表：chat_sessions
 * 用來儲存每一段完整的聊天對話歷程（由一位使用者觸發）
 */
@Entity
@Table(name = "chat_sessions")
public class ChatSession {

    /** 主鍵 ID，自動遞增 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 所屬會員（多對一關聯）
     * 表示該聊天會話是由哪一位使用者發起的
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private com.example.pojo.entity.user.User user;

    /**
     * Session Token（唯一識別碼，可用於 API 驗證或查詢）
     */
    private String sessionToken;

    /**
     * 建立時間（會話開始的時間）
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * 此會話中包含的所有訊息（一對多）
     * ChatMessage 中會有對應的 session 欄位
     */
    @OneToMany(mappedBy = "session")
    private Set<ChatMessage> messages;

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

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(Set<ChatMessage> messages) {
        this.messages = messages;
    }
}
