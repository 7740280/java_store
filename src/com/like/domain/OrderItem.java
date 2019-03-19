package com.like.domain;

public class OrderItem
{
    private String  itemid;
    private Integer count;
    private Double  subtotal;
    private Product product;
    private Orders  orders;

    @Override
    public String toString()
    {
        return "OrderItem{" +
                "itemid='" + itemid + '\'' +
                ", count=" + count +
                ", subtotal=" + subtotal +
                ", product=" + product +
                ", orders=" + orders +
                '}';
    }

    public String getItemid()
    {
        return itemid;
    }

    public void setItemid(String itemid)
    {
        this.itemid = itemid;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public Double getSubtotal()
    {
        return subtotal;
    }

    public void setSubtotal(Double subtotal)
    {
        this.subtotal = subtotal;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public Orders getOrders()
    {
        return orders;
    }

    public void setOrders(Orders orders)
    {
        this.orders = orders;
    }
}
