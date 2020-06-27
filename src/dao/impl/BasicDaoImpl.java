package dao.impl;

import java.sql.*;

public class BasicDaoImpl {

	/*
	初始化Connection引用conn为null，即conn没有对应指针
	Connection对象是基础，之后关于数据库其他对象都需要该对象的方法生成
	 */
	public static Connection conn=null;
	/*
	初始化PreparedStatement引用ps为null，即ps没有对应指针
	预声明要执行的sql，通过该类对象引用方法executeUpdate(),executeQuery()执行
	 */
	public static PreparedStatement ps=null;

	//获取数据库连接
	public static Connection getConnection(){
		String driverClass="org.mariadb.jdbc.Driver";
		String url = "jdbc:mariadb://localhost:3306/keshe";
		String user = "root" ;
		String password = "root";

		try {
			//加载Driver类
			Class.forName(driverClass);
			//后者返回值类型为Connection接口的实现对象
			conn=DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/*
	 * update通用sql语句操作
	 * 只适合有数据变更的sql语句，查询语句不适合
	 * 即只是用要执行executeUpdate方法的
	 * sql 执行的sql语句
	 * arrp PrepareStatement中IN参数（即"?"）的数组
	 * 返回值为executeUpdate()，即改变的记录数
	 */
	public static int executeSql(String sql, String arrp[]){
		int j=0;
		conn=getConnection();
		try {
			ps=conn.prepareStatement(sql);
			if(arrp!=null&&arrp.length>0){
				for(int i=0;i<arrp.length;i++){
					ps.setString(i+1, arrp[i]);
				}
			}
			//返回值为改变的行数
			j=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			clossAll(conn, ps, null);
		}
		return j;
	}


	// 关闭资源，参数为要关闭的资源
	// 关闭前判断是否为空，防止空指针异常
	public static void clossAll(Connection conn,PreparedStatement ps,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
