package nju.edu.order.DaoImpl;

import nju.edu.order.Dao.UserDao;
import nju.edu.order.Factory.DaoFactory;
import org.junit.Test;


public class UserDaoImplTest {

    UserDao userDao = new DaoFactory().getUserDao();

    @Test
    public void addUser() {
        System.out.println(userDao.addUser("hyh", "123"));
        System.out.println(userDao.addUser("fbrr", "123"));
    }

    @Test
    public void modifyBalance() {
        userDao.modifyBalance("hyh", 100.0);
    }

    @Test
    public void getPassword() {
        System.out.println(userDao.getPassword("hyh"));
    }

}