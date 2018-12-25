package nju.edu.order.DB;

import nju.edu.order.Model.Order;

import java.util.ArrayList;

public interface OrderDB {
    boolean createOrders(ArrayList<Order> orders);

    ArrayList<Order> getValidOrdersByUserID(String userID);

    ArrayList<Order> getnotValidOrdersByUserID(String userID);

    boolean makeOrderValid(int orderID);

    int getSize();

}
