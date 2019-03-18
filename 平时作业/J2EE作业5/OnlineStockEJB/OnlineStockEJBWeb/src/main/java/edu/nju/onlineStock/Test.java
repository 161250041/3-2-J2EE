package edu.nju.onlineStock;


import edu.nju.onlineStock.Factory.EJBFactory;
import edu.nju.onlineStock.Service.CommodityService;
import edu.nju.onlineStock.Service.OrderService;
import edu.nju.onlineStock.Service.UserService;


public class Test {
    public static void main(String[] args) {

//        UserService userService = (UserService) EJBFactory.getEJB("User");
//        System.out.println("getEJB success!");
//        System.out.println(userService.modifyBalance("hyh",1000));
//        CommodityService commodityService = (CommodityService) EJBFactory.getEJB("Commodity");
//        commodityService.getCommodityList();
//        commodityService.getPriceByCommodityID(2);
//        commodityService.getCommodityByCommodityID(2);
        OrderService orderService = (OrderService) EJBFactory.getEJB("Order");
        orderService.getnotValidOrdersByUserID("hyh");
        System.out.println(orderService.getValidOrdersByUserID("hyh"));


    }
}
