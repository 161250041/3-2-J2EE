package edu.nju.yummy.DaoImpl;

import edu.nju.yummy.Dao.VerificationDao;
import edu.nju.yummy.Model.Verification;
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
 * @Date: 2019-02-23 16:25
 * @Modified By: hyh
 */
@Repository
public class VerificationDaoImpl implements VerificationDao {
    @Autowired
    protected SessionFactory sessionFactory;

    public boolean emailExist(String email) {
        System.out.println("好好好");
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select count(*) from Verification where email=?1");
        query.setParameter(1, email);
        int count = Integer.parseInt(query.getSingleResult().toString());
        transaction.commit();
        return  count> 0;
    }

    public boolean addVerification(String email, String password, String code, int state) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Verification verification = new Verification();
        verification.setEmail(email);
        verification.setPassword(password);
        verification.setState(0);
        verification.setCode(code);

        session.save(verification);
        transaction.commit();
        return true;
    }

    public boolean modifyVerification(String email, String password, String code, int state) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Verification set code=?1,password=?2,state=?3 where email=?4");
        query.setParameter(1, code);
        query.setParameter(2, password);
        query.setParameter(3, state);
        query.setParameter(4, email);
        query.executeUpdate();
        transaction.commit();
        setState(email, state);
        return true;
    }

    public Verification getVerification(String email) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Verification where email=?1");
        query.setParameter(1, email);
        Verification verification=(Verification)query.getSingleResult();
        transaction.commit();
        return verification;
    }

    public boolean setState(String email, int state) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Verification set state=?1 where email=?2");
        query.setParameter(1, state);
        query.setParameter(2, email);
        query.executeUpdate();
        transaction.commit();
        return true;
    }
}
