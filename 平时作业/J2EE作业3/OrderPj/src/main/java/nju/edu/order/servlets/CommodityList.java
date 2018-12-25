package nju.edu.order.servlets;

import nju.edu.order.DB.CommodityDB;
import nju.edu.order.DBImpl.CommodityDBImpl;
import nju.edu.order.Model.Commodity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/commodityList")
public class CommodityList extends HttpServlet {
    CommodityDB commodityDB = new CommodityDBImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Commodity> commodities=commodityDB.getCommodityList();
        response.setContentType("text/html");
        response.setCharacterEncoding("GB2312"); //消除response对象通过ISO表编码中文时的乱码（改为通过GB2312码表）

        PrintWriter printWriter = response.getWriter();
        for (Commodity commodity : commodities) {
            String result=commodity.getCommodityID()+","+commodity.getCommodityName()+","+commodity.getCommodityPrice();
            System.out.println(result);
            printWriter.println("<p>"+result+"<p>");
        }

    }

}
