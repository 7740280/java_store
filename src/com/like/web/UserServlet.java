package com.like.web;

import com.like.domain.User;
import com.like.service.UserService;
import com.like.serviceImpl.UserServiceImpl;
import com.like.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    public String register(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            User                  user         = new User();
            BeanUtils.populate(user, parameterMap);
            user.setUid(UUIDUtils.getUUID());
            user.setState(0);
            user.setCode(UUIDUtils.getUUID() + UUIDUtils.getUUID());
            UserService userService = new UserServiceImpl();
            userService.register(user);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "注册失败,请稍后再试..");
            request.getRequestDispatcher("/jsp/info.jsp").forward(request, response);
        }

        return null;
    }

}
