package edu.nju.onlineStock.Service;

import edu.nju.onlineStock.Model.Commodity;

import javax.ejb.Remote;
import java.util.ArrayList;

@Remote
public interface CommodityService {
    ArrayList<Commodity> getCommodityList();
    Commodity getCommodityByCommodityID(int commodityID);
    double getPriceByCommodityID(int commodityID);
}
