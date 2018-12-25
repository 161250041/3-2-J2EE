package nju.edu.order.DBImpl;

import nju.edu.order.DB.OrderDB;
import nju.edu.order.Model.Order;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrderDBImplTest {
    OrderDB orderDB = new OrderDBImpl();

//    @Test
//    public void createOrders() {
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
////        Order order1 = new Order(0,"hyh",1,true,timestamp,12*0.9);
////        Order order2 = new Order(0,"hyh",2,true,timestamp,12*0.9);
//        ArrayList<Order> orders = new ArrayList<Order>();
////        orders.add(order1);
////        orders.add(order2);
//        orderDB.createOrders(orders);
//    }
//
//    @Test
//    public void getOrdersByUserID() {
//        ArrayList<Order>orders=orderDB.getValidOrdersByUserID("hyh");
//        for (Order order : orders) {
//            System.out.println(order.getOrderID());
//        }
//    }
}