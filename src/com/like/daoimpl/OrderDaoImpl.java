package com.like.daoimpl;

import com.like.dao.OrderDao;
import com.like.domain.OrderItem;
import com.like.domain.Orders;
import com.like.domain.Product;
import com.like.domain.User;
import com.like.utils.DataSourceUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Orders> getList(User user, String page, int num) throws Exception
    {
        //获取所有订单
        QueryRunner  queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String       sql         = "select * from orders where uid = ? order by ordertime desc limit ?, ? ";
        List<Orders> orders      = queryRunner.query(sql, new BeanListHandler<Orders>(Orders.class), user.getUid(), (Integer.parseInt(page) - 1) * num, num);
        //获取所有订单项
        for (Orders ord : orders) {
            //获取每个订单的订单项
            List<OrderItem> orderItems = ord.getOrderItems();
            String          sql1       = "select * from orderitem as o,product as  p where o.oid = ? and  p.pid = o.pid";
            //获取该订单下所有订单项
            List<Map<String, Object>> maps = queryRunner.query(sql1, new MapListHandler(), ord.getOid());

            for (Map<String, Object> map : maps) {
                OrderItem orderItem = new OrderItem();
                BeanUtils.populate(orderItem, map);
                Product product = new Product();
                BeanUtils.populate(product, map);
                orderItem.setProduct(product);
                orderItems.add(orderItem);
            }
        }

        return orders;
    }

    @Override
    public int getCount(User user) throws SQLException
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql         = "select count(*) from orders where uid = ? ";
        return ((Long) queryRunner.query(sql, new ScalarHandler(), user.getUid())).intValue();
    }

    @Override
    public Orders find(String id) throws Exception
    {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String      sql         = "select * from orders where oid = ?";
        Orders      orders      = queryRunner.query(sql, new BeanHandler<Orders>(Orders.class), id);

        String                    sql1   = "select * from orderitem o,product p where o.oid = ? and o.pid = p.pid";
        List<Map<String, Object>> query1 = queryRunner.query(sql1, new MapListHandler(), id);

        List<OrderItem> orderItems = orders.getOrderItems();
        for (Map<String, Object> map : query1) {
            OrderItem orderItem = new OrderItem();
            BeanUtils.populate(orderItem, map);
            Product product = new Product();
            BeanUtils.populate(product, map);
            orderItem.setProduct(product);
            orderItems.add(orderItem);
        }

        return orders;
    }
}
