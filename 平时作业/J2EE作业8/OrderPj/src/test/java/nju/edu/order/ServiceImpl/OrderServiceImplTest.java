package nju.edu.order.ServiceImpl;

import nju.edu.order.Service.CommodityService;
import nju.edu.order.Service.OrderService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class OrderServiceImplTest {
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    OrderService orderService  = (OrderService) ctx.getBean("OrderService");

    @Test
    public void createOrders() {

    }

    @Test
    public void getValidOrdersByUserID() {
        System.out.println(orderService.getValidOrdersByUserID("hyh").get(0));
    }

    @Test
    public void getnotValidOrdersByUserID() {
        System.out.println(orderService.getnotValidOrdersByUserID("hyh").get(0));

    }

    @Test
    public void makeOrderValid() {
    }

    @Test
    public void getSize() {
    }
}