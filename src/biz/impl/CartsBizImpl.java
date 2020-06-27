package biz.impl;

import biz.CartsBiz;
import dao.CartsDao;
import dao.impl.CartsDaoImpl;
import entity.Carts;

import java.util.List;

public class CartsBizImpl implements CartsBiz {

    CartsDao cartsDao = new CartsDaoImpl();

    public int addCarts(Carts carts){
        int i;

        i = cartsDao.addCarts(carts);

        return i;
    }

    public List getCartsList(int usersId) {

        return cartsDao.getCartsList(usersId);

    }

    @Override
    public int delCarts(String goodsId) {

        return cartsDao.delCarts(goodsId);
    }


}
