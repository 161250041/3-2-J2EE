package edu.nju.onlineStock.ServiceBean;


import edu.nju.onlineStock.Dao.CommodityDao;
import edu.nju.onlineStock.Model.Commodity;
import edu.nju.onlineStock.Service.CommodityService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;

@Stateless
public class CommodityServiceBean implements CommodityService {
    @EJB
    CommodityDao commodityDao;


    public ArrayList<Commodity> getCommodityList() {
        ArrayList<Commodity> commodities= commodityDao.getCommodityList();
        return commodities;
    }

    public Commodity getCommodityByCommodityID(int commodityID) {
        return commodityDao.getCommodityByCommodityID(commodityID);
    }

    public double getPriceByCommodityID(int commodityID) {
        return commodityDao.getPriceByCommodityID(commodityID);
    }
}
