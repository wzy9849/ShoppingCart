package com.wzy.dao;

import com.wzy.entity.Goods;

import java.util.List;

public interface GoodsDao {
    //查询所有Goods
    List<Goods> queryAllGoods();
    //根据goods_id查询Good
    Goods queryGoodsById(int id);
}
