package edu.nju.onlineStock;


import edu.nju.onlineStock.Factory.EJBFactory;
import edu.nju.onlineStock.Service.UserService;


public class Test {
    public static void main(String[] args) {

        UserService userService = (UserService) EJBFactory.getEJB("User");
//        System.out.println("getEJB success!");
        System.out.println(userService.getBalance("hyh"));
        System.out.println(userService.modifyBalance("hyh",1000));
        System.out.println(userService.getBalance("hyh"));
        System.out.println(userService.signin("hhh","123"));
        System.out.println(userService.signin("hyh","123"));
//        CommodityService commodityService = (CommodityService) EJBFactory.getEJB("Commodity");
//        ArrayList<Commodity>commodities=commodityService.getCommodityList();
//        for (Commodity c : commodities) {
//            System.out.println(c.getCommodityID());
//            System.out.println(commodityService.getCommodityByCommodityID(c.getCommodityID()).getCommodityID());
//            System.out.println(commodityService.getPriceByCommodityID(c.getCommodityID()));
//        }
//        commodityService.getPriceByCommodityID(2);
//        commodityService.getCommodityByCommodityID(2);
//        OrderService orderService = (OrderService) EJBFactory.getEJB("Order");
//        ArrayList<String> validOrders=orderService.getValidOrdersByUserID("hyh");
//        System.out.println("=================orders=====");
//        for (String s : validOrders) {
//            System.out.println(s);
//        }
//        ArrayList<String> notvalidOrders=orderService.getnotValidOrdersByUserID("hyh");
//        for (String s : notvalidOrders) {
//            System.out.println(s);
//        }

    }
}
