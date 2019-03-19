package com.like.daoimpl;

import com.like.dao.OrderDao;
import com.like.domain.OrderItem;
import com.like.domain.Orders;
import org.apache.commons.dbutils.QueryRunner;
import java.sql.Connection;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao
{

    @Override
    public void saveOrder(Orders orders, Connection conn) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner();
        String      sql         = "insert into orders values (?,?,?,?,?,?,?,?)";
        queryRunner.update(
                conn,
                sql,
                orders.getOid(),
                orders.getOrdertime(),
                orders.getTotal(),
                orders.getState(),
                orders.getAddress(),
                orders.getName(),
                orders.getTelephone(),
                orders.getUser().getUid()
        );
    }

    @Override
    public void saveItem(OrderItem orderItems, Connection conn) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner();
        String      sql         = "insert into orderitem values (?,?,?,?,?)";
        queryRunner.update(
                conn,
                sql,
                orderItems.getItemid(),
                orderItems.getCount(),
                orderItems.getSubtotal(),
                orderItems.getProduct().getPid(),
                orderItems.getOrders().getOid()
        );
    }
}
