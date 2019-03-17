package com.like.service;

import com.like.domain.PageBean;
import java.sql.SQLException;

public interface ProductService
{
    PageBean find(String id, int page, int pageSize) throws SQLException;
}
