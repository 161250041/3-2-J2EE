package edu.nju.onlineStock.ServiceBean;

import edu.nju.onlineStock.Dao.CommodityDao;
import edu.nju.onlineStock.Dao.OrderDao;
import edu.nju.onlineStock.Model.Commodity;
import edu.nju.onlineStock.Model.Order;
import edu.nju.onlineStock.Service.OrderService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;

@Stateless
public class OrderServiceBean implements OrderService {
    @EJB
    OrderDao orderDao;
    @EJB
    CommodityDao commodityDao;


    public boolean createOrders(ArrayList<Order> orders) {
        return orderDao.createOrders(orders);
    }

    public ArrayList<String> getValidOrdersByUserID(String userID) {
        ArrayList<Order> orders = orderDao.getValidOrdersByUserID(userID);
        ArrayList<String> orderstrs = new ArrayList<String>();
        for (Order order : orders) {
            int commodityID=order.getCommodityID();

            Commodity commodity= commodityDao.getCommodityByCommodityID(commodityID);
            String orderstr="订单号：" + order.getOrderID()
                    + "||商品名称：" + commodity.getCommodityName()
                    + " 商品单价：" + commodity.getCommodityPrice()
                    + " 购买数量：" + order.getNum()
                    +"||订单总价："+order.getDiscountPrice()+" <S>"+order.getPrice()+"</S>";
            orderstrs.add(orderstr);
        }

        return orderstrs;
    }

    public ArrayList<String> getnotValidOrdersByUserID(String userID) {
        ArrayList<Order> orders = orderDao.getnotValidOrdersByUserID(userID);
        ArrayList<String> orderstrs = new ArrayList<String>();
        for (Order order : orders) {
            int commodityID=order.getCommodityID();
            Commodity commodity= commodityDao.getCommodityByCommodityID(commodityID);
            String orderstr="订单号：" + order.getOrderID()
                    + "||商品名称：" + commodity.getCommodityName() + " 商品单价：" + commodity.getCommodityPrice() + " 购买数量：" + order.getNum()
                    +"||订单总价："+order.getDiscountPrice()+" <S>"+order.getPrice()+"</S>";
            orderstrs.add(orderstr);
        }
        return orderstrs;
    }

    public boolean makeOrderValid(int orderID) {
        return orderDao.makeOrderValid(orderID);
    }

    public int getSize() {
        return orderDao.getSize();
    }
}
