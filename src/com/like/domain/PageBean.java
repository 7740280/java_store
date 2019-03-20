package com.like.domain;

import java.util.List;

public class PageBean<T>
{
    //当前页
    private Integer       currentPage;
    //每页显示的条数
    private Integer       pageSize;
    //总条数
    private Integer       totalCount;
    //总页数
    private Integer       totalPage;
    //每页显示的数据
    private List<T> productList;

    public Integer getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage)
    {
        this.currentPage = currentPage;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount)
    {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage)
    {
        this.totalPage = totalPage;
    }

    public List<T> getProductList()
    {
        return productList;
    }

    public void setProductList(List<T> productList)
    {
        this.productList = productList;
    }

    @Override
    public String toString()
    {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", productList=" + productList +
                '}';
    }
}
