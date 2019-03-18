package nju.edu.order.ServiceImpl;

import nju.edu.order.Dao.CommodityDao;
import nju.edu.order.Dao.OrderDao;
import nju.edu.order.Model.CommoditiesEntity;
import nju.edu.order.Model.OrdersEntity;
import nju.edu.order.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CommodityDao commodityDao;

    private static OrderServiceImpl orderService = new OrderServiceImpl();

    private OrderServiceImpl() {
    }

    public static OrderServiceImpl getInstance() {
        return orderService;
    }

    public boolean createOrders(ArrayList<OrdersEntity> orders) {
        return orderDao.createOrders(orders);
    }

    public ArrayList<String> getValidOrdersByUserID(String userID) {
        ArrayList<OrdersEntity> orders = orderDao.getValidOrdersByUserID(userID);
        ArrayList<String> orderstrs = new ArrayList<String>();
        for (OrdersEntity order : orders) {
            int commodityID=order.getCommodityID();
            CommoditiesEntity commodity= commodityDao.getCommodityByCommodityID(commodityID);
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
        ArrayList<OrdersEntity> orders = orderDao.getnotValidOrdersByUserID(userID);
        ArrayList<String> orderstrs = new ArrayList<String>();
        for (OrdersEntity order : orders) {
            int commodityID=order.getCommodityID();
            CommoditiesEntity commodity= commodityDao.getCommodityByCommodityID(commodityID);
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
