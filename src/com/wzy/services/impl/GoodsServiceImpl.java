package com.wzy.services.impl;

import com.wzy.dao.GoodsDao;
import com.wzy.dao.Impl.GoodsDaoImpl;
import com.wzy.entity.Goods;
import com.wzy.services.GoodsService;

import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    private GoodsDao goodsDao = new GoodsDaoImpl();
    @Override

    public List<Goods> queryAllGoods() {
        List<Goods> list = null;
        list = goodsDao.queryAllGoods();
        return list;

    }

    @Override
    public Goods queryGoodsById(int id) {
        return goodsDao.queryGoodsById(id);
    }
}
