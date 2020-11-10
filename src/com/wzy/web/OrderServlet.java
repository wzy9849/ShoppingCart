package com.wzy.web;

import com.wzy.entity.Goods;
import com.wzy.entity.Order;
import com.wzy.entity.OrderItem;
import com.wzy.entity.UserInfo;
import com.wzy.services.GoodsService;
import com.wzy.services.OrderItemService;
import com.wzy.services.OrderService;
import com.wzy.services.impl.GoodsServiceImpl;
import com.wzy.services.impl.OrderItemServiceImpl;
import com.wzy.services.impl.OrderServiceImpl;
import com.wzy.utils.OrderNoUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "OrderServlet", urlPatterns = "*.order")
public class OrderServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private OrderItemService orderItemService = new OrderItemServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/order/settle.order")) {
            settle(request, response);
        }
        if (path.equals("/order/addOrder.order")) {
            addOrder(request, response);
        }
        if (path.equals("/order/queryHistory.order")) {
            queryHistory(request, response);
        }
        if (path.equals("/order/queryDetail.order")) {
            queryDetail(request, response);
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


    protected void settle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        float sum = Float.parseFloat(request.getParameter("sum"));
        String ckitems = request.getParameter("ckitems");
        String[] ckitem = ckitems.split(",");

        HttpSession session = request.getSession();
        Map cart = (Map) session.getAttribute("cart");
        Map<Integer, Integer> order = new HashMap();
        for (int i = 0; i < ckitem.length; i++) {
            int key = Integer.parseInt(ckitem[i]);
            if (cart.containsKey(key)) {
                order.put(key, (int) cart.get(key));
            }
        }

        System.out.println("cart:" + cart);
        System.out.println("order:" + order);

        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for (Map.Entry<Integer, Integer> entry : order.entrySet()) {
            int goods_id = entry.getKey();
            int num = entry.getValue();
            Goods goods = goodsService.queryGoodsById(goods_id);
            float price = goods.getPrice() * num;
            orderItems.add(new OrderItem(goods, num, price));
        }

        System.out.println("orderItems:" + orderItems);

        session.setAttribute("order", order);
        session.setAttribute("sum", sum);
        session.setAttribute("orderItems", orderItems);
        response.sendRedirect(request.getContextPath() + "/order.jsp");
    }

    protected void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<OrderItem> orderItems = (ArrayList<OrderItem>) session.getAttribute("orderItems");
        Map cart = (Map) session.getAttribute("cart");
        float sum_price = (float) session.getAttribute("sum");
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");

        String order_no = OrderNoUtils.generateUniqueNo();

        Order order = new Order(order_no, userInfo, sum_price, new Date());
        if (orderService.addOrder(order)) {
            for (OrderItem orderItem :
                    orderItems) {
                orderItem.setOrder(orderService.queryOrderByOrderNo(order.getOrder_no()));
                if (!orderItemService.addOrderItem(orderItem)) {
                    System.out.println("添加OrderItem失败！");
                } else {
                    cart.remove(orderItem.getGoods().getId());
                }
            }
            System.out.println("cart:" + cart);
            System.out.println("order:" + order);

            session.setAttribute("cart", cart);
            Order new_order = orderService.queryOrderByOrderNo(order.getOrder_no());
            new_order.setOrderItemSet(orderItems);
            request.setAttribute("order", new_order);
            request.getRequestDispatcher("/success.jsp").forward(request, response);

        } else {
            System.out.println("添加Order失败");
        }
    }

    protected void queryHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        List<Order> orderList = orderService.queryHistoryOrder(userInfo);
        request.setAttribute("orderList", orderList);

        System.out.println(orderList);
        request.getRequestDispatcher("/historyOrders.jsp").forward(request, response);
    }

    protected void queryDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int order_id = Integer.parseInt(request.getParameter("order_id"));
//        List<OrderItem> orderItems = orderItemService.queryOrderItemByOrderId(order_id);

        Order order = orderService.queryOrderByOrderId(order_id);
        order.setOrderItemSet(orderItemService.queryOrderItemByOrderId(order_id));

        request.setAttribute("order", order);
//        request.setAttribute("orderItems", orderItems);
        request.getRequestDispatcher("/orderDetail.jsp").forward(request, response);


    }


}
