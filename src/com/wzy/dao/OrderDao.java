package com.wzy.dao;

import com.wzy.entity.Order;
import com.wzy.entity.UserInfo;

import java.util.List;

public interface OrderDao {
    //添加订单
    boolean addOrder(Order order);
    //根据用户id查询历史订单
    List<Order> queryHistoryOrder(UserInfo userInfo);
    //根据订单编号查询订单
    Order queryOrderByOrderNo(String orderNo);
    //根据订单id查询订单
    Order queryOrderByOrderId(int orderId);



}
