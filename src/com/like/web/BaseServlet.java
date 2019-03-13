package com.like.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            //获取当前对象的字节码对象
            Class clazz = this.getClass();
            //获取到方法
            Method method = clazz.getMethod(request.getParameter("method"), HttpServletRequest.class, HttpServletResponse.class);
            //让方法执行
            String value = (String) method.invoke(clazz.newInstance(), request, response);
            if (value != null) {
                request.getRequestDispatcher(value).forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
