package edu.nju.onlineStock.Dao;


import edu.nju.onlineStock.Model.Commodity;

import javax.ejb.Remote;
import java.util.ArrayList;

@Remote
public interface CommodityDao {

    ArrayList<Commodity> getCommodityList();

    Commodity getCommodityByCommodityID(int commodityID);

    double getPriceByCommodityID(int commodityID);


}
