package com.like.service;

import java.sql.SQLException;

public interface CategoryService
{
    String findAll() throws SQLException;

    String findAllFromRedis() throws SQLException;
}
