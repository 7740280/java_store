package com.like.serviceImpl;

import com.like.dao.UserDao;
import com.like.daoimpl.UserDaoImpl;
import com.like.domain.User;
import com.like.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService
{
    public void register(User user) throws SQLException
    {
        UserDao userDao = new UserDaoImpl();
        userDao.register(user);
    }
}
