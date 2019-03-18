package nju.edu.order.DaoImpl;

import nju.edu.order.Dao.CommodityDao;
import nju.edu.order.Factory.DaoFactory;
import nju.edu.order.Model.CommoditiesEntity;
import org.junit.Test;

import java.util.ArrayList;


public class CommodityDaoImplTest {

    CommodityDao commodityDao = new DaoFactory().getCommodityDao();


    @Test
    public void getCommodityList() {
        ArrayList<CommoditiesEntity> commodities= commodityDao.getCommodityList();
        for (CommoditiesEntity commodity : commodities) {
            System.out.println(commodity.getCommodityID()+","+commodity.getCommodityName()+","+commodity.getCommodityPrice());
        }
    }

    @Test
    public void getInstance() {
    }


    @Test
    public void getCommodityByCommodityID() {
        System.out.println(commodityDao.getCommodityByCommodityID(1).getCommodityName());
    }

    @Test
    public void getPriceByCommodityID() {
        System.out.println(commodityDao.getPriceByCommodityID(1));
    }
}