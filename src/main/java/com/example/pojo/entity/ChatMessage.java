package com.example.pojo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * ChatMessage（聊天訊息）實體類別
 * 對應資料表：chat_messages
 * 用於儲存單筆訊息記錄，包含發送者、內容、所屬會話與時間戳
 */
@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    /** 主鍵 ID，自動遞增 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 所屬聊天會話（多對一關聯）
     * 表示此訊息屬於哪個 ChatSession
     */
    @ManyToOne
    @JoinColumn(name = "session_id")
    private ChatSession session;

    /**
     * 發送者類型（USER 或 BOT）
     * 使用 Enum + 字串儲存於資料表中
     */
    @Enumerated(EnumType.STRING)
    private Sender sender;

    /**
     * 訊息內容，使用 TEXT 欄位儲存較長內容
     */
    @Column(columnDefinition = "TEXT")
    private String message;

    /**
     * 建立時間（發送時間）
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * 發送者列舉
     * USER：代表使用者
     * BOT：代表機器人回覆
     */
    public enum Sender {
        USER,
        BOT
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

    public ChatSession getSession() {
        return session;
    }

    public void setSession(ChatSession session) {
        this.session = session;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
