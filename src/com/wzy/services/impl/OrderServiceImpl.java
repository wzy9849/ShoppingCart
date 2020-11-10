package com.wzy.services.impl;

import com.wzy.dao.Impl.OrderDaoImpl;
import com.wzy.dao.OrderDao;
import com.wzy.entity.Order;
import com.wzy.entity.UserInfo;
import com.wzy.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public boolean addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    @Override
    public List<Order> queryHistoryOrder(UserInfo userInfo) {
        return orderDao.queryHistoryOrder(userInfo);
    }

    @Override
    public Order queryOrderByOrderNo(String orderNo) {
        Order order = new Order();
        order = orderDao.queryOrderByOrderNo(orderNo);
        return order;
    }

    @Override
    public Order queryOrderByOrderId(int orderId) {
        Order order = new Order();
        order = orderDao.queryOrderByOrderId(orderId);
        return order;
    }
}
