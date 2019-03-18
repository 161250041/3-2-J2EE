package edu.nju.yummy.Service;

import edu.nju.yummy.Model.*;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface UserService {
    /**
     * @Author: hyh
     * @Description: 用户注册
     * 0代表注册失败 1代表email已被注册 2代表注册成功等待验证
     * @Date: 2019-02-14 01:51
     */
    int regist(String email, String password);

    /**
     * @Author: hyh
     * @Description: 用户登陆
     * 0代表该用户不存在 1代表密码错误 2代表登陆成功
     * @Date: 2019-02-14 01:51
     */
    int signin(String email, String password);

    int getUserId(String email);

    String getOBPassword(String email);

    double getBalance(String email);

    /**
     * @Author: hyh
     * @Description: 用户账户注销，会员自己注销，不可恢复，但不删除数据？
     * @Date: 2019-02-14 02:01
     */
    boolean logout(String email);

    /**
     * @Author: hyh
     * @Description: 注册验证
     * @Date: 2019-02-14 01:53
     */
    boolean verify(String email, String code);

    /**
     * @Author: hyh
     * @Description: 返回修改提交情况
     * @Date: 2019-02-14 01:56
     */
    boolean modifyInfo(String email, String phoneNumber, String name, ArrayList<Address> address);

//    ArrayList<Restaurant> getRestaurantList();

    ArrayList<CommodityInfo> getCommodityList(int id);

    ArrayList<String> getCommodityTypeList(int id);

//    ArrayList<CommodityInfo> getCommodityListByType(int id, String type);

    ArrayList<Restaurant> getRestaurantListByType(String type);

    String takeOrder(String email, double price,String commodities, int addressId, Timestamp expectedDeliveryTime,int restaurantId);

    Order getOrder(int orderId);

    double calcPunctualityPossibility(String resAdd, String userAdd, Timestamp expectedDeliveryTime);

    boolean pay(int orderId, double price, String obName);

    double cancelOrder(int orderId);

    boolean cancelNotPaidOrder(int orderId);

    ArrayList<Address> getAddressList(String email);

    ArrayList<Order> getOrderList(String email);

    ArrayList<Order> getOrderListByRestaurantCode(String code);

    ArrayList<Order> getOrderListByTime(String email, Timestamp beginTime, Timestamp endTime);

    ArrayList<User_ResStatistic> getUserStatisticsByRestaurant(String email, Timestamp beginTime, Timestamp endTime);

    ArrayList<Order> getOrderListByPrice(String email,double price);

    ArrayList<Order> getOrderListByRestaurant(String email,String restaurantId);

    String getAddressByAddressId(int addressId);

    String getRestaurantNameById(int id);

    User getUser(String email);

    //    收货确认（默认）
    boolean confirmReceipt(int orderId);


}
