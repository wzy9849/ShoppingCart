package com.wzy.dao;

import com.wzy.entity.OrderItem;

import java.util.ArrayList;

public interface OrderItemDao {
    //添加OrderItem
    boolean addOrderItem(OrderItem orderItem);
    //根据order_id查询OrderItem列表
    ArrayList<OrderItem> queryOrderItemByOrderId(int order_id);
}
