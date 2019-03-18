package nju.edu.order.Controller;

import com.google.gson.*;
import nju.edu.order.Factory.ServiceFactory;
import nju.edu.order.Model.CommodityInfo;
import nju.edu.order.Model.Order;
import nju.edu.order.Service.CommodityService;
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
import java.sql.Timestamp;
import java.util.ArrayList;

@WebServlet("/takeorder")
public class TakeOrder extends HttpServlet {
    CommodityService commodityService = ServiceFactory.getCommodityService();
    OrderService orderService = ServiceFactory.getOrderService();
    UserService userService = ServiceFactory.getUserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userID =session.getAttribute("user").toString();

            BufferedReader reader=request.getReader();
            String linestr;
            String jsonstr="";
            while ((linestr=reader.readLine())!=null) {
                jsonstr+=linestr;
            }
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(jsonstr).getAsJsonArray();
            Gson gson = new GsonBuilder().create();

            double sumPrice = 0.0;
            ArrayList<Order> orders = new ArrayList<Order>();
            int orderID= orderService.getSize()+1;
            for (JsonElement element : jsonArray) {
                CommodityInfo commodityInfo = gson.fromJson(element, CommodityInfo.class);
                int id = commodityInfo.getId();
                int num = commodityInfo.getNum();
                double price= commodityService.getPriceByCommodityID(id);
                System.out.println(price);
                System.out.println(num);
                sumPrice += price * (double) num;
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Order tempOrder = new Order(orderID,userID,id,false,timestamp,price*(double)num,price*(double)num,num);
                orders.add(tempOrder);
                orderID++;
            }

            double realprice;
            double discount = 1;
            if(sumPrice<200){
            } else if (sumPrice < 400) {  //200-400 9折
                discount = 0.9;
            } else {                      //>400 8折
                discount = 0.8;
            }
            realprice = sumPrice * discount;

            JsonArray jsonElements = new JsonArray();
            for (Order order : orders) {
                order.setDiscountPrice(discount*order.getDiscountPrice());
                JsonElement jsonElement = gson.toJsonTree(order);
                jsonElements.add(jsonElement);
            }

            orderService.createOrders(orders);

            double balance= userService.getBalance(userID);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("orders", jsonElements);
            jsonObject.addProperty("realprice", realprice);
            jsonObject.addProperty("balance",balance);

            System.out.println(realprice);

            String result=gson.toJson(jsonObject);
            response.setContentType("text/html");
            response.getWriter().write(result);
        }else{
            System.out.println("null");
            response.getWriter().write("null");        }

    }

}
