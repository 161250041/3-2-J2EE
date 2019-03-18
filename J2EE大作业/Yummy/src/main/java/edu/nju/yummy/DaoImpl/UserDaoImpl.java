package edu.nju.yummy.DaoImpl;

import edu.nju.yummy.Dao.UserDao;
import edu.nju.yummy.Model.Address;
import edu.nju.yummy.Model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-23 15:52
 * @Modified By: hyh
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    protected SessionFactory sessionFactory;

    public boolean userExist(String email) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select count(*) from User where email=?1");
        query.setParameter(1, email);
        int count = Integer.parseInt(query.getSingleResult().toString());
        transaction.commit();
        return count> 0;
    }

    public boolean addUser(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setConsumption(0.0);
        session.save(user);
        transaction.commit();
        return true;
    }

    public String getPassword(String email) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select password from User where email=?1");
        query.setParameter(1, email);
        String password = query.getSingleResult().toString();
        transaction.commit();
        return password;
    }


    public boolean modifyUser(String email, String phoneNumber, String name, ArrayList<Address> addresses) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        for (Address address : addresses) {
            System.out.println(address.getPosition());
        }
        Query query = session.createQuery("select u from Address a,User u where a.user.id=u.userId and email=?1");
        query.setParameter(1, email);
        if (query.getResultList().size() == 0) {
            transaction.commit();
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            Query query1 = session.createQuery("from User u where email=?1");
            query1.setParameter(1, email);
            User user = (User) query1.getSingleResult();
            user.setPhoneNumber(phoneNumber);
            user.setUserName(name);
            user.getAddressSet().clear();
            user.getAddressSet().addAll(addresses);
            session.merge(user);
            transaction.commit();
        }else{
            User user = (User)query.getSingleResult();
            user.setPhoneNumber(phoneNumber);
            user.setUserName(name);
            user.getAddressSet().clear();
            user.getAddressSet().addAll(addresses);
            session.merge(user);
//        session.flush();
            transaction.commit();
        }
        return true;
    }

    public int getUserIdByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select userId from User where email=?1");
        query.setParameter(1, email);
        int userId = Integer.parseInt(query.getSingleResult().toString());
        transaction.commit();
        return userId;
    }

    public User getUserByEmail(String email) {
        System.out.println(email);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
//        Query query = session.createQuery("from User where email=?1");
        Query query = session.createQuery("select u from Address a,User u where a.user.id=u.userId and email=?1");
//        Query query = session.createQuery("from Address s left outer join s.user_id where s.user_id.id=?");
        query.setParameter(1, email);
        if(query.getResultList().size()==0){
            transaction.commit();
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            Query query1 = session.createQuery("from User u where email=?1");
            query1.setParameter(1, email);
            User user = (User) query1.getSingleResult();
            transaction.commit();
            user.setAddressSet(new ArrayList<Address>());
            return user;
        }else{
            User user = (User)query.getSingleResult();
            System.out.println(user.getEmail());
            System.out.println(user.getAddressSet().size());
            transaction.commit();
            return user;
        }

    }

    public boolean addConsumption(String email, double money) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update User set consumption=consumption+?1 where email=?2");
        query.setParameter(1, money);
        query.setParameter(2, email);
        query.executeUpdate();
        transaction.commit();
        return true;
    }

    public String getAddressByAddressId(int addressId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select address from Address where addressId=?1");
        query.setParameter(1, addressId);
        String address = "";
        if (query.getResultList().size() != 0) {
            address= query.getSingleResult().toString();
        }
        transaction.commit();
        return address;
    }

    public int getNum() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select count(*) from User");
        int result = Integer.parseInt(query.getSingleResult().toString());
        transaction.commit();
        return result;
    }

    public double getConsumption(String email) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select consumption from User where email=?1");
        query.setParameter(1, email);
        double result = Double.parseDouble(query.getSingleResult().toString());
        transaction.commit();
        return result;
    }


}
