package edu.nju.yummy.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.nju.yummy.Model.Address;
import edu.nju.yummy.Model.CommodityInfo;
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
@RequestMapping("/restaurant")
public class RestaurantList {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/restaurantList",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getRestaurantList(@RequestBody String typejson) {
        JSONObject jsonObject=new JSONObject(typejson);
        String type=jsonObject.getString("type");
        ArrayList<Restaurant> restaurants= userService.getRestaurantListByType(type);
        Gson gson = new Gson();
        String jsonstr = gson.toJson(restaurants);
//        System.out.println(jsonstr);
        return jsonstr;
    }

    @RequestMapping(value = "/commodityList",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getCommodityList(@RequestBody String idjson) {
        JSONObject jsonObject=new JSONObject(idjson);
        int id = jsonObject.getInt("id");
        ArrayList<CommodityInfo> commodityInfos=userService.getCommodityList(id);
        Gson gson = new Gson();
        String jsonstr = gson.toJson(commodityInfos);
        System.out.println(jsonstr);
        return jsonstr;
    }

    @RequestMapping(value = "/commodityTypeList",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getCommodityTypeList(@RequestBody String idjson) {
        JSONObject jsonObject=new JSONObject(idjson);
        int id=jsonObject.getInt("id");//商户的id
        //取得当前时间有效的商品的类型列表 1是餐厅id对得上 2是需要时间符合
        ArrayList<String> types=userService.getCommodityTypeList(id);
        Gson gson = new Gson();
        String jsonstr = gson.toJson(types);
//        System.out.println(jsonstr);
        return jsonstr;
    }

    @RequestMapping(value = "/addressList",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getAddressList(@RequestBody String emailjson) {
        JSONObject jsonObject=new JSONObject(emailjson);
        String email=jsonObject.getString("email");//商户的id
        //取得当前时间有效的商品的类型列表 1是餐厅id对得上 2是需要时间符合
        ArrayList<Address> addresses=userService.getAddressList(email);

        Gson gson = new Gson();
        JsonArray jsonElements = new JsonArray();
        for (Address address : addresses) {
            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("addressId", address.getAddressId());
            jsonObject1.addProperty("address", address.getAddress());
            jsonElements.add(jsonObject1);
        }

        String jsonstr = gson.toJson(jsonElements);
        System.out.println(jsonstr);
        return jsonstr;
    }
}
