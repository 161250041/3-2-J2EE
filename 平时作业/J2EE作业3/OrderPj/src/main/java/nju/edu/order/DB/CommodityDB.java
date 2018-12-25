package nju.edu.order.DB;

import nju.edu.order.Model.Commodity;

import java.util.ArrayList;

public interface CommodityDB {

    ArrayList<Commodity> getCommodityList();

    Commodity getCommodityByCommodityID(int commodityID);

    double getPriceByCommodityID(int commodityID);


}
