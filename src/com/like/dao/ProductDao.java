package com.like.dao;

import com.like.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao
{
    List<Product> find(String id, int page, int pageSize);

    int totalCount(String id) throws SQLException;

    List<Product> findList(String id, int i, int pageSize) throws SQLException;

    Product one(String id) throws SQLException;
}
