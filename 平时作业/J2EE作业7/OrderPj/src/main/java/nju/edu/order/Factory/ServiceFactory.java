package nju.edu.order.Factory;

import nju.edu.order.Service.CommodityService;
import nju.edu.order.Service.OrderService;
import nju.edu.order.Service.UserService;
import nju.edu.order.ServiceImpl.CommodityServiceImpl;
import nju.edu.order.ServiceImpl.OrderServiceImpl;
import nju.edu.order.ServiceImpl.UserServiceImpl;

public class ServiceFactory {
    public static CommodityService getCommodityService(){
        return CommodityServiceImpl.getInstance();
    }
    public static UserService getUserService(){
        return UserServiceImpl.getInstance();
    }
    public static OrderService getOrderService(){
        return OrderServiceImpl.getInstance();
    }

}
