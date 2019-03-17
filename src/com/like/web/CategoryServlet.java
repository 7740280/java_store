package com.like.web;

import com.like.domain.Category;
import com.like.service.CategoryService;
import com.like.serviceImpl.CategoryServiceImpl;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CategoryServlet", urlPatterns = "/category")
public class CategoryServlet extends BaseServlet
{
    public String allCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setCharacterEncoding("utf-8");
        try {
            CategoryService categoryService = new CategoryServiceImpl();
            //String       all             = categoryService.findAll();
            //改为从redis中获取数据
            String all = categoryService.findAllFromRedis();
            response.getWriter().print(all);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "查询分类失败");
        }
        return null;
    }
}
