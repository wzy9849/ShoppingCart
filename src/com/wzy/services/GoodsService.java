package com.wzy.services;

import com.wzy.entity.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> queryAllGoods();
    Goods queryGoodsById(int id);
}
