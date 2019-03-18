package nju.edu.order.DaoImpl;


import nju.edu.order.Dao.UserDao;
import nju.edu.order.Model.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jpa.HibernateQuery;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private static UserDaoImpl userDao=new UserDaoImpl();
    private Session session;

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance(){
        return userDao;
    }

    public int addUser(String userID, String password) {
        if(getPassword(userID)!=null) return 0;//已被注册
        session = HibernateUtil.currentSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.save(new UserEntity(userID, password, 0));
            transaction.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            transaction.commit();
            HibernateUtil.closeSession();
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
        session = HibernateUtil.currentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update UserEntity u set u.balance = u.balance+?1 where u.userID=?2");
        query.setParameter(1, price);
        query.setParameter(2, userID);
        int ret = query.executeUpdate();
        transaction.commit();
        HibernateUtil.closeSession();
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
        session = HibernateUtil.currentSession();
        try {
            Query query = session.createQuery("Select u.password from UserEntity u where u.userID=?1");
            query.setParameter(1, userID);
            String password = query.getSingleResult().toString();
            HibernateUtil.closeSession();
            return password;
        } catch (Exception e) {
            System.out.println("该用户不存在");
            HibernateUtil.closeSession();
            return null;
        }

//        Connection conn = DataBaseUtil.getConnection();
//        try {
//            String str = "select password from User where userID=?";
//            PreparedStatement ps = conn.prepareStatement(str);
//            ps.setString(1, userID);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                return rs.getString(1);
//            }
//            // 完成后关闭
//            rs.close();
//            ps.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    public double getBalance(String userID) {
        session = HibernateUtil.currentSession();
        Query query = session.createQuery("Select u.balance from UserEntity u where u.userID=?1");
        query.setParameter(1, userID);
        double balance = Double.parseDouble(query.getSingleResult().toString());
        HibernateUtil.closeSession();
        return balance;
    }
}
