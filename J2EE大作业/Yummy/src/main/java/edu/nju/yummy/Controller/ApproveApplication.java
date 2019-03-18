package edu.nju.yummy.Controller;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.nju.yummy.Model.Restaurant;
import edu.nju.yummy.Service.ManagerService;
import edu.nju.yummy.Service.RestaurantService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/approveApplication")
public class ApproveApplication {
    @Autowired
    ManagerService managerService;

    @RequestMapping(value = "/getUnApprovedInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getUnApprovedInfo() {

        ArrayList<Restaurant> restaurants = managerService.getUnapprovedApplication();
        Gson gson = new Gson();
        String jsonstr = gson.toJson(restaurants);
        return jsonstr;
    }

    @RequestMapping(value = "/approve", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean approve(@RequestBody String json) {
        JSONObject jsonObject = new JSONObject(json);
        int id=jsonObject.getInt("id");
        return managerService.approveApplication(id);
    }

    @RequestMapping(value = "/reject", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean reject(@RequestBody String json) {
        JSONObject jsonObject = new JSONObject(json);
        int id=jsonObject.getInt("id");
        return managerService.rejectApplication(id);
    }
}