package nju.edu.order.Service;

import nju.edu.order.Model.CommoditiesEntity;

import java.util.ArrayList;

public interface CommodityService {
    ArrayList<CommoditiesEntity> getCommodityList();
    CommoditiesEntity getCommodityByCommodityID(int commodityID);
    double getPriceByCommodityID(int commodityID);
}
