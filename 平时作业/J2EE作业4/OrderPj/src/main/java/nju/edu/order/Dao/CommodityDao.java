package nju.edu.order.Dao;

import nju.edu.order.Model.Commodity;

import java.util.ArrayList;

public interface CommodityDao {

    ArrayList<Commodity> getCommodityList();

    Commodity getCommodityByCommodityID(int commodityID);

    double getPriceByCommodityID(int commodityID);


}
