package com.example.action;
import java.io.File;
import java.util.List;

import com.example.pojo.entity.Product;
import com.example.pojo.entity.ProductImage;
import com.example.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import org.apache.commons.io.FileUtils;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * AdminProductAction：後台商品管理
 */
@Controller
public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {
    private static final long serialVersionUID = 1L;

    @Autowired
    private ProductService productService;
    private ServletContext servletContext;
    // ===== Action 層屬性 =====
    private Long id;                         // 接收商品 ID
    private Product product = new Product(); // ModelDriven

    // 多檔案上傳欄位
    private List<File> images;
    private List<String> imagesContentType;
    private List<String> imagesFileName;

    // 查回的商品列表
    private List<Product> productList;

    // ===== 1. 商品列表 =====
    public String list() {
        productList = productService.findAll();
        return "list";   // struts-config 返回 admin/products.jsp
    }

    // ===== 2. 顯示新增/編輯表單 =====
    public String input() {
        if (id != null) {
            product = productService.findById(id);
        }
        return "input";  // 返回 admin/productForm.jsp
    }

    // ===== 3. 處理新增或更新 =====
    public String save() throws Exception {
        // 處理多圖上傳
        if (images != null) {
            String uploadDir = servletContext.getRealPath("/upload");
            for (int i = 0; i < images.size(); i++) {
                File src = images.get(i);
                String fileName = imagesFileName.get(i);
                File dest = new File(uploadDir, fileName);
                FileUtils.copyFile(src, dest);

                ProductImage pi = new ProductImage();
                pi.setImageUrl("/upload/" + fileName);
                pi.setImageType(ProductImage.ImageType.DETAIL);
                pi.setSortOrder(i);
                product.addImage(pi);
            }
        }

        // 根據是否有 ID 來決定 Insert 或 Update
        if (product.getId() == null) {
            productService.create(product);
        } else {
            productService.modify(product);
        }
        return SUCCESS;  // 導向 list action
    }

    // ===== 4. 刪除商品 =====
    public String delete() {
        if (id != null) {
            productService.remove(id);
        }
        return SUCCESS;  // 導向 list action
    }

    // ===== ModelDriven 接口 =====
    @Override
    public Product getModel() {
        return product;
    }

    // ===== Getter/Setter =====
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setImages(List<File> images) {
        this.images = images;
    }

    public void setImagesContentType(List<String> imagesContentType) {
        this.imagesContentType = imagesContentType;
    }

    public void setImagesFileName(List<String> imagesFileName) {
        this.imagesFileName = imagesFileName;
    }
}