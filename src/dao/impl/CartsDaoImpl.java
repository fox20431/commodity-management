package dao.impl;

import dao.CartsDao;
import entity.Carts;
import entity.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartsDaoImpl implements CartsDao {

    public Connection conn=null;
    public PreparedStatement ps=null ;
    public ResultSet rs=null;

    public int addCarts(Carts carts) { // 往数据库中插入数据
        String sql = "insert into carts (goods_id,users_id)values(?,?)";
        String arrp[] = {String.valueOf(carts.getGoodsId()) ,String.valueOf(carts.getUsersId()) };
        int i = BasicDaoImpl.executeSql(sql, arrp);
        return i;
    }

    public List getCartsList(int usersId){

        List list=new ArrayList();

        String sql="select * from carts INNER JOIN goods ON carts.goods_id=goods.id where carts.users_id = ?";

        conn= BasicDaoImpl.getConnection();
        try {
            // 预声明要执行的函数
            ps=conn.prepareStatement(sql);
            ps.setInt(1,usersId);
            // executeQuery()返回rs
            rs=ps.executeQuery();

            //next()方法在遍历的过程返回true或false
            while(rs.next()){
                //将rs获取的数据封装到封装类中
                Goods goods=new Goods();
                goods.setId(rs.getInt("goods_id"));
                goods.setName(rs.getString("name"));
                goods.setType(rs.getString("type"));
                goods.setIntro(rs.getString("intro"));
                goods.setPrice(rs.getInt("price"));
                goods.setDate(rs.getString("date"));
                // 将封装的类打包
                list.add(goods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            BasicDaoImpl.clossAll(conn, ps, rs);
        }
        return list;
    }

    @Override
    public int delCarts(String goodsId) {
        String sql = "delete from carts where goods_id = ?";
        String arrp[] ={String.valueOf(goodsId)};

        int i = BasicDaoImpl.executeSql(sql, arrp);

        return i;
    }

}
