package edu.nju.yummy.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-03-10 19:11
 * @Modified By: hyh
 */
@Entity
@Table(name = "Order",schema = "Yummy")
public class Order {
    private int orderId;
    private String email;
    private String commoditys;
    private Integer addressId;
    private Timestamp beginTime;
    private Timestamp expectedDeliveryTime;
    private Timestamp actualDeliveryTime;
    private Double price;
    private Double actualPrice;
    private Integer state;//0待支付 1已支付在配送 2配送送达（成功） 3过期未支付 4已退款
    private Double refund;
    private Double restaurantProfit;
    private Integer restaurantId;

    public Order(int orderId, String email, String commoditys, Integer addressId, Timestamp beginTime, Timestamp expectedDeliveryTime, Timestamp actualDeliveryTime, Double price, Double actualPrice, Integer state, Double refund, Double restaurantProfit, Integer restaurantId) {
        this.orderId = orderId;
        this.email = email;
        this.commoditys = commoditys;
        this.addressId = addressId;
        this.beginTime = beginTime;
        this.expectedDeliveryTime = expectedDeliveryTime;
        this.actualDeliveryTime = actualDeliveryTime;
        this.price = price;
        this.actualPrice = actualPrice;
        this.state = state;
        this.refund = refund;
        this.restaurantProfit = restaurantProfit;
        this.restaurantId = restaurantId;
    }

    public Order() {
    }

    @Id
    @Column(name = "orderId")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "commoditys")
    public String getCommoditys() {
        return commoditys;
    }

    public void setCommoditys(String commoditys) {
        this.commoditys = commoditys;
    }

    @Basic
    @Column(name = "addressId")
    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "beginTime")
    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    @Basic
    @Column(name = "expectedDeliveryTime")
    public Timestamp getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }

    public void setExpectedDeliveryTime(Timestamp expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }

    @Basic
    @Column(name = "actualDeliveryTime")
    public Timestamp getActualDeliveryTime() {
        return actualDeliveryTime;
    }

    public void setActualDeliveryTime(Timestamp actualDeliveryTime) {
        this.actualDeliveryTime = actualDeliveryTime;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "actualPrice")
    public Double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Double actualPrice) {
        this.actualPrice = actualPrice;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "refund")
    public Double getRefund() {
        return refund;
    }

    public void setRefund(Double refund) {
        this.refund = refund;
    }

    @Basic
    @Column(name = "restaurantProfit")
    public Double getRestaurantProfit() {
        return restaurantProfit;
    }

    public void setRestaurantProfit(Double restaurantProfit) {
        this.restaurantProfit = restaurantProfit;
    }

    @Basic
    @Column(name = "restaurantId")
    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (email != null ? !email.equals(order.email) : order.email != null) return false;
        if (commoditys != null ? !commoditys.equals(order.commoditys) : order.commoditys != null) return false;
        if (addressId != null ? !addressId.equals(order.addressId) : order.addressId != null) return false;
        if (beginTime != null ? !beginTime.equals(order.beginTime) : order.beginTime != null) return false;
        if (expectedDeliveryTime != null ? !expectedDeliveryTime.equals(order.expectedDeliveryTime) : order.expectedDeliveryTime != null)
            return false;
        if (actualDeliveryTime != null ? !actualDeliveryTime.equals(order.actualDeliveryTime) : order.actualDeliveryTime != null)
            return false;
        if (price != null ? !price.equals(order.price) : order.price != null) return false;
        if (actualPrice != null ? !actualPrice.equals(order.actualPrice) : order.actualPrice != null) return false;
        if (state != null ? !state.equals(order.state) : order.state != null) return false;
        if (refund != null ? !refund.equals(order.refund) : order.refund != null) return false;
        if (restaurantProfit != null ? !restaurantProfit.equals(order.restaurantProfit) : order.restaurantProfit != null)
            return false;
        if (restaurantId != null ? !restaurantId.equals(order.restaurantId) : order.restaurantId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (commoditys != null ? commoditys.hashCode() : 0);
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        result = 31 * result + (beginTime != null ? beginTime.hashCode() : 0);
        result = 31 * result + (expectedDeliveryTime != null ? expectedDeliveryTime.hashCode() : 0);
        result = 31 * result + (actualDeliveryTime != null ? actualDeliveryTime.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (actualPrice != null ? actualPrice.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (refund != null ? refund.hashCode() : 0);
        result = 31 * result + (restaurantProfit != null ? restaurantProfit.hashCode() : 0);
        result = 31 * result + (restaurantId != null ? restaurantId.hashCode() : 0);
        return result;
    }
}
