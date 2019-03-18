package com.like.serviceImpl;

import com.like.dao.ProductDao;
import com.like.daoimpl.ProductDaoImpl;
import com.like.domain.PageBean;
import com.like.domain.Product;
import com.like.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService
{
    @Override
    public PageBean find(String id, int page, int pageSize) throws SQLException
    {
        ProductDao productDao = new ProductDaoImpl();
        //获取总条数
        int totalCount = productDao.totalCount(id);
        //获取总分页
        int totalPage = 0;
        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }

        List<Product> list = productDao.findList(id, (page - 1) * pageSize, pageSize);
        //获取每页显示的数据

        //封装page bean
        PageBean pageBean = new PageBean();

        pageBean.setCurrentPage(page);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setProductList(list);

        return pageBean;
    }

    @Override
    public Product one(String id) throws SQLException
    {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.one(id);
    }
}
