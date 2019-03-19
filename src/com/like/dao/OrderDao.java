package com.like.dao;

import com.like.domain.OrderItem;
import com.like.domain.Orders;
import java.sql.Connection;
import java.sql.SQLException;

public interface OrderDao
{
    void saveOrder(Orders orders, Connection conn) throws SQLException;

    void saveItem(OrderItem orderItems, Connection conn) throws SQLException;
}
