package dao;

import entity.Carts;

import java.util.List;

public interface CartsDao {

    int addCarts(Carts carts);

    List getCartsList(int usersId);

    int delCarts(String goodsId);

}
