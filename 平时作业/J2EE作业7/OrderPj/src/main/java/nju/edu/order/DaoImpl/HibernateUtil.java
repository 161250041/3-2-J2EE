package nju.edu.order.DaoImpl;


import nju.edu.order.Model.CommoditiesEntity;
import nju.edu.order.Model.OrdersEntity;
import nju.edu.order.Model.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * 该工具类提供了一个属性：SessionFactory sessionFactory
 * 并创建了sessionFactory 将它设置成static 这样其他程序就可以直接通过此工具类引用
 * 提供了二个方法:
 * 1：通过线程创建Session-->currentSession()
 * 2：关闭Session-->closeSession()
 * 需要在主类中手动关闭sessionFactory
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static final ThreadLocal threadLocal = new ThreadLocal();

    static {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(CommoditiesEntity.class);
        configuration.addAnnotatedClass(OrdersEntity.class);
        configuration.addAnnotatedClass(UserEntity.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }


    public static Session currentSession() {
        Session session = (Session) threadLocal.get();
        if (session == null) {
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }
        return session;

    }

    public static void closeSession() {
        Session session = (Session) threadLocal.get();
        if (session != null) {
            session.close();
            threadLocal.set(null);
        }

    }
}