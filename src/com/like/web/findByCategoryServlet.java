package com.like.web;

import com.like.domain.PageBean;
import com.like.domain.Product;
import com.like.service.ProductService;
import com.like.serviceImpl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "findByCategoryServlet", urlPatterns = "/product")
public class findByCategoryServlet extends BaseServlet
{
    public String findByCategory(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            //获取分类的id
            String id = request.getParameter("id");
            //获取当前页数
            int page = Integer.parseInt(request.getParameter("page"));
            //每页显示条数
            int pageSize = 12;

            ProductService productService = new ProductServiceImpl();
            PageBean       pageBean       = productService.find(id, page, pageSize);

            request.setAttribute("list", pageBean);
            request.setAttribute("id", id);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("msg", "查询失败");
            return "/jsp/info.jsp";
        }

        return "/jsp/product_list.jsp";
    }

    public String find(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            String         id             = request.getParameter("id");
            ProductService productService = new ProductServiceImpl();
            Product        product        = productService.one(id);

            request.setAttribute("product", product);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "没有查到该商品");
            return "/jsp/info.jsp";
        }

        return "/jsp/product_info.jsp";
    }

    public String findList(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            ProductService productService = new ProductServiceImpl();
            //查找热门商品
            List<Product> hot = productService.findHot();
            //查找最新商品
            List<Product> aNew = productService.findNew();

            request.setAttribute("hot", hot);
            request.setAttribute("aa", aNew);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "查询商品失败!");
            return "/jsp/info.jsp";
        }

        return "/jsp/index.jsp";
    }
}
