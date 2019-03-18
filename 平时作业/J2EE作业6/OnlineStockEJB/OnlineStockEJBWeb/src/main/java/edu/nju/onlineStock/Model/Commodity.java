package edu.nju.onlineStock.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Commodities")
public class Commodity implements Serializable {
    @Id
    @Column(name = "commodityID")
    private int commodityID;

    @Column(name = "commodityName")
    private String commodityName;

    @Column(name = "commodityPrice")
    private double commodityPrice;

    @Column(name = "url")
    private String url;

    public Commodity(int commodityID, String commodityName, double commodityPrice,String url) {
        this.commodityID = commodityID;
        this.commodityName = commodityName;
        this.commodityPrice = commodityPrice;
        this.url = url;
    }

    public Commodity() {
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
