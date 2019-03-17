package com.like.service;

import net.sf.json.JSONArray;

import java.sql.SQLException;

public interface CategoryService
{
    String findAll() throws SQLException;

    String findAllFromRedis() throws SQLException;
}
