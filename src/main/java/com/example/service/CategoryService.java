package com.example.service;

import java.util.List;
import com.example.pojo.entity.user.Category;

public interface CategoryService {
    List<Category> findAll();
}
