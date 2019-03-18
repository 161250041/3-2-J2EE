package edu.nju.yummy.ServiceImpl;

import edu.nju.yummy.Dao.CommodityInfoDao;
import edu.nju.yummy.Dao.OrderDao;
import edu.nju.yummy.Dao.RestaurantDao;
import edu.nju.yummy.Model.CommodityInfo;
import edu.nju.yummy.Model.Order;
import edu.nju.yummy.Model.Restaurant;
import edu.nju.yummy.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-23 17:30
 * @Modified By: hyh
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantDao restaurantDao;
    @Autowired
    private CommodityInfoDao commodityInfoDao;
    @Autowired
    private OrderDao orderDao;

    public String register(String restaurantName, String password, String address, String type, String phoneNum) {
        //标识码生成
        int id=restaurantDao.getMaxId()+1;
        String restaurantId=Integer.toHexString(id);
        if (restaurantId.length() > 7) {
            System.out.println("餐厅标识码超过最大长度7");
            return "";
        } else {
            int len=7 - restaurantId.length();
            for (int i = 0; i < len; i++) {
                restaurantId = 0 + restaurantId;
            }
        }
        restaurantDao.addRestaurant(restaurantId,restaurantName, password, address, type, phoneNum);
        return restaurantId;
    }

    public int signin(String restaurantId, String password) {
        if (restaurantExist(restaurantId)) {
            if (restaurantDao.getPassword(restaurantId).equals(password)) {
                return 2;  //成功
            } else {
                return 1;  //密码错误
            }
        }
        System.out.println("不存在该id");
        return 0;//不存在该id
    }

    public Restaurant getRestaurantInfo(String code) {
        return restaurantDao.getRestaurantById(Integer.parseInt(code));
    }


    public boolean modifyInfo(int id, String restaurantName, String address, String type, String phoneNum) {
        boolean b=restaurantDao.modifyRestaurant(id, restaurantName, address, type, phoneNum);
        if (b) {
            restaurantDao.setRestaurantState(id,0);
            return true;
        }
        return false;
    }

    public boolean releaseCommodityInfo(String code, String commodityName, double commodityPrice, int commodityNum, Timestamp beginTime, Timestamp endTime, String type) {
        //发布
        return commodityInfoDao.addCommodity(code , commodityName, commodityPrice, commodityNum, beginTime, endTime, type);
    }

    public ArrayList<CommodityInfo> getCommodityInfo(String code) {
        return commodityInfoDao.getCommodityList(code);
    }

    public ArrayList<CommodityInfo> getSetInfo(String code) {
        return commodityInfoDao.getSetList(code);
    }

    public boolean deliver(int orderId) {
        return orderDao.setOrderState(orderId, 2); //2开始配送
    }

    public ArrayList<Order> getDeliveryRecord(int id) {
        ArrayList<Order> delivering=orderDao.getResOrderListByState(id,2);
        ArrayList<Order> delivered =orderDao.getResOrderListByState(id,3);
        delivering.addAll(delivered);
        return delivering;
    }

    public ArrayList<Order> getOrderList(String code) {
        return orderDao.getResOrderList(code);
    }

    public ArrayList<Order> getOrderListByTime(int id, Timestamp beginTime, Timestamp endTime) {
        return orderDao.getResOrderListByTime(id, beginTime, endTime);
    }

    public ArrayList<Order> getOrderListByPrice(int id,double price) {
        return orderDao.getResOrderListByPrice(id, price);
    }

    public ArrayList<Order> getOrderListByUser(int id,int userId) {
        return orderDao.getResOrderListByUserId(id, userId);
    }

    private boolean restaurantExist(String restaurantId) {
        return restaurantDao.restaurantExist(restaurantId);
    }
}
