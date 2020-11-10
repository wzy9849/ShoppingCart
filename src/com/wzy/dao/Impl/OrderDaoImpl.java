package com.wzy.dao.Impl;

import com.wzy.dao.OrderDao;
import com.wzy.dao.UserInfoDao;
import com.wzy.entity.Order;
import com.wzy.entity.UserInfo;
import com.wzy.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl implements OrderDao{
    private UserInfoDao userInfoDao = new UserInfoDaoImpl();


    @Override
    public boolean addOrder(Order order) {
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());

        String order_no = order.getOrder_no();
        int userInfo_id = order.getUserInfo().getId();
        float sum_price = order.getSum_price();
        Date createTime = order.getCreateTime();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);

        String sql = "insert into `orders` (order_no,userInfo_id,sum_price,createTime) values(?,?,?,?)";

        int row = 0;
        try {
            row = queryRunner.update(sql,
                    new Object[]{order_no, userInfo_id, sum_price, time});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("row = " + row);
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Order> queryHistoryOrder(UserInfo userInfo) {
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        int userInfo_id = userInfo.getId();
        String sql = "select * from `orders` where userInfo_id = ?";
        List<Order> list = null;
        try {
            list = (List) queryRunner.query(sql,
                    new Object[]{userInfo_id},
                    new BeanListHandler<>(Order.class));

            for (Order order :
                    list) {
                order.setUserInfo(userInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Order queryOrderByOrderNo(String orderNo) {
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "select * from `orders` where order_no = ?";
        Order order = null;
        try {
            order = (Order)queryRunner.query(sql,orderNo,new BeanHandler(Order.class));
            int userInfo_id = (int) queryRunner.query("select userInfo_id from `orders` where order_no = ?",orderNo,new ScalarHandler<Integer>());
            UserInfo userInfo = userInfoDao.queryUserInfoById(userInfo_id);
            order.setUserInfo(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public Order queryOrderByOrderId(int orderId) {
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "select * from `orders` where id = ?";
        Order order = null;
        try {
            order = (Order)queryRunner.query(sql,orderId,new BeanHandler(Order.class));
            int userInfo_id = (int) queryRunner.query("select userInfo_id from `orders` where id = ?",orderId,new ScalarHandler<Integer>());
            UserInfo userInfo = userInfoDao.queryUserInfoById(userInfo_id);
            order.setUserInfo(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }
}
