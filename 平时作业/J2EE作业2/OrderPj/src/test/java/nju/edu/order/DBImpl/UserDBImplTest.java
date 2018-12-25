package nju.edu.order.DBImpl;

import nju.edu.order.DB.UserDB;
import org.junit.Test;


public class UserDBImplTest {

    UserDB userDB = new UserDBImpl();

    @Test
    public void addUser() {
//        System.out.println(userDB.addUser("hyh", "123"));
        System.out.println(userDB.addUser("fbr", "123"));
    }

    @Test
    public void modifyBalance() {
        userDB.modifyBalance("hyh", 100.0);
    }

    @Test
    public void getPassword() {
        System.out.println(userDB.getPassword("hyh"));
    }

}