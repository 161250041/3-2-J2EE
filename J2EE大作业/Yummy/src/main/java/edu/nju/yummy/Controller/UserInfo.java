package edu.nju.yummy.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.nju.yummy.Model.Address;
import edu.nju.yummy.Model.Order;
import edu.nju.yummy.Model.User;
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
@RequestMapping("/userInfo")
public class UserInfo {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getUserInfod(@RequestBody String emailjson) {
        JSONObject jsonObject=new JSONObject(emailjson);
        String email = jsonObject.getString("email");
        User user=userService.getUser(email);

        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("name",user.getUserName());
        jsonObject1.addProperty("phoneNumber",user.getPhoneNumber());
        jsonObject1.addProperty("consumption",user.getConsumption());
        String jsonstr = jsonObject1.toString();
        return jsonstr;
    }

    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean modifyUserInfo(@RequestBody String json) {
        JSONObject jsonObject=new JSONObject(json);
        String email = jsonObject.getString("email");
        String name = jsonObject.getString("name");
        String phoneNumber = jsonObject.getString("phoneNumber");
        JSONArray addresses = jsonObject.getJSONArray("addresses");
        ArrayList<Address> addressArrayList = new ArrayList<Address>();
        User user = userService.getUser(email);
        int position = 0;
        for (int i = 0; i < addresses.length(); i++) {
            JSONObject job = addresses.getJSONObject(i);
            String str = job.get("address").toString();
            Address address = new Address();
            address.setAddress(str);
            address.setUser(user);
            address.setPosition(position);
            position++;
            addressArrayList.add(address);
        }
        return userService.modifyInfo(email, phoneNumber, name, addressArrayList);

//        User user=userService.getUser(email);
//
//        JsonObject jsonObject1 = new JsonObject();
//        jsonObject1.addProperty("name",user.getUserName());
//        jsonObject1.addProperty("phoneNumber",user.getPhoneNumber());
//        jsonObject1.addProperty("consumption",user.getConsumption());
//        String jsonstr = jsonObject1.toString();
//        return true;
    }
}
