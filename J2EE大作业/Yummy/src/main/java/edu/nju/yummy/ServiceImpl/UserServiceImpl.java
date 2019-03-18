package edu.nju.yummy.ServiceImpl;

import com.google.gson.JsonObject;
import edu.nju.yummy.Dao.*;
import edu.nju.yummy.Model.*;
import edu.nju.yummy.Service.UserService;
import edu.nju.yummy.Util.CodeUtil;
import edu.nju.yummy.Util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: hyh
 * @Description:
 * @Date: 2019-02-23 15:41
 * @Modified By: hyh
 */
@Service
public class UserServiceImpl implements UserService {  //0代表注册失败 1代表email已被注册 2代表注册成功等待验证
    @Autowired
    private RestaurantDao restaurantDao;
    @Autowired
    private CommodityInfoDao commodityInfoDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private VerificationDao verificationDao;
    @Autowired
    private OnlineBankingDao onlineBankingDao;

    public int regist(String email, String password) {
        //email是否存在
        if(userExist(email)) return 1; //已存在

        //利用正则表达式（可改进）验证邮箱是否符合邮箱的格式
        if(!email.matches("^\\w+@(\\w+\\.)+\\w+$")){
            System.out.println("不是有效邮箱");
            return 0;
        }
        //生成激活码
        String code= CodeUtil.getRandomString();
        //将用户保存到verification
        //保存成功则通过线程的方式给用户发送一封邮件
        boolean b;
        if (verificationDao.emailExist(email)) { //有曾经的未验证的记录
            b = verificationDao.modifyVerification(email, password, code, 0);
        }else {
            b = verificationDao.addVerification(email, password, code, 0);
        }
        if (b) {
            System.out.println(code);
//            new Thread(new MailUtil(email, code)).start();
            new MailUtil(email,code).run();
            return 2;
        }
        return 0;
    }

    public int signin(String email, String password) {  //0代表该用户不存在 1代表密码错误 2代表登陆成功
        if (userExist(email)){
            if (userDao.getPassword(email).equals(password)) {
                return 2;
            } else {
                return 1;
            }
        }
        return 0;
    }

    public int getUserId(String email) {
        return userDao.getUserIdByEmail(email);
    }

    public String getOBPassword(String email) {
        return onlineBankingDao.getPassword(email);
    }

    public double getBalance(String email) {
        return onlineBankingDao.getBalance(email);
    }

    public boolean logout(String email) {
        return false;
    }


    public boolean verify(String email,String code) {
        Verification verification = verificationDao.getVerification(email);
        //code是否已验证
        if (verification.getState() == 1) {
            return true;
        }
        //验证code
            //若code相同则addUser
        if (verification.getCode().equals(code)) {
            userDao.addUser(email, verification.getPassword());
            verificationDao.setState(email, 1);
            return true;
        }
            //不同则报错
        return false;
    }

    public boolean modifyInfo(String email,String phoneNumber, String name, ArrayList<Address> address) {
        return userDao.modifyUser(email,phoneNumber, name, address);
    }

    public ArrayList<String> getCommodityTypeList(int id) {
        return commodityInfoDao.getCommodityTypeList(id);
    }

//    public ArrayList<CommodityInfo> getCommodityListByType(int id, String type) {
//        return commodityInfoDao.getCommodityListByType(id,type);
//    }

//    public ArrayList<Restaurant> getRestaurantList() {
//        return restaurantDao.getRestaurantList();
//    }

    public ArrayList<Restaurant> getRestaurantListByType(String type) {
        return restaurantDao.getRestaurantListByType(type);
    }


    public ArrayList<CommodityInfo> getCommodityList(int id) {
        return commodityInfoDao.getCommodityList(id);
    }

    public double calcPunctualityPossibility(String resAdd,String userAdd,Timestamp expectedDeliveryTime) {
        //TODO
        return 0;
    }

    public String takeOrder(String email,double price, String commodities, int addressId,Timestamp expectedDeliveryTime,int restaurantId) {
        //计算多少钱
//        String[] commodityList = commodities.split(",");
//        double price = 0;
//        for (String commodity : commodityList) {
//            price+=commodityInfoDao.getPrice(Integer.parseInt(commodity));
//        }
        //是否有优惠
        double actualPrice = price;
        double consumption = userDao.getConsumption(email);
        if (consumption > 1000) {
            actualPrice *= 0.9;
        } else if (consumption > 500) {
            actualPrice *= 0.95;
        }
        if (price > 400) {
            actualPrice = 0.88*actualPrice;
        } else if (price > 200) {
            actualPrice = 0.9*actualPrice;
        } else if (price > 100) {
            actualPrice = 0.95*actualPrice;
        }
        double profit;
        if (actualPrice > 500) {
            profit = actualPrice * 0.9;
        } else if (actualPrice > 300) {
            profit = actualPrice * 0.88;
        } else if (actualPrice > 100) {
            profit = actualPrice * 0.85;
        } else {
            profit = actualPrice * 0.82;
        }
        price = (double) (Math.round(price * 100)) / 100;
        actualPrice = (double) (Math.round(actualPrice * 100)) / 100;
        profit = (double) (Math.round(profit * 100)) / 100;
        //addorder()
        Timestamp now = new Timestamp(System.currentTimeMillis());
        int orderId=orderDao.addOrder(email, commodities, addressId, now, expectedDeliveryTime, price, actualPrice, 0,profit,restaurantId);
        userDao.addConsumption(email, actualPrice);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("actualPrice", actualPrice);
        jsonObject.addProperty("orderId",orderId);
        return jsonObject.toString();
    }

    public Order getOrder(int orderId) {
        return orderDao.getOrderById(orderId);
    }

    //TODO 套餐

    public boolean pay(int orderId, double price, String obName) {
        //比较price与余额
            //扣款
            onlineBankingDao.modifyBalance(obName,price*(-1));
            //更改order state
            orderDao.setOrderState(orderId, 1);
//            orderDao.setObName(orderId,obName);
            return true;
            //减库存
    }

    public double cancelOrder(int orderId) {
        //查询order actualPrice和当前时间和可能性
        Order order=orderDao.getOrderById(orderId);
        long now = System.currentTimeMillis();
        long minute = (now - order.getBeginTime().getTime()) / 1000 / 60;

        //计算refund
        double refund = order.getRestaurantProfit();
        double price = refund;
        if (minute < 10) {
            refund *= 0.95;
        } else {
            if (minute < 40) {
                refund *= 0.8;
            } else if (minute < 60) {
                refund *= 0.85;
            } else if (minute < 90) {
                refund *= 0.9;
            }
        }

        System.out.println("计算出refund"+refund);
        //更改order state restaurantProfit refund
        orderDao.setOrderState(orderId, 4);
        orderDao.modifyOrderResProfit(orderId, -1 * refund);
        System.out.println(refund);
        orderDao.setOrderRefund(orderId, refund);

        //退款
        onlineBankingDao.modifyBalance(order.getEmail(), refund);
        System.out.println("退款给" + order.getEmail() + "金额" + refund);
        return refund;
    }

    public boolean cancelNotPaidOrder(int orderId) {
        orderDao.setOrderState(orderId, 3);
        orderDao.setOrderResProfit(orderId, 0);
        return true;
    }

    public ArrayList<Address> getAddressList(String email) {
        User user=userDao.getUserByEmail(email);
        System.out.println(user.getAddressSet().size());
        List<Address> addressList = user.getAddressSet();
        ArrayList<Address> addresses = new ArrayList<Address>();
        for (Address address : addressList) {
            addresses.add(address);
        }
        return addresses;
    }

    public ArrayList<Order> getOrderList(String email) {
        return orderDao.getUserOrderListByEmail(email);
    }

    public ArrayList<Order> getOrderListByRestaurantCode(String code) {
        return orderDao.getResOrderList(code);
    }

    public ArrayList<Order> getOrderListByTime(String email, Timestamp beginTime, Timestamp endTime) {
        return orderDao.getUserOrderListByTime(email, beginTime, endTime);
    }

    public ArrayList<User_ResStatistic> getUserStatisticsByRestaurant(String email, Timestamp beginTime, Timestamp endTime) {
        return orderDao.getUserStatisticsByRestaurant(email, beginTime, endTime);

    }

    public ArrayList<Order> getOrderListByPrice(String email,double price) {
        return orderDao.getUserOrderListByPrice(email, price);

    }

    public ArrayList<Order> getOrderListByRestaurant(String email,String restaurantId) {
        return orderDao.getUserOrderListByRestaurantId(email, restaurantId);
    }

    public boolean confirmReceipt(int orderId) {
        //更改orderstate
        orderDao.setOrderState(orderId, 2);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        orderDao.setEndTime(orderId, timestamp);
        return false;
    }

    public String getAddressByAddressId(int addressId) {
        return userDao.getAddressByAddressId(addressId);
    }

    public String getRestaurantNameById(int id) {
        return restaurantDao.getRestaurantById(id).getRestaurantName();
    }

    public User getUser(String email) {
        return userDao.getUserByEmail(email);
    }

    private boolean userExist(String email) {
        return userDao.userExist(email);
    }

}
