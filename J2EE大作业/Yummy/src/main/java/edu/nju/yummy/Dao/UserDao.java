package edu.nju.yummy.Dao;

import edu.nju.yummy.Model.Address;
import edu.nju.yummy.Model.User;

import java.util.ArrayList;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-23 15:41
 * @Modified By: hyh
 */
public interface UserDao {
    boolean userExist(String email);

    boolean addUser(String email, String password);

    String getPassword(String email);

    boolean modifyUser(String email,String phoneNumber, String name, ArrayList<Address> address);

    int getUserIdByEmail(String email);

    User getUserByEmail(String email);

    boolean addConsumption(String email, double money);

    String getAddressByAddressId(int addressId);

    int getNum();

    double getConsumption(String email);

}
