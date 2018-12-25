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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    UserDB userDB = new UserDBImpl();
    CommodityDB commodityDB = new CommodityDBImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;

        // Logout action removes session
        if (null != session) {
            session.invalidate();
            session = null;
        }

        //人数
        ServletContext Context= getServletContext();

        int onlinenum=Integer.parseInt(Context.getAttribute("onlinenum").toString());
        Context.setAttribute("onlinenum",onlinenum-1);
        int totalnum=Integer.parseInt(Context.getAttribute("totalnum").toString());
        Context.setAttribute("totalnum",totalnum-1);

        dispatcher = request.getRequestDispatcher("signin.html");
        dispatcher.forward(request, response);
        System.out.println("logout!!");








    }
}