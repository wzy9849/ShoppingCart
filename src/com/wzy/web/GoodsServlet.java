package com.wzy.web;

import com.wzy.entity.Goods;
import com.wzy.services.GoodsService;
import com.wzy.services.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GoodsServlet", urlPatterns = "*.goods")
public class GoodsServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=utf-8");
        String path = request.getServletPath();
        if (path.equals("/goods/queryAllGoods.goods")) {
            queryAllGoods(request, response);
        }


    }

    protected void queryAllGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Goods> goods = goodsService.queryAllGoods();
        request.setAttribute("goods", goods);
        request.getRequestDispatcher("/list.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
