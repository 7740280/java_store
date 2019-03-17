package com.like.daoimpl;


import com.like.dao.ProductDao;
import com.like.domain.Product;
import com.like.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.management.Query;
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
}
