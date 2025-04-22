package com.example.action;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.dto.ProductPageResult;
import com.example.pojo.entity.Product;
import com.example.pojo.entity.Category;
import com.example.service.ProductService;
import com.example.service.CategoryService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller
public class ProductAction extends ActionSupport implements SessionAware {

    private static final Logger logger = LoggerFactory.getLogger(ProductAction.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    private String keyword;
    private List<String> categoryIds;
    private int pageNo = 1;
    private int totalPages;
    private List<Product> productList;
    private List<Category> categoryList;
    private Long id;
    private Long productId;
    private Map<String, Object> session;

    @Override
    public String execute() {
        categoryList = categoryService.findAll();
        keyword = (keyword != null && !keyword.trim().isEmpty()) ? keyword.trim() : null;
        if (categoryIds == null) categoryIds = java.util.Collections.emptyList();
        if (pageNo < 1) pageNo = 1;

        logger.debug("Search params - keyword: {}, categories: {}, pageNo: {}", keyword, categoryIds, pageNo);

        try {
            ProductPageResult result = productService
                .searchWithFilterAndPaging(keyword, categoryIds, pageNo, 10);
            productList = result.getProducts();
            totalPages = result.getTotalPages();
            pageNo      = result.getPageNo(); 
            
         // === DEBUG: 印出每個商品的所有 imageUrl ===
            for (Product p : productList) {
                List<String> urls = p.getImages()
                                     .stream()
                                     .map(img -> img.getImageUrl())
                                     .collect(Collectors.toList());
                logger.debug("Product[{}] image URLs = {}", p.getId(), urls);
            }
            
            return SUCCESS;
        } catch (Exception e) {
            logger.error("Error fetching products", e);
            addActionError("查詢錯誤: " + e.getMessage());
            return ERROR;
        }
    }

    public String detail() {
        try {
            productList = null; // clear list
            categoryList = categoryService.findAll();
            // load single product
            Product p = productService.findById(id);
            if (p == null) return ERROR;
            session.put("detailProduct", p);
            return SUCCESS;
        } catch (Exception e) {
            logger.error("Error loading product detail", e);
            return ERROR;
        }
    }

    public String addToCart() {
        Product p = productService.findById(productId);
        if (p != null) {
            @SuppressWarnings("unchecked")
            Map<Long, Integer> cart = (Map<Long, Integer>) session.get("cart");
            if (cart == null) cart = new java.util.HashMap<>();
            cart.put(productId, cart.getOrDefault(productId, 0) + 1);
            session.put("cart", cart);
        }
        return SUCCESS;
    }

    // --- Getters/Setters ---
    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }

    public List<String> getCategoryIds() { return categoryIds; }
    public void setCategoryIds(List<String> categoryIds) { this.categoryIds = categoryIds; }

    public int getPageNo() { return pageNo; }
    public void setPageNo(int pageNo) { this.pageNo = pageNo; }

    public int getTotalPages() { return totalPages; }
    public List<Product> getProductList() { return productList; }
    public List<Category> getCategoryList() { return categoryList; }

    public void setId(Long id) { this.id = id; }
    public void setProductId(Long productId) { this.productId = productId; }

    @Override
    public void setSession(Map<String, Object> session) { this.session = session; }
}