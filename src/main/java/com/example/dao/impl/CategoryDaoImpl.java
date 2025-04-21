package com.example.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.dao.CategoryDao;
import com.example.pojo.entity.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public List<Category> findAll() {
        return getSession().createQuery("FROM Category", Category.class).list();
    }
}
