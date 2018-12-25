package nju.edu.order.servlets;

import nju.edu.order.DB.CommodityDB;
import nju.edu.order.DB.UserDB;
import nju.edu.order.DBImpl.CommodityDBImpl;
import nju.edu.order.DBImpl.UserDBImpl;
import nju.edu.order.Model.Commodity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/signin")
public class Signin extends HttpServlet {
    UserDB userDB = new UserDBImpl();
    CommodityDB commodityDB = new CommodityDBImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        HttpSession session = request.getSession(false);
//        Cookie cookie = null;
//        Cookie[] cookies = request.getCookies();
//
//        Integer ival = new Integer(1);
//
//        if (null != cookies) {
//            // Look through all the cookies and see if the
//            // cookie with the login info is there.
//            for (int i = 0; i < cookies.length; i++) {
//                cookie = cookies[i];
//                if (cookie.getName().equals("LoginCookie")) {
//                    login=cookie.getValue();
//                    break;
//                }
//            }
//        }
//
//
//        // Logout action removes session, but the cookie remains
//        if (null != request.getParameter("Logout")) {
//            if (null != session) {
//                session.invalidate();
//                session = null;
//            }
//        }
        System.out.println("signinpage");
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        System.out.println("username=" + userID);
        System.out.println("password=" + password);


        response.setContentType("text/html");
        response.setCharacterEncoding("GB2312"); //消除response对象通过ISO表编码中文时的乱码（改为通过GB2312码表）
        PrintWriter printWriter = response.getWriter();

        String result;
        String truePassword = userDB.getPassword(userID);
        RequestDispatcher dispatcher;


        if (truePassword == null) {    //用户不存在
            dispatcher = request.getRequestDispatcher("signin.html");
            dispatcher.include(request, response);
            printWriter.print("<small style=\"font-family:arial;color:red;font-size:2px;position: absolute;margin-left:20px;\">           该用户不存在！<small>");
        } else if(truePassword.equals(password)){

            HttpSession session = request.getSession();
            session.setAttribute("user", userID); // 登录成功，向session存入一个登录标记

            //人数
            ServletContext Context= getServletContext();
            int onlinenum=Integer.parseInt(Context.getAttribute("onlinenum").toString());
            Context.setAttribute("onlinenum",onlinenum+1);

            ArrayList<Commodity> commodities=commodityDB.getCommodityList();
            response.setContentType("text/html");
            response.setCharacterEncoding("GB2312"); //消除response对象通过ISO表编码中文时的乱码（改为通过GB2312码表）

            for (Commodity commodity : commodities) {
                result=commodity.getCommodityID()+","+commodity.getCommodityName()+","+commodity.getCommodityPrice();
                System.out.println(result);
//                printWriter.println("<p>"+result+"<p>");
            }
            dispatcher = request.getRequestDispatcher("commodity.html");
            dispatcher.forward(request, response);
            return ;
        } else{
            dispatcher = request.getRequestDispatcher("signin.html");
            dispatcher.include(request, response);
            printWriter.print("<small style=\"font-family:arial;color:red;font-size:2px;position: absolute;margin-left:20px;\">            密码错误！<small>");

        }

    }

}
