package nju.edu.order.Factory;

import nju.edu.order.Dao.CommodityDao;
import nju.edu.order.Dao.OrderDao;
import nju.edu.order.Dao.UserDao;
import nju.edu.order.DaoImpl.CommodityDaoImpl;
import nju.edu.order.DaoImpl.OrderDaoImpl;
import nju.edu.order.DaoImpl.UserDaoImpl;

public class DaoFactory {
    public static CommodityDao getCommodityDao(){
        return CommodityDaoImpl.getInstance();
    }
    public static OrderDao getOrderDao(){
        return OrderDaoImpl.getInstance();
    }
    public static UserDao getUserDao(){
        return UserDaoImpl.getInstance();
    }

}
