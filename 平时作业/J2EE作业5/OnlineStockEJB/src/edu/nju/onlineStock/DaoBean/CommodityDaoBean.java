package edu.nju.onlineStock.DaoBean;


import edu.nju.onlineStock.Dao.CommodityDao;
import edu.nju.onlineStock.Model.Commodity;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Stateless
public class CommodityDaoBean implements CommodityDao {

    public ArrayList<Commodity> getCommodityList() {
        System.out.println(11);
        Connection conn = DataBaseUtil.getConnection();
        String str = "select * from Commodities";
        ArrayList<Commodity> commodities = new ArrayList<Commodity>();
        try {
            PreparedStatement ps = conn.prepareStatement(str);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                commodities.add(new Commodity(rs.getInt(1), rs.getString(2), rs.getDouble(3),rs.getString(4)));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commodities;
    }

    public Commodity getCommodityByCommodityID(int commodityID) {
        Commodity commodity;
        Connection conn = DataBaseUtil.getConnection();
        String str = "select * from Commodities where commodityID=?";
        try {
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setInt(1,commodityID);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                commodity = new Commodity(rs.getInt(1), rs.getString(2), rs.getDouble(3),rs.getString(4));
                rs.close();
                ps.close();
                conn.close();
                return commodity;
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public double getPriceByCommodityID(int commodityID) {
        double result=0.0;
        Connection conn = DataBaseUtil.getConnection();
        System.out.println("commodityID"+commodityID);
        String str = "select commodityPrice from Commodities where commodityID=?";
        try {
            PreparedStatement ps = conn.prepareStatement(str);
            ps.setInt(1,commodityID);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                result=rs.getDouble(1);
                System.out.println(result);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
