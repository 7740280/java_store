package com.like.web;

import com.like.domain.*;
import com.like.service.OrderService;
import com.like.serviceImpl.OrderServiceImpl;
import com.like.utils.UUIDUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends BaseServlet
{
    public String addOrder(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            //获取用户
            HttpSession session = request.getSession();
            User        user    = (User) session.getAttribute("user");
            if (user == null) {
                request.setAttribute("msg", "请先登录");
                return "/jsp/info.jsp";
            }

            //获取购物车
            Cart cart = (Cart) session.getAttribute("cart");

            //创建订单
            Orders orders = new Orders();
            //设置订单号
            orders.setOid(UUIDUtils.getUUID());
            //设置下单时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String           date             = simpleDateFormat.format(new Date());
            orders.setOrdertime(date);
            //获取总净额
            orders.setTotal(cart.getTotal());
            //设置状态
            orders.setState(0);
            //设置用户
            orders.setUser(user);
            //设置订单项
            Map<String, CartItem> map = cart.getMap();
            //获取订单项
            List<OrderItem> orderItems = orders.getOrderItems();

            for (String key : map.keySet()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setItemid(UUIDUtils.getUUID());
                orderItem.setCount(map.get(key).getCount());
                orderItem.setSubtotal(map.get(key).getSubTotal());
                orderItem.setProduct(map.get(key).getProduct());
                orderItem.setOrders(orders);
                //添加订单项
                orderItems.add(orderItem);
            }

            OrderService orderService = new OrderServiceImpl();
            orderService.save(orders);
            request.setAttribute("orders", orders);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "提交订单失败");
            return "/jsp/info.jsp";
        }

        return "/jsp/order_info.jsp";
    }
}
