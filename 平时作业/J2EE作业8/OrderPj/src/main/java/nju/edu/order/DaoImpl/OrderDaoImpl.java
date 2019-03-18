package nju.edu.order.DaoImpl;

import nju.edu.order.Dao.OrderDao;
import nju.edu.order.Model.OrdersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("OrderDao")
public class OrderDaoImpl implements OrderDao {
    @Autowired
    protected SessionFactory sessionFactory;

    private Session session;

    private OrderDaoImpl() {
    }

    public boolean createOrders(ArrayList<OrdersEntity> orders) {
        session = sessionFactory.getCurrentSession();

//        Transaction transaction=session.beginTransaction();
        try {
            for (OrdersEntity order : orders) {
                session.save(order);
            }
//            transaction.commit();
            
        } catch (Exception e) {
//            transaction.commit();
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public ArrayList<OrdersEntity> getValidOrdersByUserID(String userID) {
        session = sessionFactory.getCurrentSession();
        ArrayList<OrdersEntity> orders=new ArrayList<OrdersEntity>();
        try {
            Query query = session.createQuery("from OrdersEntity o where o.userID=?1 and o.isValid=?2");
            query.setParameter(1, userID);
            query.setParameter(2, 1);
            orders= (ArrayList<OrdersEntity>) query.list();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;

//        Connection conn = DataBaseUtil.getConnection();
//        ArrayList<OrdersEntity> orders = new ArrayList<OrdersEntity>();
//        String str = "select * from Orders where userID=? and isValid=?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(str);
//            ps.setString(1, userID);
//            ps.setBoolean(2,true);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                orders.add(new OrdersEntity(rs.getInt(1), rs.getString(2), rs.getInt(3),
//                        rs.getBoolean(4), rs.getTimestamp(5), rs.getDouble(6),
//                        rs.getDouble(7),rs.getInt(8)));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }

    public ArrayList<OrdersEntity> getnotValidOrdersByUserID(String userID) {
        session = sessionFactory.getCurrentSession();
        ArrayList<OrdersEntity> orders=new ArrayList<OrdersEntity>();
        try {
            Query query = session.createQuery("from OrdersEntity o where o.userID=?1 and o.isValid=?2");
            query.setParameter(1, userID);
            query.setParameter(2, 0);
            orders= (ArrayList<OrdersEntity>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    public boolean makeOrderValid(int orderID) {
        session = sessionFactory.getCurrentSession();
//        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update OrdersEntity o set o.isValid = ?1 where orderID=?2");
        query.setParameter(1, 1);
        query.setParameter(2, orderID);
        int ret = query.executeUpdate();
//        transaction.commit();
        return ret==1?true:false;

//
//        Connection conn = DataBaseUtil.getConnection();
//        String str = "update Orders set isValid = ? where orderID=?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(str);
//            ps.setBoolean(1, true);
//            ps.setInt(2, orderID);
//            ps.execute();
//            ps.close();
//            conn.close();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
    }

    public int getSize() {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from OrdersEntity");
        int size = Integer.parseInt(query.getSingleResult().toString());
        return size;

//        Connection conn = DataBaseUtil.getConnection();
//        String str = "select count(1) from Orders";
//        try {
//            PreparedStatement ps = conn.prepareStatement(str);
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                return rs.getInt(1);
//            }
//            rs.close();
//            ps.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return 0;
    }
}
