package nju.edu.order.Model;

public class Commodity {
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
}
