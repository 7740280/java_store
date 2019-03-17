package com.like.web;

import com.like.domain.User;
import com.like.service.UserService;
import com.like.serviceImpl.UserServiceImpl;
import com.like.utils.MailUtils;
import com.like.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;


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

    public String register(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            User                  user         = new User();
            BeanUtils.populate(user, parameterMap);
            user.setUid(UUIDUtils.getUUID());
            user.setState(0);
            user.setCode(UUIDUtils.getUUID() + UUIDUtils.getUUID());
            UserService userService = new UserServiceImpl();
            //注册
            userService.register(user);
            //生成token
            String token = UUIDUtils.getUUID();
            //发送激活邮件
            MailUtils.sendMail(user.getEmail(), "这是一封激活邮件,请<a href=http://localhost:8080/store/user?method=active&token=" + user.getCode() + ">点击激活</a>.");

            request.setAttribute("msg", "注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "注册失败,请稍后再试..");
            try {
                request.getRequestDispatcher("/jsp/info.jsp").forward(request, response);
            } catch (ServletException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return "/jsp/info.jsp";
    }

    public String active(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            //查询用户是否存在
            String token = request.getParameter("token");

            UserService userService = new UserServiceImpl();
            User        user        = userService.getUserByToken(token);
            //没有,提示激活时间已过期
            if (user == null) {
                request.setAttribute("msg", "该用户不存在,请重新注册");
                return "/jsp/register.jsp";
            }
            //有,激活状态改为1
            user.setState(1);
            userService.active(user);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("msg", "激活失败,请稍后重试!!!");
            return "/jsp/info.jsp";
        }
        return "index.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserService userService = new UserServiceImpl();
            User        user        = userService.login(username, password);

            if (user == null) {
                request.setAttribute("msg", "用户不存在!!!");
                return "/jsp/login.jsp";
            }

            if (user.getState() == 0) {
                request.setAttribute("msg", "用户未激活!!!");
                return "/jsp/info.jsp";
            }

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            //这里必须使用重定向,不能使用请求转发.因为要返回浏览器将sessionID设置到cookie中去
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "登录失败,请稍后重试!!!");
            return "/jsp/info.jsp";
        }

        return null;
    }


    public String logout(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("msg", "退出失败,请稍后重试!!!");
            return "/jsp/info.jsp";
        }
        return null;
    }
}
