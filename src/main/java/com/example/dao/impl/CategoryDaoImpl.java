package com.example.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.dao.CategoryDao;
import com.example.pojo.entity.user.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        String hql = "FROM Category";
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(hql, Category.class).list();
    }
}
