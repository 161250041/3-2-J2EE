package nju.edu.order.Model;

public class Commodity {
    int commodityID;
    String commodityName;
    double commodityPrice;

    public Commodity(int commodityID, String commodityName, double commodityPrice) {
        this.commodityID = commodityID;
        this.commodityName = commodityName;
        this.commodityPrice = commodityPrice;
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
}
