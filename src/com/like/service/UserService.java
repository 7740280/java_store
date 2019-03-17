package com.like.service;

import com.like.domain.User;

import java.sql.SQLException;

public interface UserService
{
    void register(User user) throws SQLException;

    User getUserByToken(String token) throws SQLException;

    void active(User user) throws SQLException;

    User login(String username, String password) throws SQLException;
}
