package edu.nju.onlineStock.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    @Id
    @Column(name = "orderID")
    private int orderID;

    @Column(name = "userID")
    private String userID;

    @Column(name = "commodityID")
    private int commodityID;

    @Column(name = "isValid")
    private boolean isValid;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "discountPrice")
    private double discountPrice;

    @Column(name = "price")
    private double price;

    @Column(name = "num")
    private int num;

    public Order(int orderID, String userID, int commodityID, boolean isValid, Timestamp time, double discountPrice, double price, int num) {
        this.orderID = orderID;
        this.userID = userID;
        this.commodityID = commodityID;
        this.isValid = isValid;
        this.time = time;
        this.discountPrice = discountPrice;
        this.price = price;
        this.num = num;
    }

    public Order() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(int commodityID) {
        this.commodityID = commodityID;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}