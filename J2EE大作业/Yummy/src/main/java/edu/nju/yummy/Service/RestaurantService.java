package edu.nju.yummy.Service;

import edu.nju.yummy.Model.CommodityInfo;
import edu.nju.yummy.Model.Order;
import edu.nju.yummy.Model.Restaurant;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface RestaurantService {
    String register(String restaurantName,String password,String address,String type,String phoneNum);

    int signin(String restaurantId,String password);

    Restaurant getRestaurantInfo(String code);

    boolean modifyInfo(int id,String restaurantName,String address,String type,String phoneNum);

    boolean releaseCommodityInfo(String code, String commodityName, double commodityPrice, int commodityNum, Timestamp beginTime,Timestamp endTime,String type);

    //    boolean deliver(int orderId);
    ArrayList<CommodityInfo> getCommodityInfo(String code);

    ArrayList<CommodityInfo> getSetInfo(String code);

    ArrayList<Order> getDeliveryRecord(int id);

    //    查看本餐厅统计信息（详情）
    //    点餐/退订/财务等
    //    按照时间、金额、会员等分类统计
    ArrayList<Order> getOrderList(String code);
    ArrayList<Order> getOrderListByTime(int id, Timestamp beginTime,Timestamp endTime);
    ArrayList<Order> getOrderListByPrice(int id,double price);
    ArrayList<Order> getOrderListByUser(int id,int userId);
}
