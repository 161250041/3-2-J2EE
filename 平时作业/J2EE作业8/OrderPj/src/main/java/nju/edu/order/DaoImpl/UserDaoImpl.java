package nju.edu.order.DaoImpl;


import nju.edu.order.Dao.UserDao;
import nju.edu.order.Model.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    protected SessionFactory sessionFactory;

    private Session session;

    private UserDaoImpl() {
    }

    public int addUser(String userID, String password) {
        if(getPassword(userID)!=null) return 0;//已被注册
        session = sessionFactory.getCurrentSession();
//        Transaction transaction=session.beginTransaction();
        try {
            session.save(new UserEntity(userID, password, 0));
//            transaction.commit();
            
        } catch (Exception e) {
//            transaction.commit();
            e.printStackTrace();
            return 0;
        }

        return 1;

//        Connection conn = DataBaseUtil.getConnection();
//        String str="insert into User value (?,?,?,?)";
//        try {
//            PreparedStatement ps = conn.prepareStatement(str);
//            ps.setInt(1, 0);
//            ps.setString(2, userID);
//            ps.setString(3, password);
//            ps.setDouble(4, 0);
//            ps.execute();
//            ps.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return 1;
    }

    public boolean modifyBalance(String userID, double price) { //+ -
        if(getPassword(userID)==null)return false; //不存在该用户
        session = sessionFactory.getCurrentSession();
//        Transaction transaction = session.getTransaction();
        Query query = session.createQuery("update UserEntity u set u.balance = u.balance+?1 where u.userID=?2");
        query.setParameter(1, price);
        query.setParameter(2, userID);
        int ret = query.executeUpdate();
//        transaction.commit();
        
        if (ret==1) {
            System.out.println("余额更新成功"+" "+price);
            return true;
        }
        System.out.println("余额更新失败");
        return false;
//        Connection conn = DataBaseUtil.getConnection();
//        String str="update User set balance = balance+? where userID=?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(str);
//            ps.setDouble(1, price);
//            ps.setString(2,userID);
//            ps.execute();
//            ps.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }



    }


    public String getPassword(String userID) {
        session = sessionFactory.getCurrentSession();
        try {
            Query query = session.createQuery("Select u.password from UserEntity u where u.userID=?1");
            query.setParameter(1, userID);
            String password = query.getSingleResult().toString();
            return password;
        } catch (Exception e) {
            System.out.println("该用户不存在");
            return null;
        }
    }

    public double getBalance(String userID) {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("Select u.balance from UserEntity u where u.userID=?1");
        query.setParameter(1, userID);
        double balance = Double.parseDouble(query.getSingleResult().toString());
        return balance;
    }
}
