package nju.edu.order.ServiceImpl;

import nju.edu.order.Service.CommodityService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class CommodityServiceImplTest {
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    CommodityService commodityService = (CommodityService) ctx.getBean("CommodityService");

    @Test
    public void getCommodityList() {

        System.out.println(commodityService.getCommodityList().size());
    }

    @Test
    public void getCommodityByCommodityID() {
        System.out.println(commodityService.getCommodityByCommodityID(1).getCommodityName());
    }

    @Test
    public void getPriceByCommodityID() {
        System.out.println(commodityService.getPriceByCommodityID(1));
    }
}