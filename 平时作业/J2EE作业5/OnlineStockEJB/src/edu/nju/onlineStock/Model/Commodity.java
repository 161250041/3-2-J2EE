package edu.nju.onlineStock.Model;

import java.io.Serializable;

public class Commodity implements Serializable {
    int commodityID;
    String commodityName;
    double commodityPrice;
    String url;

    public Commodity(int commodityID, String commodityName, double commodityPrice,String url) {
        this.commodityID = commodityID;
        this.commodityName = commodityName;
        this.commodityPrice = commodityPrice;
        this.url = url;
    }

    public int getCommodityID() {
        return commodityID;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public double getCommodityPrice() {
        return commodityPrice;
    }

    public String getUrl() {
        return url;
    }

    public void setCommodityID(int commodityID) {
        this.commodityID = commodityID;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public void setCommodityPrice(double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
