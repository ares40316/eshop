package com.example.pojo.entity.user;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "id", length = 20)
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(name = "id-generator", strategy = "com.example.utils.IdGenerateUtil")
    private String id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "sort_order")
    private Integer sortOrder;

    // ✅ 一個分類底下可以有很多商品
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

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

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

    // Getter / Setter ...
}
