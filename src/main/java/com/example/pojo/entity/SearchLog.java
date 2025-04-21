package com.example.pojo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * SearchLog（搜尋紀錄）實體類別
 * 對應資料表：search_logs
 * 用來儲存使用者的搜尋行為，包括關鍵字、篩選條件（JSON 格式）、以及搜尋時間
 */
@Entity
@Table(name = "search_logs")
public class SearchLog {

    /** 主鍵 ID，自動遞增 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 搜尋的使用者（多對一關聯）
     * 可為 null 表示訪客使用
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private com.example.pojo.entity.user.User user;

    /**
     * 搜尋關鍵字（如：手機、筆電）
     */
    private String query;

    /**
     * 使用的篩選條件，使用 JSON 儲存（例如分類、價格範圍、品牌等）
     * 可儲存為字串，如：{"category":"手機","price":{"min":3000,"max":10000}}
     */
    @Column(columnDefinition = "JSON")
    private String filters;

    /**
     * 搜尋時間（紀錄用戶行為時間點）
     */
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

    public com.example.pojo.entity.user.User getUser() {
        return user;
    }

    public void setUser(com.example.pojo.entity.user.User user) {
        this.user = user;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
