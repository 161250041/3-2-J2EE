package edu.nju.yummy.Controller;

import com.google.gson.JsonObject;
import edu.nju.yummy.Model.Address;
import edu.nju.yummy.Model.Restaurant;
import edu.nju.yummy.Model.User;
import edu.nju.yummy.Service.RestaurantService;
import edu.nju.yummy.Service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-03-11 01:18
 * @Modified By: hyh
 */
@Controller
@RequestMapping("/restaurantInfo")
public class RestaurantInfo {
    @Autowired
    RestaurantService restaurantService;
    @RequestMapping(value = "/getRestaurantInfo", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getRestaurantInfo(@RequestBody String codejson) {
        JSONObject jsonObject=new JSONObject(codejson);
        String code = jsonObject.getString("code");
        Restaurant restaurant = restaurantService.getRestaurantInfo(code);
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("id",restaurant.getId());
        jsonObject1.addProperty("name",restaurant.getRestaurantName());
        jsonObject1.addProperty("type",restaurant.getType());
        jsonObject1.addProperty("address",restaurant.getAddress());
        jsonObject1.addProperty("phoneNumber",restaurant.getPhoneNumber());
        String jsonstr = jsonObject1.toString();
        return jsonstr;
    }

    @RequestMapping(value = "/modifyRestaurantInfo", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean modifyRestaurantInfo(@RequestBody String json) {
        JSONObject jsonObject=new JSONObject(json);
        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String phoneNumber = jsonObject.getString("phoneNumber");
        String type = jsonObject.getString("type");
        String address = jsonObject.getString("address");
        return restaurantService.modifyInfo(id, name, address, type, phoneNumber);
    }
}
