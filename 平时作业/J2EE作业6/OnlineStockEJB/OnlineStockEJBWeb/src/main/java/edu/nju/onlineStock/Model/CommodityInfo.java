package edu.nju.onlineStock.Model;

import java.io.Serializable;

public class CommodityInfo implements Serializable {
    int id;
    int num;

    public CommodityInfo(int id, int num) {
        this.id = id;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
