package nju.edu.order.ServiceImpl;
import nju.edu.order.Dao.UserDao;
import nju.edu.order.Factory.DaoFactory;
import nju.edu.order.Service.UserService;



public class UserServiceImpl implements UserService {
    private static UserServiceImpl signinService = new UserServiceImpl();
    UserDao userDao =DaoFactory.getUserDao();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance(){
        return signinService;
    }

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
