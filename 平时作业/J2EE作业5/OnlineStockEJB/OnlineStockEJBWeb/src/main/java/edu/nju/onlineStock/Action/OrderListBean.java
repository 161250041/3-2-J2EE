package edu.nju.onlineStock.Action;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderListBean implements Serializable {
    private ArrayList<String> ordersList;
    public ArrayList<String> getOrdersList() {
        return ordersList;
    }
    public void setOrdersList(ArrayList<String> ordersList) {
        this.ordersList = ordersList;
    }
}
