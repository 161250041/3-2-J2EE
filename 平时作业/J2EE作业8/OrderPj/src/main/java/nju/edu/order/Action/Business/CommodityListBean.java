package nju.edu.order.Action.Business;

import nju.edu.order.Model.CommoditiesEntity;

import java.util.ArrayList;

public class CommodityListBean {

    private ArrayList<CommoditiesEntity> commodityList;
    public ArrayList<CommoditiesEntity> getCommodityList() {
        return commodityList;
    }
    public void setCommodityList(ArrayList<CommoditiesEntity> commodityList) {
        this.commodityList = commodityList;
    }

}
