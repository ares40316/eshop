package com.example.dao;

import java.util.List;
import com.example.pojo.entity.user.Category;

public interface CategoryDao {
    List<Category> findAll();
}
