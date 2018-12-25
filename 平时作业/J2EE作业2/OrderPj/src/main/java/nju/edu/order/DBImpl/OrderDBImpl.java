package nju.edu.order.DBImpl;

import nju.edu.order.DB.OrderDB;
import nju.edu.order.Model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDBImpl implements OrderDB {
    public boolean createOrders(ArrayList<Order> orders) {
        Connection conn = DataBaseUtil.getConnection();
        String str = "insert into Orders value (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(str);
            for (Order order : orders) {
                ps.setInt(1, order.getOrderID());
                ps.setString(2, order.getUserID());
                ps.setInt(3, order.getCommodityID());
                ps.setBoolean(4, order.isValid());
                ps.setTimestamp(5, order.getTimestamp());
                ps.setDouble(6, order.getDiscountPrice());
                ps.setDouble(7,order.getPrice());
                ps.setInt(8,order.getNum());
                ps.execute();
            }
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Order> getValidOrdersByUserID(String userID) {
        Connection conn = DataBaseUtil.getConnection();
        ArrayList<Order> orders = new ArrayList<Order>();
        String str = "select * from Orders where userID=? and isValid=?";
        try {
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, userID);
            ps.setBoolean(2,true);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(new Order(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getBoolean(4), rs.getTimestamp(5), rs.getDouble(6),
                        rs.getDouble(7),rs.getInt(8)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public ArrayList<Order> getnotValidOrdersByUserID(String userID) {
        Connection conn = DataBaseUtil.getConnection();
        ArrayList<Order> orders = new ArrayList<Order>();
        String str = "select * from Orders where userID=? and isValid=?";
        try {
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setString(1, userID);
            ps.setBoolean(2,false);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(new Order(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getBoolean(4), rs.getTimestamp(5), rs.getDouble(6),
                        rs.getDouble(7),rs.getInt(8)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public boolean makeOrderValid(int orderID) {
        Connection conn = DataBaseUtil.getConnection();
        String str = "update Orders set isValid = ? where orderID=?";
        try {
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setBoolean(1, true);
            ps.setInt(2, orderID);
            ps.execute();
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getSize() {
        Connection conn = DataBaseUtil.getConnection();
        String str = "select count(1) from Orders";
        try {
            PreparedStatement ps = conn.prepareStatement(str);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
