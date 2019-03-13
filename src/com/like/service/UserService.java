package com.like.service;

import com.like.domain.User;

import java.sql.SQLException;

public interface UserService
{
    void register(User user) throws SQLException;
}
