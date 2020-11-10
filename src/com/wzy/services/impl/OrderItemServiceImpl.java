package com.wzy.services.impl;

import com.wzy.dao.Impl.OrderItemDaoImpl;
import com.wzy.dao.OrderItemDao;
import com.wzy.entity.OrderItem;
import com.wzy.services.OrderItemService;

import java.util.ArrayList;

public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Override
    public boolean addOrderItem(OrderItem orderItem) {
        return orderItemDao.addOrderItem(orderItem);
    }

    @Override
    public ArrayList<OrderItem> queryOrderItemByOrderId(int order_id) {
        return orderItemDao.queryOrderItemByOrderId(order_id);
    }
}
