package com.like.domain;

public class Product
{
    private String   pid;
    private String   pname;
    private String   market_price;
    private double   shop_price;
    private String   pimage;
    private String   pdate;
    private String   is_hot;
    private String   pdesc;
    private String   pflag;
    //外键使用对象来表示
    private Category category;

    @Override
    public String toString()
    {
        return "Product{" +
                "pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", market_price='" + market_price + '\'' +
                ", shop_price=" + shop_price +
                ", pimage='" + pimage + '\'' +
                ", pdate='" + pdate + '\'' +
                ", is_hot='" + is_hot + '\'' +
                ", pdesc='" + pdesc + '\'' +
                ", pflag='" + pflag + '\'' +
                ", category=" + category +
                '}';
    }

    public String getPid()
    {
        return pid;
    }

    public void setPid(String pid)
    {
        this.pid = pid;
    }

    public String getPname()
    {
        return pname;
    }

    public void setPname(String pname)
    {
        this.pname = pname;
    }

    public String getMarket_price()
    {
        return market_price;
    }

    public void setMarket_price(String market_price)
    {
        this.market_price = market_price;
    }

    public double getShop_price()
    {
        return shop_price;
    }

    public void setShop_price(double shop_price)
    {
        this.shop_price = shop_price;
    }

    public String getPimage()
    {
        return pimage;
    }

    public void setPimage(String pimage)
    {
        this.pimage = pimage;
    }

    public String getPdate()
    {
        return pdate;
    }

    public void setPdate(String pdate)
    {
        this.pdate = pdate;
    }

    public String getIs_hot()
    {
        return is_hot;
    }

    public void setIs_hot(String is_hot)
    {
        this.is_hot = is_hot;
    }

    public String getPdesc()
    {
        return pdesc;
    }

    public void setPdesc(String pdesc)
    {
        this.pdesc = pdesc;
    }

    public String getPflag()
    {
        return pflag;
    }

    public void setPflag(String pflag)
    {
        this.pflag = pflag;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }
}
