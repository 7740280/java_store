package com.like.web;

import com.like.domain.PageBean;
import com.like.service.ProductService;
import com.like.serviceImpl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

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
}
