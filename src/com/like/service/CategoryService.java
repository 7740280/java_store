package com.like.service;

import net.sf.json.JSONArray;

import java.sql.SQLException;

public interface CategoryService
{
    JSONArray findAll() throws SQLException;
}
