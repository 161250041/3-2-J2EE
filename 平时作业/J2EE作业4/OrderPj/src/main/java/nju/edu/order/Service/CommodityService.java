package nju.edu.order.Service;

import nju.edu.order.Model.Commodity;

import java.util.ArrayList;

public interface CommodityService {
    ArrayList<Commodity> getCommodityList();
    Commodity getCommodityByCommodityID(int commodityID);
    double getPriceByCommodityID(int commodityID);
}
