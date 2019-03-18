package edu.nju.onlineStock.Dao;

import edu.nju.onlineStock.Model.Order;

import javax.ejb.Remote;
import java.util.ArrayList;

@Remote
public interface OrderDao {
    boolean createOrders(ArrayList<Order> orders);

    ArrayList<Order> getValidOrdersByUserID(String userID);

    ArrayList<Order> getnotValidOrdersByUserID(String userID);

    boolean makeOrderValid(int orderID);

    int getSize();

}
