package edu.nju.onlineStock.Controller;

import edu.nju.onlineStock.Action.CommodityListBean;
import edu.nju.onlineStock.Factory.EJBFactory;
import edu.nju.onlineStock.Model.Commodity;
import edu.nju.onlineStock.Service.CommodityService;

import edu.nju.onlineStock.Service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/signin")
public class Signin extends HttpServlet {
    UserService userService = (UserService) EJBFactory.getEJB("User");
    CommodityService commodityService = (CommodityService) EJBFactory.getEJB("Commodity");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession edu.nju.order = request.getSession(false);
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
//        // Logout action removes edu.nju.order, but the cookie remains
//        if (null != request.getParameter("Logout")) {
//            if (null != edu.nju.order) {
//                edu.nju.order.invalidate();
//                edu.nju.order = null;
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

        int signin = userService.signin(userID, password);

        RequestDispatcher dispatcher;

        if (signin == 0) {    //用户不存在
            dispatcher = request.getRequestDispatcher("/signin.html");
            dispatcher.include(request, response);
            printWriter.print("<small style=\"font-family:arial;color:red;font-size:2px;position: absolute;margin-left:20px;\">           该用户不存在！<small>");
            return;

        } else if (signin == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("user", userID); // 登录成功，向session存入一个登录标记

            //人数
            ServletContext Context = getServletContext();
            int onlinenum = Integer.parseInt(Context.getAttribute("onlinenum").toString());
            Context.setAttribute("onlinenum", onlinenum + 1);

            response.setContentType("text/html");
            response.setCharacterEncoding("GB2312"); //消除response对象通过ISO表编码中文时的乱码（改为通过GB2312码表）

            CommodityListBean commodityListBean = new CommodityListBean();
            commodityListBean.setCommodityList(commodityService.getCommodityList());

            //控制台输出
            ArrayList<Commodity> commodities = commodityListBean.getCommodityList();
            for (Commodity commodity : commodities) {
                System.out.println(commodity.getCommodityName());
            }

            ServletContext context = getServletContext();
            session.setAttribute("commodityListBean", commodityListBean);
            context.getRequestDispatcher("/CommodityList.jsp").forward(request, response);

            return;
        } else {
            dispatcher = request.getRequestDispatcher("/signin.html");
            dispatcher.include(request, response);
            printWriter.print("<small style=\"font-family:arial;color:red;font-size:2px;position: absolute;margin-left:20px;\">            密码错误！<small>");
            return;
        }

    }

}
