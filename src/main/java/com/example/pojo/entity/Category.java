package com.example.pojo.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Category（商品分類）實體類別
 * 對應資料表：categories
 * 可支援父子分類（樹狀結構）及分類時間資訊
 */
@Entity
@Table(name = "categories")
public class Category {

    /** 主鍵 ID，自動遞增 */
	@Id
    @Column(name = "id", length = 20)
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(name = "id-generator", strategy = "com.example.utils.IdGenerateUtil")
    private Long id;

    /** 分類名稱，必填，例如：電子產品、家電、衣服 */
    @Column(nullable = false)
    private String name;

    /**
     * 上層分類（多對一關聯）
     * 可為 null，代表為第一層（根分類）
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    /**
     * 子分類清單（一對多關聯）
     * 使用 mappedBy 表示由 child 的 parent 欄位維護
     */
    @OneToMany(mappedBy = "parent")
    private Set<Category> children;

    /** 建立時間，新增時自動填入 */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /** 更新時間，每次修改分類時更新 */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public void setChildren(Set<Category> children) {
        this.children = children;
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
