package com.like.daoimpl;


import com.like.dao.ProductDao;
import com.like.domain.Product;
import com.like.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao
{
    @Override
    public List<Product> find(String id, int page, int pageSize)
    {
        return null;
    }

    @Override
    public int totalCount(String id) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql         = "select count(*) from product where cid = ?";
        return ((Long) queryRunner.query(sql, new ScalarHandler(), id)).intValue();
    }

    @Override
    public List<Product> findList(String id, int i, int pageSize) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql         = "select * from product where cid = ? limit ? , ?";
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class), id, i, pageSize);
    }

    @Override
    public Product one(String id) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql         = "select * from product where pid = ? ";
        return queryRunner.query(sql, new BeanHandler<Product>(Product.class), id);
    }

    @Override
    public List<Product> findHot() throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql         = "select * from product where is_hot = 0 limit 0 , 9 ";
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
    }

    @Override
    public List<Product> findNew() throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql         = "select * from product order by pdate desc limit 0, 9";
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
    }
}
