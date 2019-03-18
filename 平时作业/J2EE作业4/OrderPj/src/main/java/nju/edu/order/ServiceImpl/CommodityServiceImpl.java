package nju.edu.order.ServiceImpl;

import nju.edu.order.Dao.CommodityDao;
import nju.edu.order.Factory.DaoFactory;
import nju.edu.order.Model.Commodity;
import nju.edu.order.Service.CommodityService;

import java.util.ArrayList;

public class CommodityServiceImpl implements CommodityService {
    CommodityDao commodityDao =DaoFactory.getCommodityDao();

    private static CommodityServiceImpl commodityService=new CommodityServiceImpl();

    private CommodityServiceImpl() {
    }

    public static CommodityServiceImpl getInstance(){
        return commodityService;
    }

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
