package com.example.dao;

import java.util.List;
import com.example.pojo.entity.Category;

public interface CategoryDao {
    List<Category> findAll();
}
