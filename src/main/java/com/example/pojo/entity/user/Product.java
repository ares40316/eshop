package com.example.pojo.entity.user;

import javax.persistence.*;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.example.pojo.entity.user.Category;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "product")
public class Product {

	// 商品編號（主鍵，使用自訂產生器）
	@Id
	@Column(name = "id", length = 20)
	@GeneratedValue(generator = "id-generator")
	@GenericGenerator(name = "id-generator", strategy = "com.example.utils.IdGenerateUtil")
	private String id;

	// 商品名稱
	@Column(name = "name", length = 100)
	private String name;

	// 商品售價（元）
	@Column(name = "price")
	private Integer price;

	// 商品簡介
	@Column(name = "summary")
	private String summary;

	// 商品詳細說明
	@Column(name = "description")
	private String description;

	// 商品主圖路徑
	@Column(name = "image_path", length = 255)
	private String imagePath;

	// 分類
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	// 是否上架
	@Column(name = "is_available")
	private Boolean isAvailable;

	// 上架時間
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	// 商品庫存數量
	@Column(name = "stock")
	private Integer stock;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Category getCategory() { return category; }
	public void setCategory(Category category) { this.category = category; }

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	

	
	// Getter / Setter 略，可使用 Lombok 或 IDE 自動生成
}
