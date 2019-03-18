package edu.nju.onlineStock.ServiceBean;

import edu.nju.onlineStock.Dao.UserDao;
import edu.nju.onlineStock.Service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserServiceBean implements UserService {
    @EJB
    UserDao userDao;

    public int signin(String userID, String password) {
        String truePassword = userDao.getPassword(userID);
        if (truePassword == null) {    //用户不存在
            return 0;
        } else if(truePassword.equals(password)){ //登陆
            return 1;
        } else{  //密码错误
            return 2;
        }
    }
    public double getBalance(String userID) {
        return userDao.getBalance(userID);
    }
    public boolean modifyBalance(String userID, double price){
        return userDao.modifyBalance(userID, price);
    }
}
