package nju.edu.order.Dao;

import nju.edu.order.Model.OrdersEntity;

import java.util.ArrayList;

public interface OrderDao {
    boolean createOrders(ArrayList<OrdersEntity> orders);

    ArrayList<OrdersEntity> getValidOrdersByUserID(String userID);

    ArrayList<OrdersEntity> getnotValidOrdersByUserID(String userID);

    boolean makeOrderValid(int orderID);

    int getSize();

}
