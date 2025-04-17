package com.example.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.dto.ProductPageResult;
import com.example.pojo.entity.user.Product;
import com.example.pojo.entity.user.Category;
import com.example.service.ProductService;
import com.example.service.CategoryService;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.StringUtils;

@Controller
public class ProductAction extends ActionSupport implements SessionAware {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;

    private String keyword;
    private List<String> categoryIds = new ArrayList<>();
    private String id;
    private String productId;
    private int pageNo = 1;
    private final int pageSize = 10;
    private int totalPages;
    private List<Product> productList;
    private Product product;
    private List<Category> categoryList;
    private Map<String, Object> session;

   
    public void setCategoryIds(String[] categoryIdsArray) {
        this.categoryIds = Arrays.asList(categoryIdsArray);
    }
    
    @Override
    public String execute() {
        // 強制載入分類
        this.categoryList = categoryService.findAll();
        
        // 參數處理
        keyword = StringUtils.trimToNull(keyword);
        if (categoryIds == null) categoryIds = new ArrayList<>();
        
        // 分頁保護
        if (pageNo < 1) pageNo = 1;

        // 調試日誌
        System.out.println("搜索參數 - keyword: " + keyword + ", categoryIds: " + categoryIds);

        try {
            ProductPageResult result = productService.searchWithFilterAndPaging(
                keyword, 
                categoryIds.isEmpty() ? null : categoryIds,
                pageNo, 
                pageSize
            );
            
            this.productList = result.getProducts();
            this.totalPages = result.getTotalPages();
            
            System.out.println("查詢成功，獲取商品數: " + productList.size());
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("查詢錯誤: " + e.getMessage());
            return ERROR;
        }
    }

    public String detail() {
        product = productService.findById(id);
        return (product != null) ? SUCCESS : ERROR;
    }

    public String addToCart() {
        Product selected = productService.findById(productId);
        if (selected != null) {
            @SuppressWarnings("unchecked")
            Map<String, Integer> cart = (Map<String, Integer>) session.get("cart");
            if (cart == null) {
                cart = new java.util.HashMap<>();
            }
            cart.put(productId, cart.getOrDefault(productId, 0) + 1);
            session.put("cart", cart);
        }
        return SUCCESS;
    }

    
    
    // Getter/Setter 方法保持不變
    public List<Product> getProductList() { return productList; }
    public Product getProduct() { return product; }
    public List<Category> getCategoryList() { return categoryList; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
    public void setCategoryIds(List<String> categoryIds) { this.categoryIds = categoryIds; }
    public void setId(String id) { this.id = id; }
    public void setProductId(String productId) { this.productId = productId; }
    public void setPageNo(int pageNo) { this.pageNo = pageNo; }
    public int getPageNo() { return pageNo; }
    public int getTotalPages() { return totalPages; }
    @Override 
    public void setSession(Map<String, Object> session) { this.session = session; }
}