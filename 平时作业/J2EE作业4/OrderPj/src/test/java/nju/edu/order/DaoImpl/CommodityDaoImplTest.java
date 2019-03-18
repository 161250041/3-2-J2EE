package nju.edu.order.DaoImpl;

import nju.edu.order.Dao.CommodityDao;
import nju.edu.order.Factory.DaoFactory;
import nju.edu.order.Model.Commodity;
import org.junit.Test;

import java.util.ArrayList;


public class CommodityDaoImplTest {

    CommodityDao commodityDao = new DaoFactory().getCommodityDao();


    @Test
    public void getCommodityList() {
        ArrayList<Commodity> commodities= commodityDao.getCommodityList();
        for (Commodity commodity : commodities) {
            System.out.println(commodity.getCommodityID()+","+commodity.getCommodityName()+","+commodity.getCommodityPrice());
        }
    }
}