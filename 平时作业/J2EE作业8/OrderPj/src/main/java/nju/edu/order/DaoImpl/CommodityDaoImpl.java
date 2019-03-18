package nju.edu.order.DaoImpl;

import nju.edu.order.Dao.CommodityDao;
import nju.edu.order.Model.CommoditiesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("CommodityDao")
public class CommodityDaoImpl implements CommodityDao {
    @Autowired
    protected SessionFactory sessionFactory;

    private Session session;

    private CommodityDaoImpl() {
    }

    public ArrayList<CommoditiesEntity> getCommodityList() {
        session = sessionFactory.getCurrentSession();
        ArrayList<CommoditiesEntity> commodities = new ArrayList<CommoditiesEntity>();

        try {
            Query query=session.createQuery("from CommoditiesEntity");
            commodities = (ArrayList<CommoditiesEntity>)query.list();
        } catch (Exception e) {
            System.out.println("商品列表为空");
            return commodities;
        }

        return commodities;
    }

    public CommoditiesEntity getCommodityByCommodityID(int commodityID) {
        session = sessionFactory.getCurrentSession();
        try {
            Query query=session.createQuery("from CommoditiesEntity c where c.commodityID=?1");
            query.setParameter(1, commodityID);
            CommoditiesEntity commoditiesEntity = (CommoditiesEntity)query.getSingleResult();
            return commoditiesEntity;
        } catch (Exception e) {
            System.out.println("无该商品");
            e.printStackTrace();
            return null;
        }

    }

    public double getPriceByCommodityID(int commodityID) {
        session = sessionFactory.getCurrentSession();
        double price = 0.0;
        try {
            Query query=session.createQuery("select c.commodityPrice from CommoditiesEntity c where c.commodityID=?1");
            query.setParameter(1, commodityID);
            price= Double.parseDouble(query.getSingleResult().toString());
            return price;
        } catch (Exception e) {
            System.out.println("无该商品");
            e.printStackTrace();
            return price;
        }
    }
}

