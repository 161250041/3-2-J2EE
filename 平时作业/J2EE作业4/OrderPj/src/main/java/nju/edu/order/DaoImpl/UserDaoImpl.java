package nju.edu.order.DaoImpl;


import nju.edu.order.Dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private static UserDaoImpl userDao=new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance(){
        return userDao;
    }

    public int addUser(String userID, String password) {
        if(getPassword(userID)!=null) return 0;//已被注册
        Connection conn = DataBaseUtil.getConnection();
        String str="insert into User value (?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setInt(1, 0);
            ps.setString(2, userID);
            ps.setString(3, password);
            ps.setDouble(4, 0);
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public boolean modifyBalance(String userID, double price) { //+ -
        if(getPassword(userID)==null)return false; //不存在该用户
        Connection conn = DataBaseUtil.getConnection();
        String str="update User set balance = balance+? where userID=?";
        try {
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setDouble(1, price);
            ps.setString(2,userID);
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("余额更新成功"+" "+price);

        return true;
    }


    public String getPassword(String userID) {

        Connection conn = DataBaseUtil.getConnection();
        try {
            String str = "select password from User where userID=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString(1);
            }
            // 完成后关闭
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public double getBalance(String userID) {
        Connection conn = DataBaseUtil.getConnection();
        try {
            String str = "select balance from User where userID=?";
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getDouble(1);
            }
            // 完成后关闭
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
