package edu.nju.onlineStock.Dao;

import javax.ejb.Remote;

@Remote
public interface UserDao {

    int addUser(String userID, String password);//user存在 返回0,不存在成功返回1

    boolean modifyBalance(String userID, double price);

    String getPassword(String userID); //如果user存在返回password  不存在返回null

    double getBalance(String userID);

}
