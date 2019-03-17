package com.like.dao;

import com.like.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao
{
    List<Category> findAll() throws SQLException;
}
