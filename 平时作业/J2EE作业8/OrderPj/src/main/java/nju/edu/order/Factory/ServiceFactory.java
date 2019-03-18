package nju.edu.order.Factory;

import nju.edu.order.Service.CommodityService;
import nju.edu.order.Service.OrderService;
import nju.edu.order.Service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceFactory {
    private static ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    public static CommodityService getCommodityService(){
        CommodityService commodityService = (CommodityService) ctx.getBean("CommodityService");
        return commodityService;
    }
    public static UserService getUserService(){
        UserService userService = (UserService)ctx.getBean("UserService");
        return userService;
    }
    public static OrderService getOrderService(){
        OrderService orderService = (OrderService) ctx.getBean("OrderService");
        return orderService;
    }

}
