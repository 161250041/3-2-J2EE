package edu.nju.yummy.DaoImpl;

import edu.nju.yummy.Dao.RestaurantDao;
import edu.nju.yummy.Model.Restaurant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-23 17:35
 * @Modified By: hyh
 */
@Repository
public class RestaurantDaoImpl implements RestaurantDao {
    @Autowired
    protected SessionFactory sessionFactory;

    public boolean addRestaurant(String restaurantId, String restaurantName, String password, String address, String type, String phoneNum) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(restaurantId);
        restaurant.setRestaurantName(restaurantName);
        restaurant.setPassword(password);
        restaurant.setAddress(address);
        restaurant.setType(type);
        restaurant.setPhoneNumber(phoneNum);
        session.save(restaurant);
        transaction.commit();
        return true;
    }

    public String getPassword(String restaurantId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select password from Restaurant where restaurantId=?1");
        query.setParameter(1, restaurantId);
        String password = query.getSingleResult().toString();
        transaction.commit();
        return password;
    }

    public boolean restaurantExist(String restaurantId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Restaurant where restaurantId=?1");
        query.setParameter(1, restaurantId);
        if (query.getResultList().size()==0) {
            transaction.commit();
            return false;
        }
        transaction.commit();
        return true;
    }

    public boolean modifyRestaurant(int id, String restaurantName, String address, String type, String phoneNum) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Restaurant set restaurantName=?1,address=?2,type=?3,phoneNumber=?4 where id=?5");
        query.setParameter(1, restaurantName)
                .setParameter(2, address)
                .setParameter(3, type)
                .setParameter(4, phoneNum)
                .setParameter(5, id);
        query.executeUpdate();
        transaction.commit();
        return true;
    }


    public ArrayList<Restaurant> getUnapprovedApplication() {
        return null;
    }


    public boolean setRestaurantState(int id, int state) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Restaurant set state=?1 where id=?2");
        query.setParameter(1, state)
                .setParameter(2, id);
        query.executeUpdate();
        transaction.commit();
        return true;
    }

    public ArrayList<Restaurant> getRestaurantList(int state) { //state必须是1
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Restaurant where state=?1");
        query.setParameter(1, state);
        ArrayList<Restaurant> restaurants= (ArrayList<Restaurant>)query.getResultList();
        transaction.commit();
        return restaurants;
    }

    public ArrayList<Restaurant> getRestaurantListByType(String type) {
        if (type.equals("全部商家")) {
            return getRestaurantList(1);
        }else{
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Restaurant where type=?1 and state=?2");
            query.setParameter(1, type);
            query.setParameter(2, 1);
            ArrayList<Restaurant> restaurants= (ArrayList<Restaurant>)query.getResultList();
            transaction.commit();
            return restaurants;
        }
    }

    public int getMaxId() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select max(id) from Restaurant ");
        if (query.getResultList().size() == 0 || query.getSingleResult() == null) {
            transaction.commit();
            return 0;
        }
        int maxid = Integer.parseInt(query.getSingleResult().toString());
        transaction.commit();
        return maxid;
    }

    public Restaurant getRestaurantById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Restaurant where id=?1");
        query.setParameter(1, id);
        Restaurant restaurant = (Restaurant) query.getSingleResult();
        System.out.println("数据库！"+restaurant.getRestaurantName());
        transaction.commit();
        return restaurant;
    }

    public int getNum() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select count(*) from Restaurant");
        int result = Integer.parseInt(query.getSingleResult().toString());
        transaction.commit();
        return result;
    }
}
