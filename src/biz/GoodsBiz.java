package biz;

import entity.Goods;
import entity.Users;

import java.util.List;

public interface GoodsBiz {

    public List getGoodsList(int UsersId, String keyWord, String priceOrder, int pageSize, int pageNo);

    public int addGoods(Goods goods);

    public int getAllPage(int usersId, int pageSize);

    public int updateGoodsById(Goods goods);

    public Goods getGoodsById(String id);
}
