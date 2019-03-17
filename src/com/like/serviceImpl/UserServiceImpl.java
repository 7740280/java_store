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

    public User getUserByToken(String token) throws SQLException
    {
        UserDao userDao = new UserDaoImpl();
        return userDao.getUserByToken(token);
    }

    public void active(User user) throws SQLException
    {
        UserDao userDao = new UserDaoImpl();
        userDao.active(user);
    }

    @Override
    public User login(String username, String password) throws SQLException
    {
        UserDao userDao = new UserDaoImpl();
        return userDao.login(username, password);
    }

}
