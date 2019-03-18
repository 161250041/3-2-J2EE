package edu.nju.onlineStock.Action;

import edu.nju.onlineStock.Model.Commodity;

import java.io.Serializable;
import java.util.ArrayList;

public class CommodityListBean implements Serializable {

    private ArrayList<Commodity> commodityList;
    public ArrayList<Commodity> getCommodityList() {
        return commodityList;
    }
    public void setCommodityList(ArrayList<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

}
