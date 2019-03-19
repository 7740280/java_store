package com.like.domain;

public class CartItem
{
    //商品
    private Product product;
    //数量
    private Integer count;
    //小计
    private double subTotal;

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public double getSubTotal()
    {
        return product.getShop_price() * count;
    }

//    public void setSubTotal(double subTotal)
//    {
//        this.subTotal = subTotal;
//    }


    @Override
    public String toString()
    {
        return "CartItem{" +
                "product=" + product +
                ", count=" + count +
                ", subTotal=" + subTotal +
                '}';
    }
}
