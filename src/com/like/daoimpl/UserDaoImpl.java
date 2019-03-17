package com.like.daoimpl;

import com.like.dao.UserDao;
import com.like.domain.User;
import com.like.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao
{
    public void register(User user) throws SQLException
    {
        QueryRunner qr  = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql = "insert into user values (?,?,?,?,?,?,?,?,?)";
        qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());
    }

    public User getUserByToken(String token) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql         = "select * from user where code = ?";
        return queryRunner.query(sql, new BeanHandler<User>(User.class), token);
    }

    public void active(User user) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql         = "update user set state = ? where uid = ?";
        queryRunner.update(sql, user.getState(), user.getUid());
    }

    @Override
    public User login(String username, String password) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql         = "select * from user where username = ? and password = ?";
        return queryRunner.query(sql, new BeanHandler<User>(User.class), username, password);
    }
}
