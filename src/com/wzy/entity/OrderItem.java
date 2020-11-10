package com.wzy.entity;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private int id;
    private Goods goods;
    private int num;
    private float price;
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Goods goods, int num, float price) {
        this.goods = goods;
        this.num = num;
        this.price = price;
    }

    public OrderItem(int id, Goods goods, int num, float price, Order order) {
        this.id = id;
        this.goods = goods;
        this.num = num;
        this.price = price;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", goods=" + goods +
                ", num=" + num +
                ", price=" + price +
                ", order=" + order +
                '}';
    }
}
