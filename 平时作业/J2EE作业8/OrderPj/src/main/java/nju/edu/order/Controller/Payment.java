package nju.edu.order.Controller;

import com.google.gson.*;
import nju.edu.order.Factory.ServiceFactory;
import nju.edu.order.Model.OrdersEntity;
import nju.edu.order.Service.OrderService;
import nju.edu.order.Service.UserService;

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
    OrderService orderService = ServiceFactory.getOrderService();
    UserService userService = ServiceFactory.getUserService();
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
            OrdersEntity order = gson.fromJson(element, OrdersEntity.class);
            System.out.println(order.getOrderID());
            orderService.makeOrderValid(order.getOrderID());
        }
    }

}
