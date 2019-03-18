package edu.nju.yummy.DaoImpl;

import edu.nju.yummy.Dao.OnlineBankingDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-24 15:44
 * @Modified By: hyh
 */
@Repository
public class OnlineBankingDaoImpl implements OnlineBankingDao {
    @Autowired
    protected SessionFactory sessionFactory;

    public double getBalance(String email) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select balance from OnlineBanking where name=?1");
        query.setParameter(1, email);
        double balance = Double.parseDouble(query.getSingleResult().toString());
        transaction.commit();
        return balance;
    }

    public boolean modifyBalance(String name, double money) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        System.out.println(name + money);
        Query query = session.createQuery("update OnlineBanking set balance=balance+?1 where name=?2");
        query.setParameter(1, money);
        query.setParameter(2, name);
        query.executeUpdate();
        transaction.commit();
        return true;
    }

    public String getPassword(String email) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select password from OnlineBanking where name=?1");
        query.setParameter(1, email);
        String password = query.getSingleResult().toString();
        transaction.commit();
        return password;
    }
}
