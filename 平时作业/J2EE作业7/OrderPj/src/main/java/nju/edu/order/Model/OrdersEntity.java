package nju.edu.order.Model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Orders", schema = "J2EEOrderPj")
public class OrdersEntity {

    private int orderID;

    private String userID;

    private int commodityID;

    private int isValid;

    private Timestamp time;

    private double discountPrice;

    private double price;

    private int num;

    public OrdersEntity(int orderID, String userID, int commodityID, boolean isValid, Timestamp time, double discountPrice, double price, int num) {
        this.orderID = orderID;
        this.userID = userID;
        this.commodityID = commodityID;
        if (isValid) {
            this.isValid = 1;
        } else {
            this.isValid = 0;
        }
        this.time = time;
        this.discountPrice = discountPrice;
        this.price = price;
        this.num = num;
    }

    public OrdersEntity() {
    }

    @Id
    @Column(name = "orderID")
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    @Basic
    @Column(name = "userID")
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Basic
    @Column(name = "commodityID")
    public int getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(int commodityID) {
        this.commodityID = commodityID;
    }

    @Basic
    @Column(name = "isValid")
    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "discountPrice")
    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "num")
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (orderID != that.orderID) return false;
        if (commodityID != that.commodityID) return false;
        if (isValid != that.isValid) return false;
        if (Double.compare(that.discountPrice, discountPrice) != 0) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (num != that.num) return false;
        if (userID != null ? !userID.equals(that.userID) : that.userID != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderID;
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + commodityID;
        result = 31 * result + isValid;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        temp = Double.doubleToLongBits(discountPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + num;
        return result;
    }
}
