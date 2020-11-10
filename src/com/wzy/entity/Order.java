package com.wzy.entity;

import java.io.Serializable;
import java.util.*;

public class Order implements Serializable {
    private int id;
    private String order_no;
    private UserInfo userInfo;
    private float sum_price;
    private Date createTime;

    public List<OrderItem> getOrderItemSet() {
        return orderItemSet;
    }

    public void setOrderItemSet(List<OrderItem> orderItemSet) {
        this.orderItemSet = orderItemSet;
    }

    private List<OrderItem> orderItemSet = new ArrayList<OrderItem>();



    public Order() {
    }
    public Order(int id, String order_no, UserInfo userInfo, float sum_price, Date createTime) {
        this.id = id;
        this.order_no = order_no;
        this.userInfo = userInfo;
        this.sum_price = sum_price;
        this.createTime = createTime;
    }
    public Order(String order_no, UserInfo userInfo, float sum_price, Date createTime) {
        this.order_no = order_no;
        this.userInfo = userInfo;
        this.sum_price = sum_price;
        this.createTime = createTime;
    }

    public Order(String order_no, UserInfo userInfo, float sum_price, Date createTime, List<OrderItem> orderItemSet) {
        this.order_no = order_no;
        this.userInfo = userInfo;
        this.sum_price = sum_price;
        this.createTime = createTime;
        this.orderItemSet = orderItemSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public float getSum_price() {
        return sum_price;
    }

    public void setSum_price(float sum_price) {
        this.sum_price = sum_price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_no='" + order_no + '\'' +
                ", userInfo=" + userInfo +
                ", sum_price=" + sum_price +
                ", createTime=" + createTime +
                ", orderItemSet=" + orderItemSet +
                '}';
    }
}
