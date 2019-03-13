package com.like.dao;

import com.like.domain.User;

import java.sql.SQLException;

public interface UserDao
{
    void register(User user) throws SQLException;
}
