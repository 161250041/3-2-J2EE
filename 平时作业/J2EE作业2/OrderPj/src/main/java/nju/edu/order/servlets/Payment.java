package nju.edu.order.servlets;

import com.google.gson.*;
import nju.edu.order.DB.CommodityDB;
import nju.edu.order.DB.OrderDB;
import nju.edu.order.DB.UserDB;
import nju.edu.order.DBImpl.CommodityDBImpl;
import nju.edu.order.DBImpl.OrderDBImpl;
import nju.edu.order.DBImpl.UserDBImpl;
import nju.edu.order.Model.CommodityInfo;
import nju.edu.order.Model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

@WebServlet("/payment")
public class Payment extends HttpServlet {
    CommodityDB commodityDB = new CommodityDBImpl();
    OrderDB orderDB = new OrderDBImpl();
    UserDB userDB = new UserDBImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String userID =session.getAttribute("user").toString();

        BufferedReader reader=request.getReader();
        String linestr;
        String jsonstr="";
        while ((linestr=reader.readLine())!=null) {
            jsonstr+=linestr;
        }

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonstr).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("orders");

        double realprice = jsonObject.get("realprice").getAsDouble();
        System.out.println(realprice);
        userDB.modifyBalance(userID, realprice * (-1));

        Gson gson = new GsonBuilder().create();

        for (JsonElement element : jsonArray) {
            Order order = gson.fromJson(element, Order.class);
            System.out.println(order.getOrderID());
            orderDB.makeOrderValid(order.getOrderID());
        }
    }

}
