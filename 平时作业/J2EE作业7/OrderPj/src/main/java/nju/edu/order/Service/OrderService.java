package nju.edu.order.Service;

import nju.edu.order.Model.OrdersEntity;

import java.util.ArrayList;

public interface OrderService {

    boolean createOrders(ArrayList<OrdersEntity> orders);

    ArrayList<String> getValidOrdersByUserID(String userID);

    ArrayList<String> getnotValidOrdersByUserID(String userID) ;

    boolean makeOrderValid(int orderID);

    int getSize();
}
