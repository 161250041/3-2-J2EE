package edu.nju.onlineStock.DaoBean;

import edu.nju.onlineStock.Dao.UserDao;
import edu.nju.onlineStock.Model.User;

import javax.ejb.Stateless;
import javax.persistence.*;


@Stateless
public class UserDaoBean implements UserDao {
    @PersistenceUnit(name = "OnlineStockEJB")
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("OnlineStockEJB");

    @PersistenceContext
    protected EntityManager entityManager;

    public int addUser(String userID, String password) {
        entityManager = factory.createEntityManager();
        if(getPassword(userID)!=null) return 0;//已被注册
        User user = new User(0,userID,password,0);
        entityManager.persist(user);
        entityManager.clear();
        return 1;
    }

    public boolean modifyBalance(String userID, double price) { //+ -
        if(getPassword(userID)==null)return false; //不存在该用户
        entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("Select u from User u where u.userID=:userID");
        query.setParameter("userID", userID);
        User user=(User)query.getSingleResult();
        double balance = user.getBalance() + price;
        user.setBalance(balance);
        entityManager.flush();
        entityManager.clear();
        System.out.println("余额更新成功"+" "+price);

        return true;
    }


    public String getPassword(String userID) {
        entityManager = factory.createEntityManager();
        try {
            Query query = entityManager.createQuery("Select u from User u where u.userID=:userID");
            query.setParameter("userID", userID);
            String password = ((User) query.getSingleResult()).getPassword();
            entityManager.clear();
            return password;
        } catch (Exception e) {
            System.out.println("该用户不存在");
            entityManager.clear();
            return null;
        }

    }

    public double getBalance(String userID) {
        entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("Select u.balance from User u where u.userID=:userID");
        query.setParameter("userID", userID);

        double balance = (double) query.getSingleResult();
        entityManager.clear();
        return balance;
    }
}
