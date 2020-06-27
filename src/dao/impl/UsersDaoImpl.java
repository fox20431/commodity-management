package dao.impl;

import dao.UsersDao;
import entity.Goods;
import entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDaoImpl implements UsersDao {

    public Connection conn=null;
    public PreparedStatement ps=null;
    public ResultSet rs=null;

    @Override
    public boolean login(Users users) {
        boolean flag = false;
        String sql = "select * from users where username=? and password=?";
        conn= BasicDaoImpl.getConnection();
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,users.getUsername());
            ps.setString(2, users.getPassword());
            rs=ps.executeQuery();
            if(rs.next()){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BasicDaoImpl.clossAll(conn,ps,rs);
        }

        return flag;
    }

    @Override
    public Users getUsersByUsername(String username) {
        Users users = new Users();
        String sql = "select * from users where username=?";
        conn= BasicDaoImpl.getConnection();
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            rs=ps.executeQuery();
            if(rs.next()){
                users.setId(rs.getInt("id"));
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));
                users.setEmail(rs.getString("email"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BasicDaoImpl.clossAll(conn,ps,rs);
        }
        return users;
    }

    @Override
    public int addUsers(Users users){
        // 变量 JAVA提供变量  自定义变量
        String sql = "INSERT INTO users(username,password,email) VALUES (?,?,?);";
        // 硬编码，不好
        String arrp[]={users.getUsername(),users.getPassword(),users.getEmail()};
        int i= BasicDaoImpl.executeSql(sql, arrp);
        return i;
    }

    @Override
    public boolean validateUsername(String username) {
        boolean flag = false;
        String sql = "select * from users where username = ?";
        conn= BasicDaoImpl.getConnection();
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            rs=ps.executeQuery();
            if(rs.next()){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BasicDaoImpl.clossAll(conn,ps,rs);
        }
        return flag;
    }


}
