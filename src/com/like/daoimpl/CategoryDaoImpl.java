package com.like.daoimpl;

import com.like.dao.CategoryDao;
import com.like.domain.Category;
import com.like.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao
{
    @Override
    public List<Category> findAll() throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql         = "select * from category";
        return queryRunner.query(sql, new BeanListHandler<Category>(Category.class));
    }
}
