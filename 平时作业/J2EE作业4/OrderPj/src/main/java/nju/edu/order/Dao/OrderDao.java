package nju.edu.order.Dao;

import nju.edu.order.Model.Order;

import java.util.ArrayList;

public interface OrderDao {
    boolean createOrders(ArrayList<Order> orders);

    ArrayList<Order> getValidOrdersByUserID(String userID);

    ArrayList<Order> getnotValidOrdersByUserID(String userID);

    boolean makeOrderValid(int orderID);

    int getSize();

}
