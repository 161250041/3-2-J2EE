package edu.nju.onlineStock.Service;


import edu.nju.onlineStock.Model.Order;

import javax.ejb.Remote;
import java.util.ArrayList;

@Remote
public interface OrderService {

    boolean createOrders(ArrayList<Order> orders);

    ArrayList<String> getValidOrdersByUserID(String userID);

    ArrayList<String> getnotValidOrdersByUserID(String userID) ;

    boolean makeOrderValid(int orderID);

    int getSize();
}
