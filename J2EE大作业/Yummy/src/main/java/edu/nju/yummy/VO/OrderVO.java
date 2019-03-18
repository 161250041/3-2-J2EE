package edu.nju.yummy.VO;

import edu.nju.yummy.Model.CommodityInfo;

import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-03-10 10:57
 * @Modified By: hyh
 */
@Entity
public class OrderVO {
    private String email;
    private String commodity;
    private double price;
//    private String password;
    private int addressId;
    private Timestamp expectedDeliveryTime;
    private int restaurantId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Timestamp getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }

    public void setExpectedDeliveryTime(Timestamp expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

}
