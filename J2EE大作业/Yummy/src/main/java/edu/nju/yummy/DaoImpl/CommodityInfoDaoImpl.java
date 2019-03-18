package edu.nju.yummy.DaoImpl;

import edu.nju.yummy.Dao.CommodityInfoDao;
import edu.nju.yummy.Model.CommodityInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-23 18:00
 * @Modified By: hyh
 */
@Repository("CommodityInfoDao")
public class CommodityInfoDaoImpl implements CommodityInfoDao {
    @Autowired
    protected SessionFactory sessionFactory;

    public boolean addCommodity(String code, String commodityName, double commodityPrice, int commodityNum, Timestamp beginTime, Timestamp endTime, String type) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        CommodityInfo commodityInfo = new CommodityInfo();
        int restaurantId = Integer.parseInt(code);
        commodityInfo.setRestaurantId(restaurantId);
        commodityInfo.setCommodityName(commodityName);
        commodityInfo.setCommodityPrice(commodityPrice);
        commodityInfo.setCommodityNum(commodityNum);
        commodityInfo.setBeginTime(beginTime);
        commodityInfo.setEndTime(endTime);
        commodityInfo.setType(type);
        session.save(commodityInfo);
        transaction.commit();
        return true;
    }

    public double getPrice(int commodityId) {
        return 0;
    }

//    public ArrayList<CommodityInfo> getCommodityListByType(int id, String type) {
//        return null;
//    }


    public ArrayList<String> getCommodityTypeList(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Query query = session.createQuery("select distinct type from CommodityInfo where restaurantId=?1 and beginTime<?2 and endTime>?2");
        query.setParameter(1, id);
        query.setParameter(2, timestamp);
        ArrayList<String> types = (ArrayList<String>)query.getResultList();
        transaction.commit();
        return types;
    }

    //取得当前时间有效的商品列表 1是餐厅id对得上 2是需要时间符合
    public ArrayList<CommodityInfo> getCommodityList(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Query query = session.createQuery("from CommodityInfo where restaurantId=?1 and beginTime<?2 and endTime>?2");
        query.setParameter(1, id);
        query.setParameter(2, timestamp);
        ArrayList<CommodityInfo> commodityInfos = (ArrayList<CommodityInfo>)query.getResultList();
        transaction.commit();
        return commodityInfos;
    }

    public ArrayList<CommodityInfo> getCommodityList(String code) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        int restaurantId = Integer.parseInt(code);
        Query query = session.createQuery("from CommodityInfo where restaurantId=?1");
        query.setParameter(1, restaurantId);
        ArrayList<CommodityInfo> commodityInfos = (ArrayList<CommodityInfo>)query.getResultList();
        transaction.commit();
        return commodityInfos;
    }

    public ArrayList<CommodityInfo> getSetList(String code) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        int restaurantId = Integer.parseInt(code);
        Query query = session.createQuery("from CommodityInfo where restaurantId=?1 and type=?2");
        query.setParameter(1, restaurantId);
        query.setParameter(2, "套餐");
        ArrayList<CommodityInfo> commodityInfos = (ArrayList<CommodityInfo>)query.getResultList();
        transaction.commit();
        return commodityInfos;
    }
}
