package edu.nju.onlineStock.DaoBean;


import edu.nju.onlineStock.Dao.CommodityDao;
import edu.nju.onlineStock.Model.Commodity;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;


@Stateless
public class CommodityDaoBean implements CommodityDao {
    @PersistenceUnit(name = "OnlineStockEJB")
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("OnlineStockEJB");

    @PersistenceContext
    protected EntityManager entityManager;


    public ArrayList<Commodity> getCommodityList() {
        entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("select new edu.nju.onlineStock.Model.Commodity(commodityID,commodityName,commodityPrice,url) from Commodity");
        ArrayList<Commodity> commodities = (ArrayList<Commodity>)query.getResultList();
        entityManager.clear();
        return commodities;
    }

    public Commodity getCommodityByCommodityID(int commodityID) {
        entityManager = factory.createEntityManager();
        Commodity commodity = entityManager.find(Commodity.class, commodityID);
        entityManager.clear();
        return commodity;
    }

    public double getPriceByCommodityID(int commodityID) {
        entityManager = factory.createEntityManager();
        Commodity commodity = entityManager.find(Commodity.class, commodityID);
        entityManager.clear();
        return commodity.getCommodityPrice();
    }
}
