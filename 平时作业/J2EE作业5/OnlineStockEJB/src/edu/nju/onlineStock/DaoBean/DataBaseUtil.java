package edu.nju.onlineStock.DaoBean;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

public class DataBaseUtil {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/J2EEOrderPj";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "12345678";
    public static Connection getConnection() {
        Connection conn = null;
        Properties properties = new Properties();
//            properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        InitialContext initialContext = null;
        try {
            initialContext = new InitialContext(properties);
            DataSource dataSource = (DataSource) initialContext.lookup("java:/MySqlDS");
            if (conn == null) {
                conn = dataSource.getConnection();
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        // 注册 JDBC 驱动器
//        try {
//            Class.forName(JDBC_DRIVER);
//            if (conn == null) {
//                //连接
//                conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            }
//        } catch (ClassNotFoundException e1) {
//            e1.printStackTrace();
//        } catch (SQLException e) {
//            // 处理 JDBC 错误
//            e.printStackTrace();
//        }
        return conn;
    }
//
//    public static void closeConnection(Connection conn){
//        if(conn!=null){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void closeStatement(Statement statement){
//        if(statement!=null){
//            try {
//                statement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
