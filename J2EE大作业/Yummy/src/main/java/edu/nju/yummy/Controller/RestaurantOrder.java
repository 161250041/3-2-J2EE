package edu.nju.yummy.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.nju.yummy.Model.Order;
import edu.nju.yummy.Model.Restaurant;
import edu.nju.yummy.Service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/restaurantOrder")
public class RestaurantOrder {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/getRestaurantOrder",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getRestaurantOrder(@RequestBody String codejson) {
        JSONObject jsonObject=new JSONObject(codejson);
        String code=jsonObject.getString("code");
        ArrayList<Order> orders= userService.getOrderListByRestaurantCode(code);

        Gson gson = new Gson();
        JsonArray jsonElements = new JsonArray();
        for (Order order : orders) {
            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("orderId", order.getOrderId());
            jsonObject1.addProperty("commodities", order.getCommoditys());
            jsonObject1.addProperty("price", order.getPrice());
            jsonObject1.addProperty("actualPrice", order.getActualPrice());
//            jsonObject1.addProperty("address",a);
            String address=userService.getAddressByAddressId(order.getAddressId());
            if (address.equals("")) {
                address = "地址";
            }
            System.out.println(order.getRestaurantId());
            String restaurantName=userService.getRestaurantNameById(order.getRestaurantId());
            jsonObject1.addProperty("address", address);
            jsonObject1.addProperty("user", order.getEmail());
            jsonObject1.addProperty("refund", order.getRefund());
            jsonObject1.addProperty("beginTime",order.getBeginTime()==null?"":order.getBeginTime().toString());
            jsonObject1.addProperty("endTime", order.getActualDeliveryTime()==null?"":order.getActualDeliveryTime().toString());
            jsonObject1.addProperty("state", order.getState());
            jsonElements.add(jsonObject1);
        }

        String jsonstr = gson.toJson(jsonElements);
        System.out.println(jsonstr);
        return jsonstr;

    }
}
