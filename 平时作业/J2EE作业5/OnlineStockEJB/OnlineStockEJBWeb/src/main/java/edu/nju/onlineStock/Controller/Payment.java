package edu.nju.onlineStock.Controller;

import edu.nju.onlineStock.Factory.EJBFactory;
import edu.nju.onlineStock.Model.Order;
import edu.nju.onlineStock.Service.CommodityService;
import edu.nju.onlineStock.Service.OrderService;
import edu.nju.onlineStock.Service.UserService;

import com.google.gson.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/payment")
public class Payment extends HttpServlet {
    UserService userService = (UserService) EJBFactory.getEJB("User");
    OrderService orderService = (OrderService) EJBFactory.getEJB("Order");

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
        userService.modifyBalance(userID, realprice * (-1));

        Gson gson = new GsonBuilder().create();

        for (JsonElement element : jsonArray) {
            Order order = gson.fromJson(element, Order.class);
            System.out.println(order.getOrderID());
            orderService.makeOrderValid(order.getOrderID());
        }
    }

}
