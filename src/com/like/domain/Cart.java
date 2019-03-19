package com.like.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart
{
    private Map<String, CartItem> map = new LinkedHashMap<>();

    private double total;

    public Map<String, CartItem> getMap()
    {
        return map;
    }

    public void setMap(Map<String, CartItem> map)
    {
        this.map = map;
    }

    public double getTotal()
    {
        return total;
    }

    //    public void setTotal(double total)
//    {
//        this.total = total;
//    }
    //分为三种方式获取总金额

    //1.清空购物车
    public void clear()
    {
        //删除map
        map.clear();
        //总金额为0
        total = 0;
    }

    //2.添加
    public void add(CartItem cartItem)
    {
        CartItem item = map.get(cartItem.getProduct().getPid());
        if (item == null) {
            //原来没有
            map.put(cartItem.getProduct().getPid(), cartItem);
            total = total + cartItem.getSubTotal();
        } else {
            //原来有,这里之所以改变原map的值,是因为这里是引用
            item.setCount(item.getCount() + cartItem.getCount());
            total = total + item.getSubTotal();
        }
    }

    //3.删除
    public void remove(String key)
    {
        CartItem remove = map.remove(key);
        total = total - remove.getSubTotal();
    }


    @Override
    public String toString()
    {
        return "Cart{" +
                "map=" + map +
                ", total=" + total +
                '}';
    }
}
