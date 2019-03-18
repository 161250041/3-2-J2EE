package edu.nju.yummy.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-23 00:10
 * @Modified By: hyh
 */
@Entity
@Table(name = "CommodityInfo", schema = "Yummy")
public class    CommodityInfo {
    private int commodityId;
    private int restaurantId;
    private String commodityName;
    private Double commodityPrice;
    private Integer commodityNum;
    private Timestamp beginTime;
    private Timestamp endTime;
    private String type;

    @Id
    @Column(name = "commodityId")
    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    @Basic
    @Column(name = "restaurantId")
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Basic
    @Column(name = "commodityName")
    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    @Basic
    @Column(name = "commodityPrice")
    public Double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    @Basic
    @Column(name = "commodityNum")
    public Integer getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(Integer commodityNum) {
        this.commodityNum = commodityNum;
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
    @Column(name = "endTime")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommodityInfo that = (CommodityInfo) o;

        if (commodityId != that.commodityId) return false;
        if (restaurantId != that.restaurantId) return false;
        if (commodityName != null ? !commodityName.equals(that.commodityName) : that.commodityName != null)
            return false;
        if (commodityPrice != null ? !commodityPrice.equals(that.commodityPrice) : that.commodityPrice != null)
            return false;
        if (commodityNum != null ? !commodityNum.equals(that.commodityNum) : that.commodityNum != null) return false;
        if (beginTime != null ? !beginTime.equals(that.beginTime) : that.beginTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commodityId;
        result = 31 * result + restaurantId;
        result = 31 * result + (commodityName != null ? commodityName.hashCode() : 0);
        result = 31 * result + (commodityPrice != null ? commodityPrice.hashCode() : 0);
        result = 31 * result + (commodityNum != null ? commodityNum.hashCode() : 0);
        result = 31 * result + (beginTime != null ? beginTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        
        return result;
    }
}
