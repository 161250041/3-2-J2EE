package nju.edu.order.Dao;

import nju.edu.order.Model.CommoditiesEntity;

import java.util.ArrayList;

public interface CommodityDao {

    ArrayList<CommoditiesEntity> getCommodityList();

    CommoditiesEntity getCommodityByCommodityID(int commodityID);

    double getPriceByCommodityID(int commodityID);


}
