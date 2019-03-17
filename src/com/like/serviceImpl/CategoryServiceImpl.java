package com.like.serviceImpl;

import com.like.dao.CategoryDao;
import com.like.daoimpl.CategoryDaoImpl;
import com.like.domain.Category;
import com.like.service.CategoryService;
import net.sf.json.JSONArray;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService
{
    @Override
    public JSONArray findAll() throws SQLException
    {
        CategoryDao    categoryDao = new CategoryDaoImpl();
        List<Category> all         = categoryDao.findAll();
        JSONArray      jsonArray   = JSONArray.fromObject(all);
        return jsonArray;
    }
}
