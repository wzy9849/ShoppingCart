package com.wzy.dao.Impl;

import com.wzy.dao.GoodsDao;
import com.wzy.entity.Goods;
import com.wzy.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {

    @Override
    public List<Goods> queryAllGoods() {
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "select id,name,price from goods";
        List<Goods> list = null;
        try {
            list = (List<Goods>) queryRunner.query(sql, new BeanListHandler(Goods.class));
            //输出查询结果
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Goods queryGoodsById(int id) {
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "select id,name,price from goods where id = ?";
        Goods goods = null;
        try {
            goods = (Goods) queryRunner.query(sql, new Object[] {id},new BeanHandler<Goods>(Goods.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }


}
