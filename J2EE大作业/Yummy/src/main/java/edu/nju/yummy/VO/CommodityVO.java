package edu.nju.yummy.VO;

import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-03-11 09:53
 * @Modified By: hyh
 */
@Entity
public class CommodityVO {
    private String code;
    private String name;
    private double price;
    private int num;
    private Timestamp beginTime;
    private Timestamp endTime;
    private String type;

    public CommodityVO(String code, String name, double price, int num, Timestamp beginTime, Timestamp endTime, String type) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.num = num;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
