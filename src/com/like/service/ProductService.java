package com.like.service;

import com.like.domain.PageBean;
import com.like.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService
{
    PageBean find(String id, int page, int pageSize) throws SQLException;

    Product one(String id) throws SQLException;

    List<Product> findHot() throws SQLException;

    List<Product> findNew() throws SQLException;
}
