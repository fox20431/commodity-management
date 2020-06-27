package dao.impl;
import java.sql.*;
import java.util.*;

import dao.GoodsDao;
import entity.Users;
import util.Tools;
import entity.Goods;


public class GoodsDaoImpl implements GoodsDao {

	public Connection conn=null;
	public PreparedStatement ps=null ;
    /*
	ResultSet类引用用于储存关系数据库的表
	通过getInt(),getString()等方法来取出
     */
	public ResultSet rs=null;

	/*
	这里不用BaseDao类的executeSql方法原因：
	executeSql完成所有操作后并返回了更改记录数
	而不能满足我们查询并获取记录内容的功能
	 */

	public int getAllPage(int usersId, int pageSize){ //获取总共多少页
		int allPageNo=1;//页数
		int allCount=0;//表内记录数

		String sql="select count(*) as all_count from goods where users_id =?";

		conn= BasicDaoImpl.getConnection();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,usersId);
			rs=ps.executeQuery();
			if(rs.next()){
				allCount=rs.getInt("all_count");
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}finally {
			BasicDaoImpl.clossAll(conn,ps,rs);
		}
		if(allCount%pageSize==0){
			allPageNo=allCount/pageSize;
		}else{
			allPageNo=allCount/pageSize+1;
		}
		return allPageNo;
	}

	public List getGoodsList(int usersId, String keyWord, String priceOrder, int pageSize, int pageNo){

		List list=new ArrayList();

		String sql="select * from goods where users_id = ?";

		if(keyWord !=null&&!"".equals(keyWord)){
			sql+=" and (name like '%"+ keyWord +"%' or intro like '%"+ keyWord +"%'）";
		}
		if(priceOrder!=null&&!"".equals(priceOrder)){
			if("yes".equals(priceOrder)){
				sql+=" order by price desc";
			}
			if("no".equals(priceOrder)){
				sql+=" order by price asc";
			}

		}else{
			sql+=" order by id asc";
		}

		sql+=" limit "+(pageNo-1)*pageSize+","+pageSize;
		
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
				goods.setId(rs.getInt("id"));
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

	//通过id删除
	public int delGoodsById(String id){
		String sql="delete from goods where id=?";
		String arrp[]={id};
		int i= BasicDaoImpl.executeSql(sql, arrp);
		return i;
	}
	

	// 修改
	public int updateGoodsById(Goods goods){
		// 变量 JAVA提供变量  自定义变量
		String sql="UPDATE goods SET name=?,type=?,intro=?,price=? WHERE id=?";
		// 硬编码，不好
		String arrp[]={goods.getName(),goods.getType(),goods.getIntro(),String.valueOf(goods.getPrice()),String.valueOf(goods.getId())};
		int i= BasicDaoImpl.executeSql(sql, arrp);
		return i;
	}


	public Goods getGoodsById(String id){
		Goods goods=new Goods();
		String sql="select * from goods where id=?";
		conn= BasicDaoImpl.getConnection();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();// 获取查询的结果
			if(rs.next()){//如果查询到了
				goods.setId(rs.getInt("id"));
				goods.setName(rs.getString("name"));
				goods.setType(rs.getString("type"));
				goods.setIntro(rs.getString("intro"));
				goods.setPrice(rs.getInt("price"));
				goods.setDate(rs.getString("date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			BasicDaoImpl.clossAll(conn, ps, rs);
		}
		return goods;
	}

	// 添加选项
	public int addGoods(Goods goods) { // 往数据库中插入数据
		String sql = "insert into goods (name,type,intro,price,date,users_id)values(?,?,?,?,?,?)";
		String arrp[] = {goods.getName(), goods.getType(), goods.getIntro(), String.valueOf(goods.getPrice()), Tools.DateTime(),String.valueOf(goods.getUsersId())};
		int i = BasicDaoImpl.executeSql(sql, arrp);
		return i;
	}
}

