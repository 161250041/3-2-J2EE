package nju.edu.order.DaoImpl;

import nju.edu.order.Dao.OrderDao;
import nju.edu.order.Factory.DaoFactory;

public class OrderDBImplTest {
    OrderDao orderDao = new DaoFactory().getOrderDao();

//    @Test
//    public void createOrders() {
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
////        Order order1 = new Order(0,"hyh",1,true,timestamp,12*0.9);
////        Order order2 = new Order(0,"hyh",2,true,timestamp,12*0.9);
//        ArrayList<Order> orders = new ArrayList<Order>();
////        orders.add(order1);
////        orders.add(order2);
//        orderDao.createOrders(orders);
//    }
//
//    @Test
//    public void getOrdersByUserID() {
//        ArrayList<Order>orders=orderDao.getValidOrdersByUserID("hyh");
//        for (Order order : orders) {
//            System.out.println(order.getOrderID());
//        }
//    }
}