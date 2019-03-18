package nju.edu.order.DaoImpl;

import nju.edu.order.Dao.OrderDao;
import nju.edu.order.Factory.DaoFactory;
import nju.edu.order.Model.OrdersEntity;
import org.junit.Test;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrderDaoImplTest {
    OrderDao orderDao = DaoFactory.getOrderDao();

    @Test
    public void createOrders() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        OrdersEntity order = new OrdersEntity(51,"hyh",1,false,timestamp,25,25,1);
        ArrayList<OrdersEntity> ordersEntities = new ArrayList<OrdersEntity>();
        ordersEntities.add(order);
        orderDao.createOrders(ordersEntities);
    }

    @Test
    public void getValidOrdersByUserID() {
        System.out.println(orderDao.getValidOrdersByUserID("hyh").get(0).getOrderID());
    }

    @Test
    public void getnotValidOrdersByUserID() {
        System.out.println(orderDao.getnotValidOrdersByUserID("hyh").get(0).getOrderID());
    }

    @Test
    public void makeOrderValid() {
        orderDao.makeOrderValid(51);
    }

    @Test
    public void getSize() {
        System.out.println(orderDao.getSize());
    }
}