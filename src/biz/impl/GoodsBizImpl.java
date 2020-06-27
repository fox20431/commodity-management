package biz.impl;

import biz.GoodsBiz;
import dao.GoodsDao;
import dao.impl.GoodsDaoImpl;
import entity.Goods;
import entity.Users;

import java.util.ArrayList;
import java.util.List;

public class GoodsBizImpl implements GoodsBiz {

    GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public List getGoodsList(int usersId,String keyWord, String priceOrder, int pageSize, int pageNo) {

        return goodsDao.getGoodsList(usersId, keyWord, priceOrder, pageSize, pageNo);

    }

    @Override
    public int addGoods(Goods goods) {
        return goodsDao.addGoods(goods);
    }

    @Override
    public int getAllPage(int usersId, int pageSize) {

        return goodsDao.getAllPage(usersId, pageSize);
    }

    @Override
    public int updateGoodsById(Goods goods) {
        return goodsDao.updateGoodsById(goods);
    }

    @Override
    public Goods getGoodsById(String id) {

        Goods goods = goodsDao.getGoodsById(id);

        return goods;
    }


}
