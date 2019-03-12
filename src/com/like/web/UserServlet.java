package com.like.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends BaseServlet
{
    public String loginView(HttpServletRequest request, HttpServletResponse response)
    {
        return "/jsp/login.jsp";
    }

    public String registerView(HttpServletRequest request, HttpServletResponse response)
    {
        return "/jsp/register.jsp";
    }

}
