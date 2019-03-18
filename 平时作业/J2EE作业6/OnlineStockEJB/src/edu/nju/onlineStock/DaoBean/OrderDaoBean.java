package edu.nju.onlineStock.DaoBean;

import edu.nju.onlineStock.Dao.OrderDao;
import edu.nju.onlineStock.Model.Commodity;
import edu.nju.onlineStock.Model.Order;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Stateless
public class OrderDaoBean implements OrderDao {
    @PersistenceUnit(name = "OnlineStockEJB")
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("OnlineStockEJB");

    @PersistenceContext
    protected EntityManager entityManager;

    public boolean createOrders(ArrayList<Order> orders) {
        entityManager = factory.createEntityManager();
        try {
            for (Order order : orders) {
                entityManager.persist(order);
            }
            entityManager.flush();
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.clear();
            return false;
        }
        return true;

    }

    public ArrayList<Order> getValidOrdersByUserID(String userID) {
        entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("select o from Order o where o.userID=:userID and o.isValid=:isValid");
        query.setParameter("userID", userID);
        query.setParameter("isValid", true);
        ArrayList<Order> orders = (ArrayList<Order>) query.getResultList();
        entityManager.clear();

        return orders;
    }

    public ArrayList<Order> getnotValidOrdersByUserID(String userID) {
        entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("select o from Order o where o.userID=:userID and o.isValid=:isValid");
        query.setParameter("userID", userID);
        query.setParameter("isValid", false);
        ArrayList<Order> orders = (ArrayList<Order>) query.getResultList();
        entityManager.clear();
        return orders;
    }

    public boolean makeOrderValid(int orderID) {
        entityManager = factory.createEntityManager();
        Order order = entityManager.find(Order.class, orderID);
        order.setValid(true);
        try{
            entityManager.flush();
//            entityManager.merge(order);
            entityManager.clear();
        }catch(Exception e){
            e.printStackTrace();
            entityManager.clear();
            return false;
        }
        return true;
    }

    public int getSize() {
        entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("select count(o) from Order o");
        int size = Integer.parseInt(query.getSingleResult().toString()) ;
        return size;
    }
}
