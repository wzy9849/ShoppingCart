package com.wzy.services;

import com.wzy.entity.Order;
import com.wzy.entity.UserInfo;

import java.util.List;

public interface OrderService {
    boolean addOrder(Order order);

    List<Order> queryHistoryOrder(UserInfo userInfo);

    Order queryOrderByOrderNo(String orderNo);

    Order queryOrderByOrderId(int orderId);
}
