package nju.edu.order.ServiceImpl;

import nju.edu.order.Service.CommodityService;
import nju.edu.order.Service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserServiceImplTest {
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    UserService userService = (UserService) ctx.getBean("UserService");

    @Test
    public void signin() {
        userService.signin("hyh","123");
        userService.signin("hyhh","123");

    }

    @Test
    public void getBalance() {
        System.out.println(userService.getBalance("hyh"));
    }

    @Test
    public void modifyBalance() {
        userService.modifyBalance("fbrr", 100);
    }
}