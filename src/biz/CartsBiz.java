package biz;

import entity.Carts;

import java.util.List;

public interface CartsBiz {
    int addCarts(Carts carts);
    List getCartsList(int usersId);
    int delCarts(String goodsId);

}
