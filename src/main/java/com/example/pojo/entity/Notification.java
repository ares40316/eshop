package com.example.pojo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Notification（通知）實體類別
 * 對應資料表：notifications
 * 用來記錄系統傳送給使用者或管理者的通知訊息，例如活動公告、訂單提醒、系統警告等
 */
@Entity
@Table(name = "notifications")
public class Notification {

    /** 主鍵 ID，自動遞增 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 接收者身份類型（ENUM）
     * USER：會員
     * ADMIN：管理員
     */
    @Enumerated(EnumType.STRING)
    private RecipientType userType;

    /**
     * 接收者的 ID（對應 User 或 Admin 的 ID）
     * 根據 userType 決定是哪張表的使用者
     */
    private Long userId;

    /** 通知標題，例如「訂單已出貨」、「優惠券即將到期」 */
    private String title;

    /** 通知內容，支援長內容，使用 TEXT 儲存 */
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * 是否已讀
     * true：使用者已查看通知；false：尚未讀取
     */
    @Column(name = "is_read")
    private Boolean isRead;

    /** 通知建立時間 */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * 接收者類型列舉
     */
    public enum RecipientType {
        USER,
        ADMIN
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

    public RecipientType getUserType() {
        return userType;
    }

    public void setUserType(RecipientType userType) {
        this.userType = userType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
