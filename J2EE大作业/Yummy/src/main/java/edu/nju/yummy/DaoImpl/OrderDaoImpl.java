package edu.nju.yummy.DaoImpl;

import edu.nju.yummy.Dao.OrderDao;
//import edu.nju.yummy.Model.Order;
import edu.nju.yummy.Model.Order;
import edu.nju.yummy.Model.User_ResStatistic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-24 15:33
 * @Modified By: hyh
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    protected SessionFactory sessionFactory;

    public int addOrder(String email, String commoditys, int addressId, Timestamp beginTime, Timestamp expectedDeliveryTime, double price, double actualPrice, int state,double resProfit,int restaurantId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        System.out.println(email+commoditys+addressId+beginTime+expectedDeliveryTime+" "+price+" "+actualPrice+" "+state+" "+resProfit);
        Query query = session.createSQLQuery("insert into Yummy.`Order`(email,commoditys,addressId,beginTime,expectedDeliveryTime,price,actualPrice,state,restaurantProfit,restaurantId) values(?,?,?,?,?,?,?,?,?,?)");
        query.setParameter(1, email);
        query.setParameter(2, commoditys);
        query.setParameter(3, addressId);
        query.setParameter(4, beginTime);
        query.setParameter(5, expectedDeliveryTime);
        query.setParameter(6, price);
        query.setParameter(7, actualPrice);
        query.setParameter(8, state);
        query.setParameter(9, resProfit);
        query.setParameter(10, restaurantId);
        query.executeUpdate();
//        Order order = new Order();
//        order.setEmail(email);
//        order.setCommoditys(commoditys);
//        order.setAddressId(addressId);
//        order.setBeginTime(beginTime);
//        order.setExpectedDeliveryTime(expectedDeliveryTime);
//        order.setPrice(price);
//        order.setActualPrice(actualPrice);
//        order.setState(state);
//        order.setRestaurantProfit(resProfit);
//        order.setObName(email);
//        session.saveOrUpdate(order);
        transaction.commit();

        Session session1 = sessionFactory.getCurrentSession();
        Transaction transaction1 = session1.beginTransaction();
        Query query1 = session1.createSQLQuery("select max(orderId) from Yummy.`Order`");
        int i=Integer.parseInt(query1.getSingleResult().toString());
        transaction1.commit();
        System.out.println("orderid"+i);
        return i;
    }

    public boolean setOrderState(int orderId,int state) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("update Yummy.`Order` set state=? where orderId=?");
        query.setParameter(1, state);
        query.setParameter(2, orderId);
        query.executeUpdate();
        transaction.commit();
        return true;
    }

    public boolean setEndTime(int orderId,Timestamp timestamp) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("update Yummy.`Order` set actualDeliveryTime=? where orderId=?");
        query.setParameter(1, timestamp);
        query.setParameter(2, orderId);
        query.executeUpdate();
        transaction.commit();
        return true;
    }

    public boolean setObName(int orderId, String obName) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("update Yummy.`Order` set obName=? where orderId=?");
        query.setParameter(1, obName);
        query.setParameter(2, orderId);
        query.executeUpdate();
        transaction.commit();
        return true;
    }

    public boolean modifyOrderResProfit(int orderId, double profit) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("update Yummy.`Order` set restaurantProfit=restaurantProfit+? where orderId=?");
        query.setParameter(1, profit);
        query.setParameter(2, orderId);
        query.executeUpdate();
        transaction.commit();
        return true;
    }

    public boolean setOrderResProfit(int orderId, double profit) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("update Yummy.`Order` set restaurantProfit=? where orderId=?");
        query.setParameter(1, profit);
        query.setParameter(2, orderId);
        query.executeUpdate();
        transaction.commit();
        return true;
    }

    public boolean setOrderRefund(int orderId, double refund) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("update Yummy.`Order` set refund=? where orderId=?");
        query.setParameter(1, refund);
        query.setParameter(2, orderId);
        query.executeUpdate();
        transaction.commit();
        return true;
    }

    public Order getOrderById(int orderId) {
        System.out.println(orderId);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createSQLQuery("select * from Yummy.`Order` where orderId=?").addEntity(Order.class);
//        Query query1 = session.createQuery(" from Order where orderId=?1");
        query.setParameter(1, orderId);
        Order order = (Order)query.getSingleResult();
        transaction.commit();
        return order;
    }

    public ArrayList<Order> getResOrderListByState(int id, int state) {
        return null;
    }

    public ArrayList<Order> getResOrderList(String code) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        int id = Integer.parseInt(code);
        Query query = session.createSQLQuery("select * from Yummy.`Order` where restaurantId=?").addEntity(Order.class);
        query.setParameter(1, id);
        ArrayList<Order> orders = (ArrayList<Order>) query.getResultList();
        transaction.commit();
        return orders;
    }

    public ArrayList<Order> getResOrderListByTime(int id, Timestamp beginTime, Timestamp endTime) {
        System.out.println(id);
        System.out.println(beginTime);
        System.out.println(endTime);
        System.out.println("==============");
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("select * from Yummy.`Order` where restaurantId=? and beginTime>=? and beginTime<=?").addEntity(Order.class);
        query.setParameter(1, id);
        query.setParameter(2, beginTime);
        query.setParameter(3, endTime);
        ArrayList<Order> orders = (ArrayList<Order>) query.getResultList();
        transaction.commit();
        return orders;
    }

    public ArrayList<Order> getResOrderListByPrice(int id, double price) {
        return null;
    }

    public ArrayList<Order> getResOrderListByUserId(int id, int userId) {
        return null;
    }

    public ArrayList<Order> getUserOrderListByEmail(String email) {
        //先检查是否有过期的 state=0 now-order.beginTime>2*60*1000   set state=3
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()-2*60*1000); //两分钟
        Query query = session.createSQLQuery("update Yummy.`Order` set Yummy.`Order`.state=3 where email=? and Yummy.`Order`.state=0 and Yummy.`Order`.beginTime<?");
//        Query query1 = session.createQuery(" from Order where orderId=?1");
        query.setParameter(1, email);
        query.setParameter(2, timestamp);
        query.executeUpdate();
        transaction.commit();

        Session session1 = sessionFactory.getCurrentSession();
        Transaction transaction1 = session1.beginTransaction();
        Query query1 = session1.createSQLQuery("select * from `Order` where email=?").addEntity(Order.class);
        query1.setParameter(1, email);
        ArrayList<Order> orders = (ArrayList<Order>)query1.list();
        transaction1.commit();
        return orders;
    }

    public ArrayList<Order> getUserOrderListByTime(String email, Timestamp beginTime, Timestamp endTime) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("select * from Yummy.`Order` where email=? and beginTime>=? and beginTime<=?").addEntity(Order.class);
        query.setParameter(1, email);
        query.setParameter(2, beginTime);
        query.setParameter(3, endTime);
        ArrayList<Order> orders = (ArrayList<Order>) query.getResultList();
        transaction.commit();
        return orders;
    }

    public ArrayList<User_ResStatistic> getUserStatisticsByRestaurant(String email, Timestamp beginTime, Timestamp endTime) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("select sum(actualPrice) as value,restaurantName as name from Yummy.`Order` as O,Restaurant as R where email=? and beginTime>=? and beginTime<=? and O.restaurantId=R.restaurantId group by O.restaurantId,restaurantName order by sum(price)")
                .addScalar( "name" , StandardBasicTypes.STRING) .addScalar( "value",StandardBasicTypes.DOUBLE).setResultTransformer(Transformers.aliasToBean(User_ResStatistic.class));
        query.setParameter(1, email);
        query.setParameter(2, beginTime);
        query.setParameter(3, endTime);
        ArrayList<User_ResStatistic> statistics =(ArrayList<User_ResStatistic> )query.list();
        System.out.println(statistics.size());
        System.out.println(query.list().size());
        transaction.commit();
        return statistics;
    }

    public ArrayList<Order> getUserOrderListByPrice(String email, double price) {
        return null;
    }

    public ArrayList<Order> getUserOrderListByRestaurantId(String email, String restaurantId) {
        return null;
    }

    public double getProfit(Timestamp beginTime,Timestamp endTime) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        double result = 0;
        Query query = session.createSQLQuery("select sum(actualPrice)-sum(refund)-sum(restaurantProfit)  from Yummy.`Order` where beginTime>=? and beginTime<?");
        query.setParameter(1, beginTime);
        query.setParameter(2, endTime);
        System.out.println(query.getResultList().size());
        if (query.getResultList().size() != 0&&query.getSingleResult()!=null) {
            result = Double.parseDouble(query.getSingleResult().toString());
        }
        System.out.println(result);
        System.out.println("end");
        transaction.commit();
        return result;
    }


}
