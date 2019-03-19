package com.like.serviceImpl;

import com.like.dao.OrderDao;
import com.like.daoimpl.OrderDaoImpl;
import com.like.domain.OrderItem;
import com.like.domain.Orders;
import com.like.service.OrderService;
import com.like.utils.DataSourceUtils;
import java.sql.Connection;
import java.sql.SQLException;

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
}
