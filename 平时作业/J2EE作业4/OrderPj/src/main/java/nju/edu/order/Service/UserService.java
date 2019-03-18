package nju.edu.order.Service;

public interface UserService {
    int signin(String userID, String password);//返回值 0:用户不存在 1:登陆成功 2:密码错误
    double getBalance(String userID);
    boolean modifyBalance(String userID, double price);
}
