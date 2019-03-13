package com.like.daoimpl;

import com.like.dao.UserDao;
import com.like.domain.User;
import com.like.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao
{
    public void register(User user) throws SQLException
    {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into user values (?,?,?,?,?,?,?,?,?)";
        qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());
    }
}
