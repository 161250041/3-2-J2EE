package edu.nju.yummy.Dao;

import edu.nju.yummy.Model.Order;
import edu.nju.yummy.Model.User_ResStatistic;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-23 18:03
 * @Modified By: hyh
 */
public interface OrderDao {
    int addOrder(String email, String commoditys, int addressId, Timestamp beginTime, Timestamp expectedDeliveryTime, double price, double actualPrice, int state,double resProfit,int restaurantId);

    boolean setOrderState(int orderId,int state);
    boolean setEndTime(int orderId,Timestamp timestamp);

    boolean setObName(int orderId, String obName);

    boolean modifyOrderResProfit(int orderId, double profit);

    boolean setOrderResProfit(int orderId, double profit);

    boolean setOrderRefund(int orderId, double refund);

    Order getOrderById(int orderId);

    ArrayList<Order> getResOrderListByState(int id, int state);

    ArrayList<Order>  getResOrderList(String code);

    ArrayList<Order> getResOrderListByTime(int id, Timestamp beginTime, Timestamp endTime);

    ArrayList<Order> getResOrderListByPrice(int id, double price);

    ArrayList<Order> getResOrderListByUserId(int id, int userId);

    ArrayList<Order> getUserOrderListByEmail(String email);

    ArrayList<Order> getUserOrderListByTime(String email, Timestamp beginTime, Timestamp endTime);

    ArrayList<User_ResStatistic> getUserStatisticsByRestaurant(String email, Timestamp beginTime, Timestamp endTime);

    ArrayList<Order> getUserOrderListByPrice(String email,double price);

    ArrayList<Order> getUserOrderListByRestaurantId(String email, String restaurantId);

    double getProfit(Timestamp beginTime,Timestamp endTime);

}
