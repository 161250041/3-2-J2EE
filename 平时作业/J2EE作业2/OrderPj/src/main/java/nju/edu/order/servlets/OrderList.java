package nju.edu.order.servlets;


import nju.edu.order.DB.CommodityDB;
import nju.edu.order.DB.OrderDB;
import nju.edu.order.DBImpl.CommodityDBImpl;
import nju.edu.order.DBImpl.OrderDBImpl;
import nju.edu.order.Model.Commodity;
import nju.edu.order.Model.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/orderlist")
public class OrderList extends HttpServlet {
    CommodityDB commodityDB = new CommodityDBImpl();
    OrderDB orderDB = new OrderDBImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userID=session.getAttribute("user").toString();
            ArrayList<Order> validOrders=orderDB.getValidOrdersByUserID(userID);
            ArrayList<Order> notValidOrders=orderDB.getnotValidOrdersByUserID(userID);

            response.setContentType("text/html");
            response.setCharacterEncoding("GB2312"); //消除response对象通过ISO表编码中文时的乱码（改为通过GB2312码表）

            PrintWriter printWriter = response.getWriter();

            printWriter.write("<button onclick=\"window.location.href='commodity.html'\">返回商品列表</button>");
            printWriter.write("==================有效订单=========================");
            for (Order order : validOrders) {

                int commodityID=order.getCommodityID();
                Commodity commodity=commodityDB.getCommodityByCommodityID(commodityID);

                String result = "订单号：" + order.getOrderID()
                        + "||商品名称：" + commodity.getCommodityName() + " 商品单价：" + commodity.getCommodityPrice() + " 购买数量：" + order.getNum()
                        +"||订单总价："+order.getDiscountPrice()+" <S>"+order.getPrice()+"</S>";
                printWriter.write("<p>"+result+"<p>");
            }
            printWriter.write(" ");
            printWriter.write("============================无效订单=========================");
            for (Order order : notValidOrders) {
                int commodityID=order.getCommodityID();
                Commodity commodity=commodityDB.getCommodityByCommodityID(commodityID);

                String result = "订单号：" + order.getOrderID()
                        + "||商品名称：" + commodity.getCommodityName() + " 商品单价：" + commodity.getCommodityPrice() + " 购买数量：" + order.getNum()
                        +"||订单总价："+order.getDiscountPrice()+" <S>"+order.getPrice()+"</S>";
                printWriter.write("<p>"+result+"<p>");
            }
        }else{
            response.setContentType("text/html");
            response.setCharacterEncoding("GB2312"); //消除response对象通过ISO表编码中文时的乱码（改为通过GB2312码表）
            PrintWriter printWriter = response.getWriter();
            RequestDispatcher dispatcher = request.getRequestDispatcher("signin.html");
            dispatcher.include(request, response);
            printWriter.print("<small style=\"font-family:arial;color:red;font-size:2px;position: absolute;margin-left:20px;\">            请先登陆！<small>");

        }

    }

}