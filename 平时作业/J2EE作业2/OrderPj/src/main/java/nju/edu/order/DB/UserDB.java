package nju.edu.order.DB;

public interface UserDB {

    int addUser(String userID, String password);//user存在 返回0,不存在成功返回1

    boolean modifyBalance(String userID, Double price);

    String getPassword(String userID); //如果user存在返回password  不存在返回null

    Double getBalance(String userID);

}
