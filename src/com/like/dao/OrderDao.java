package com.like.dao;

import com.like.domain.OrderItem;
import com.like.domain.Orders;
import com.like.domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao
{
    void saveOrder(Orders orders, Connection conn) throws SQLException;

    void saveItem(OrderItem orderItems, Connection conn) throws SQLException;

    List<Orders> getList(User user, String page, int num) throws Exception;

    int getCount(User user) throws SQLException;
}
