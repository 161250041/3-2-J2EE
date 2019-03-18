package edu.nju.onlineStock.Model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable {
    int orderID;
    String userID;
    int commodityID;
    boolean isValid;
    Timestamp timestamp;
    double discountPrice;
    double price;
    int num;

    public Order(int orderID, String userID, int commodityID, boolean isValid, Timestamp timestamp, double discountPrice, double price, int num) {
        this.orderID = orderID;
        this.userID = userID;
        this.commodityID = commodityID;
        this.isValid = isValid;
        this.timestamp = timestamp;
        this.discountPrice = discountPrice;
        this.price = price;
        this.num = num;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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