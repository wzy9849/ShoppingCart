package com.wzy.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CartServlet", urlPatterns = "*.cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/cart/addGoods.cart")) {
            addGoods(request, response);
        }
        if (path.equals("/cart/updateGoods.cart")) {
            updateGoods(request, response);
        }
        if (path.equals("/cart/deleteGoods.cart")) {
            deleteGoods(request, response);
        }
        if (path.equals("/cart/clearCart.cart")) {
            clearCart(request, response);
        }


    }

    protected void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        int goods_id = Integer.parseInt(request.getParameter("goods_id"));
        if (session.getAttribute("cart") == null) {
            Map cart = new HashMap();
            cart.put(goods_id, 1);
            session.setAttribute("cart", cart);
        } else {
            Map cart = (Map) session.getAttribute("cart");
            if (cart.get(goods_id) == null) {
                cart.put(goods_id, 1);
                session.setAttribute("cart", cart);
            } else {
                int num = (int) cart.get(goods_id);
                cart.put(goods_id, num + 1);
                session.setAttribute("cart", cart);
            }

        }
        response.sendRedirect(request.getContextPath() + "/list.jsp");

    }

    protected void updateGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int goods_id = Integer.parseInt(request.getParameter("ckitems"));
        if (request.getParameter("num").equals("")) {
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
        }
        int new_num = Integer.parseInt(request.getParameter("num"));
        HttpSession session = request.getSession();
        Map cart = (Map) session.getAttribute("cart");
        cart.replace(goods_id, new_num);
        session.setAttribute("cart", cart);

        response.sendRedirect(request.getContextPath() + "/cart.jsp");

    }

    protected void deleteGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ckitems = request.getParameter("ckitems");
        String[] ckitem = ckitems.split(",");

        HttpSession session = request.getSession();
        Map cart = (Map) session.getAttribute("cart");

        for (int i = 0; i < ckitem.length; i++) {
            int goods_id = Integer.parseInt(ckitem[i]);
            cart.remove(goods_id);
        }
        session.setAttribute("cart", cart);

        response.sendRedirect(request.getContextPath() + "/cart.jsp");
    }

    protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map cart = (Map) session.getAttribute("cart");
        cart.clear();
        session.setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/cart.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


}
