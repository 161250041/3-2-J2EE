package nju.edu.order.Action.Business;

import nju.edu.order.Model.Commodity;

import java.util.ArrayList;

public class CommodityListBean {

    private ArrayList<Commodity> commodityList;
    public ArrayList<Commodity> getCommodityList() {
        return commodityList;
    }
    public void setCommodityList(ArrayList<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

}
