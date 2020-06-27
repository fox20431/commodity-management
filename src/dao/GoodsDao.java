package dao;

import entity.Goods;
import entity.Users;

import java.util.List;

public interface GoodsDao {
    public int getAllPage(int usersId, int pageSize);
    public List getGoodsList(int usersId, String keyWord, String priceOrder, int pageSize, int pageNo);
    public int delGoodsById(String id);
    public int updateGoodsById(Goods goods);
    public Goods getGoodsById(String id);
    public int addGoods(Goods goods);

}
