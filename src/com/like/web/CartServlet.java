package com.like.web;

import com.like.domain.Cart;
import com.like.domain.CartItem;
import com.like.domain.Product;
import com.like.service.ProductService;
import com.like.serviceImpl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends BaseServlet
{
    public String addCart(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            //商品id
            String id = request.getParameter("id");
            //商品数量
            String quantity = request.getParameter("quantity");

            //获取商品
            ProductService productService = new ProductServiceImpl();
            Product        one            = productService.one(id);
            //获取购物项
            CartItem cartItem = new CartItem();
            cartItem.setProduct(one);
            cartItem.setCount(Integer.parseInt(quantity));

            //获取购物车
            Cart cart = getCart(request);
            cart.add(cartItem);
            request.setAttribute("cart", cart);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "添加商品失败");
            return "/jsp/info.jsp";
        }

        return "/jsp/cart.jsp";
    }

    //获取购物车
    private Cart getCart(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        Cart        cart    = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        return cart;
    }

    //删除购物项
    public String remove(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            String id   = request.getParameter("id");
            Cart   cart = getCart(request);
            cart.remove(id);
            response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "删除失败");
            return "/jsp/cart.jsp";
        }

        return null;
    }

    //清空购物车
    public String removeAll(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            Cart cart = getCart(request);
            cart.clear();
            response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("msg", "删除失败");
            return "/jsp/cart.jsp";
        }

        return null;
    }

    public String index(HttpServletRequest request, HttpServletResponse response)
    {
        return "/jsp/cart.jsp";
    }
}
