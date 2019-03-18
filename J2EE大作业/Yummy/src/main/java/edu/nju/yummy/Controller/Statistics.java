package edu.nju.yummy.Controller;

import com.google.gson.*;
import edu.nju.yummy.Model.Order;
import edu.nju.yummy.Model.User_ResStatistic;
import edu.nju.yummy.Service.ManagerService;
import edu.nju.yummy.Service.RestaurantService;
import edu.nju.yummy.Service.UserService;
import edu.nju.yummy.VO.ResTimeVO;
import edu.nju.yummy.VO.UserTimeVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.ArrayList;

@Controller
@RequestMapping("/statistics")
public class Statistics {
    @Autowired
    UserService userService;
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    ManagerService managerService;

    @RequestMapping(value = "/getRestStatistics", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getRestStatistics(@RequestBody ResTimeVO time) {
        Timestamp beginTime = time.getBeginTime();
        Timestamp endTime = time.getEndTime();
        System.out.println(beginTime+" "+endTime);
        int id = time.getId();
        ArrayList<Order> orders=restaurantService.getOrderListByTime(id, beginTime, endTime);
        JsonArray jsonElements = new JsonArray();
        String[] states={"成功","过期未支付","已退款"};//0待支付 1已支付在配送 2配送送达（成功） 3过期未支付 4已退款
        for (Order order : orders) {
            int state = order.getState();
            if(state>1){
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("state", states[state - 2]);
                jsonObject.addProperty("orderId",order.getOrderId());
                jsonObject.addProperty("email",order.getEmail());
                jsonObject.addProperty("commodities",order.getCommoditys());
                jsonObject.addProperty("beginTime",order.getBeginTime().toString());
                jsonObject.addProperty("actualPrice",order.getActualPrice());
                jsonElements.add(jsonObject);
            }
        }
        Gson gson = new Gson();
        String result = gson.toJson(jsonElements);
        System.out.println(result);
        return result;
    }

    @RequestMapping(value = "/getUserStatistics", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getUserStatistics(@RequestBody UserTimeVO time) {
        Timestamp beginTime = time.getBeginTime();
        Timestamp endTime = time.getEndTime();
        String email= time.getEmail();
        ArrayList<Order> orders=userService.getOrderListByTime(email, beginTime, endTime);
        JsonArray jsonElements = new JsonArray();
        String[] states={"成功","过期未支付","已退款"};//0待支付 1已支付在配送 2配送送达（成功） 3过期未支付 4已退款
        for (Order order : orders) {
            int state = order.getState();
            if(state>1){
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("state", states[state - 2]);
                jsonObject.addProperty("orderId",order.getOrderId());
                String restaurantName=userService.getRestaurantNameById(order.getRestaurantId());
                jsonObject.addProperty("restaurant",restaurantName);
                jsonObject.addProperty("commodities",order.getCommoditys());
                jsonObject.addProperty("beginTime",order.getBeginTime().toString());
                jsonObject.addProperty("actualPrice",order.getActualPrice());
                jsonElements.add(jsonObject);
            }
        }
        Gson gson = new Gson();
        ArrayList<User_ResStatistic> statistics = userService.getUserStatisticsByRestaurant(email, beginTime, endTime);
        JsonObject jsonObject1 = new JsonObject();
        JsonElement jsonElement = gson.toJsonTree(statistics);
        jsonObject1.add("list1", jsonElements);
        jsonObject1.add("list2", jsonElement);
        System.out.println(gson.toJson(jsonObject1));
        return  gson.toJson(jsonObject1);
    }

    @RequestMapping(value = "/getManagerStatistics", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getManagerStatistics(@RequestBody String s) {
        ArrayList<Double> arrayList=managerService.getProfit();
        Gson gson = new Gson();
        System.out.println(gson.toJson(arrayList));
        return gson.toJson(arrayList);
    }

    @RequestMapping(value = "/getResNum", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public int getResNum(@RequestBody String s) {

        return managerService.getResNum();
    }

    @RequestMapping(value = "/getUserNum", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public int getUserNum(@RequestBody String s) {
        return managerService.getUserNum();
    }
}
