// ProductPageResult.java
package com.example.dto;

import java.util.List;
import com.example.pojo.entity.user.Product;

public class ProductPageResult {
    private List<Product> products;
    private int totalPages;
    private int pageNo;

    public ProductPageResult(List<Product> products, int totalPages, int pageNo) {
        this.products = products;
        this.totalPages = totalPages;
        this.pageNo = pageNo;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPageNo() {
        return pageNo;
    }
}
