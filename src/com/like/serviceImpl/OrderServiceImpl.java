package com.like.serviceImpl;

import com.like.dao.OrderDao;
import com.like.daoimpl.OrderDaoImpl;
import com.like.domain.OrderItem;
import com.like.domain.Orders;
import com.like.domain.PageBean;
import com.like.domain.User;
import com.like.service.OrderService;
import com.like.utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService
{
    @Override
    public void save(Orders orders) throws SQLException
    {
        Connection conn = null;
        try {
            //开启事务
            conn = DataSourceUtils.getConnection();
            conn.setAutoCommit(false);

            OrderDao orderDao = new OrderDaoImpl();
            orderDao.saveOrder(orders, conn);

            for (OrderItem orderItem : orders.getOrderItems()) {
                orderDao.saveItem(orderItem, conn);
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        }
    }

    @Override
    public PageBean getList(User user, String page, int num) throws Exception
    {
        OrderDao orderDao = new OrderDaoImpl();
        //获取每一页的数据
        List<Orders> list = orderDao.getList(user, page, num);

        //获取总条数
        int count = orderDao.getCount(user);
        //获取总页数
        int total = 0;
        if (count % num == 0) {
            total = count / num;
        } else {
            total = count / num + 1;
        }

        PageBean pageBean = new PageBean();
        //总页数
        pageBean.setTotalPage(total);
        //总条数
        pageBean.setTotalCount(count);
        //每一页显示条数
        pageBean.setPageSize(num);
        //当前页
        pageBean.setCurrentPage(Integer.parseInt(page));
        //内容
        pageBean.setProductList(list);

        return pageBean;
    }

    @Override
    public Orders find(String id) throws Exception
    {
        OrderDao orderDao = new OrderDaoImpl();
        return orderDao.find(id);
    }
}
