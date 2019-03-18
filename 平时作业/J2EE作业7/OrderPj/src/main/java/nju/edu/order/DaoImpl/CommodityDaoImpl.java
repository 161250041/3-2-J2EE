package nju.edu.order.DaoImpl;

import nju.edu.order.Dao.CommodityDao;
import nju.edu.order.Model.CommoditiesEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class CommodityDaoImpl implements CommodityDao {
    private static CommodityDaoImpl commodityDao=new CommodityDaoImpl();
    private Session session;

    private CommodityDaoImpl() {
    }

    public static CommodityDaoImpl getInstance(){
        return commodityDao;
    }


    public ArrayList<CommoditiesEntity> getCommodityList() {
        session = HibernateUtil.currentSession();
        ArrayList<CommoditiesEntity> commodities = new ArrayList<CommoditiesEntity>();

        try {
            Query query=session.createQuery("from CommoditiesEntity");
            commodities = (ArrayList<CommoditiesEntity>)query.list();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            System.out.println("商品列表为空");
            HibernateUtil.closeSession();
            return commodities;
        }

        return commodities;
    }

    public CommoditiesEntity getCommodityByCommodityID(int commodityID) {
        session = HibernateUtil.currentSession();
        try {
            Query query=session.createQuery("from CommoditiesEntity c where c.commodityID=?1");
            query.setParameter(1, commodityID);
            CommoditiesEntity commoditiesEntity = (CommoditiesEntity)query.getSingleResult();
            HibernateUtil.closeSession();
            return commoditiesEntity;
        } catch (Exception e) {
            System.out.println("无该商品");
            HibernateUtil.closeSession();
            e.printStackTrace();
            return null;
        }

    }

    public double getPriceByCommodityID(int commodityID) {
        session = HibernateUtil.currentSession();
        double price = 0.0;
        try {
            Query query=session.createQuery("select c.commodityPrice from CommoditiesEntity c where c.commodityID=?1");
            query.setParameter(1, commodityID);
            price= Double.parseDouble(query.getSingleResult().toString());
            HibernateUtil.closeSession();
            return price;
        } catch (Exception e) {
            System.out.println("无该商品");
            HibernateUtil.closeSession();
            e.printStackTrace();
            return price;
        }
//        double result=0.0;
//        Connection conn = DataBaseUtil.getConnection();
//        System.out.println("commodityID"+commodityID);
//        String str = "select commodityPrice from Commodities where commodityID=?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(str);
//            ps.setInt(1,commodityID);
//            ResultSet rs=ps.executeQuery();
//            while (rs.next()) {
//                result=rs.getDouble(1);
//                System.out.println(result);
//            }
//            rs.close();
//            ps.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
    }
}
