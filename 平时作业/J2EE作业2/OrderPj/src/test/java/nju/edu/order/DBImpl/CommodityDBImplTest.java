package nju.edu.order.DBImpl;

import nju.edu.order.DB.CommodityDB;
import nju.edu.order.Model.Commodity;
import org.junit.Test;

import java.util.ArrayList;


public class CommodityDBImplTest {

    CommodityDB commodityDB = new CommodityDBImpl();


    @Test
    public void getCommodityList() {
        ArrayList<Commodity> commodities=commodityDB.getCommodityList();
        for (Commodity commodity : commodities) {
            System.out.println(commodity.getCommodityID()+","+commodity.getCommodityName()+","+commodity.getCommodityPrice());
        }
    }
}