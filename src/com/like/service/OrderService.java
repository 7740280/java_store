package com.like.service;

import com.like.domain.Orders;

import java.sql.SQLException;

public interface OrderService
{
    void save(Orders orders) throws SQLException;
}
