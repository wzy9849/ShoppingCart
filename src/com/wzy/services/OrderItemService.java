package com.wzy.services;

import com.wzy.entity.OrderItem;

import java.util.ArrayList;

public interface OrderItemService {

    boolean addOrderItem(OrderItem orderItem);

    ArrayList<OrderItem> queryOrderItemByOrderId(int order_id);
}
