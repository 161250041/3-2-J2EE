package edu.nju.yummy.ServiceImpl;

import edu.nju.yummy.Dao.OrderDao;
import edu.nju.yummy.Dao.RestaurantDao;
import edu.nju.yummy.Dao.UserDao;
import edu.nju.yummy.Model.Order;
import edu.nju.yummy.Model.Restaurant;
import edu.nju.yummy.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-23 18:04
 * @Modified By: hyh
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RestaurantDao restaurantDao;
    @Autowired
    private OrderDao orderDao;
    public ArrayList<Restaurant> getUnapprovedApplication() {
        return restaurantDao.getRestaurantList(0);
    }

    public boolean approveApplication(int id) {
        return restaurantDao.setRestaurantState(id,1);
    }

    public boolean rejectApplication(int id) {
        return restaurantDao.setRestaurantState(id,2);
    }

    public ArrayList<Double> getProfit() {
        ArrayList<Double> arrayList = new ArrayList<Double>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = df.parse("2018-10-01");
            Calendar cal = Calendar.getInstance();
            Calendar cal1 = Calendar.getInstance();
            cal.setTime(date);
            cal1.setTime(date);
            cal1.add(Calendar.MONTH, 1);
            Timestamp beginTime = new Timestamp(cal.getTimeInMillis());
            Timestamp endTime = new Timestamp(cal1.getTimeInMillis());
            for (int i = 0; i < 6; i++) {
                System.out.println(beginTime+" "+endTime);
                arrayList.add(orderDao.getProfit(beginTime,endTime));
                cal.add(Calendar.MONTH,1);
                cal1.add(Calendar.MONTH, 1);
                beginTime = new Timestamp(cal.getTimeInMillis());
                endTime = new Timestamp(cal1.getTimeInMillis());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public int getUserNum() {
        return userDao.getNum();
    }

    public int getResNum() {
        return restaurantDao.getNum();
    }

    public ArrayList<Order> getOrderDetailList() {
        return null;
    }
}
