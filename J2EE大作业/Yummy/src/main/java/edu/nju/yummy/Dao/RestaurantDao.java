package edu.nju.yummy.Dao;

import edu.nju.yummy.Model.Restaurant;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-23 17:31
 * @Modified By: hyh
 */
public interface RestaurantDao {
    //state defult 0
    boolean addRestaurant(String restaurantId,String restaurantName, String password, String address, String type, String phoneNum);

    String getPassword(String restaurantId);

    boolean restaurantExist(String restaurantId);

    boolean modifyRestaurant(int id, String restaurantName, String address, String type, String phoneNum);

//    ArrayList<Restaurant> getUnapprovedApplication();

    boolean setRestaurantState(int id,int state);

    ArrayList<Restaurant> getRestaurantList(int state);

    ArrayList<Restaurant> getRestaurantListByType(String type);

    int getMaxId();

    Restaurant getRestaurantById(int id);

    int getNum();
}
