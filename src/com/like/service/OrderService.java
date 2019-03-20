package com.like.service;

import com.like.domain.Orders;
import com.like.domain.PageBean;
import com.like.domain.User;
import java.sql.SQLException;

public interface OrderService
{
    void save(Orders orders) throws SQLException;

    PageBean getList(User user, String page, int num) throws Exception;

    Orders find(String id) throws Exception;
}
