package com.like.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@WebFilter(filterName = "RequestFilter", urlPatterns = "/*")
public class RequestFilter implements Filter
{
    public void destroy()
    {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
    {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletRequest request = (HttpServletRequest) Proxy.newProxyInstance(
                httpServletRequest.getClass().getClassLoader(),
                httpServletRequest.getClass().getInterfaces(),
                new InvocationHandler()
                {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
                    {

                        if ("getParameter".equals(method.getName()) || "getParameterMap".equals(method.getName())) {

//                            if ("get".equalsIgnoreCase(httpServletRequest.httpServletRequest())) {
//                                String s = (String) method.invoke(httpServletRequest, args);
//                                return new String(s.getBytes("iso-8859-1"), "utf-8");
//                            }

                            if ("post".equalsIgnoreCase(httpServletRequest.getMethod())) {
                                httpServletRequest.setCharacterEncoding("utf-8");
                            }
                        }
                        return method.invoke(httpServletRequest, args);
                    }

                }
        );
        chain.doFilter(request, resp);
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
