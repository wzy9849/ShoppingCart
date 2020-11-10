package com.wzy.dao.Impl;

import com.wzy.dao.OrderItemDao;
import com.wzy.entity.OrderItem;
import com.wzy.services.GoodsService;
import com.wzy.services.OrderService;
import com.wzy.services.impl.GoodsServiceImpl;
import com.wzy.services.impl.OrderServiceImpl;
import com.wzy.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderItemDaoImpl implements OrderItemDao {
    private GoodsService goodsService = new GoodsServiceImpl();
    private OrderService orderService = new OrderServiceImpl();

    @Override
    public boolean addOrderItem(OrderItem orderItem) {
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        int goods_id = orderItem.getGoods().getId();
        int num = orderItem.getNum();
        float price = orderItem.getPrice();
        int order_id = orderItem.getOrder().getId();

        String sql = "insert into order_item(goods_id,num,price,order_id) values(?,?,?,?)";
        int row = 0;

        try {
            row = queryRunner.update(sql, new Object[]{goods_id, num, price, order_id});
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<OrderItem> queryOrderItemByOrderId(int order_id) {
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        String sql = "select * from order_item where order_id = ?";

        try {
            List<Map<String, Object>> mapList = (List<Map<String, Object>>) queryRunner.query(sql,
                    order_id,
                    new MapListHandler());

            if (mapList != null) {
                for (Map<String, Object> map :
                        mapList) {
                    int id = (int) map.get("id");
                    int goods_id = (int) map.get("goods_id");
                    int num = (int) map.get("num");
                    float price = (float) map.get("price");
//                    int order_id1 = (int) map.get("order_id");
                    OrderItem orderItem = new OrderItem(id,goodsService.queryGoodsById(goods_id),
                            num,price,orderService.queryOrderByOrderId(order_id));
                    System.out.println(orderItem);
                    orderItems.add(orderItem);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItems;
    }

}
