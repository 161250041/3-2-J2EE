package edu.nju.yummy.Controller;

import com.google.gson.Gson;
import com.mysql.cj.util.TimeUtil;
import edu.nju.yummy.Model.CommodityInfo;
import edu.nju.yummy.Service.RestaurantService;
import edu.nju.yummy.Util.TimestampUtil;
import edu.nju.yummy.VO.CommodityVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-03-11 08:54
 * @Modified By: hyh
 */
@Controller
@RequestMapping("/restaurantManager")
public class RestaurantManager {
    @Autowired
    RestaurantService restaurantService;

    @RequestMapping(value = "/getCommodityList", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getCommodityList(@RequestBody String json) {
        System.out.println("商品列表");
        JSONObject jsonObject=new JSONObject(json);
        String code=jsonObject.getString("code");
        System.out.println("code:"+code);
        ArrayList<CommodityInfo> commodityInfos= restaurantService.getCommodityInfo(code); //0不存在 1错误 2成功
        Gson gson = new Gson();
        String jsonstr = gson.toJson(commodityInfos);
        return jsonstr;
    }

    @RequestMapping(value = "/getSetList", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getSetList(@RequestBody String json) {
        System.out.println("套餐列表");
        JSONObject jsonObject=new JSONObject(json);
        String code=jsonObject.getString("code");
        ArrayList<CommodityInfo> commodityInfos= restaurantService.getSetInfo(code);
        Gson gson = new Gson();
        String jsonstr = gson.toJson(commodityInfos);
        return jsonstr;
    }

    @RequestMapping(value = "/addCommodity", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean addCommodity(@RequestBody CommodityVO commodityVO) {
        System.out.println("添加商品");
        String code = commodityVO.getCode();
        String name = commodityVO.getName();
        double price = commodityVO.getPrice();
        int num = commodityVO.getNum();
        Timestamp beginTime = TimestampUtil.getActualTimestamp(commodityVO.getBeginTime());
        Timestamp endTime = TimestampUtil.getActualTimestamp(commodityVO.getEndTime());
        String type = commodityVO.getType();
        restaurantService.releaseCommodityInfo(code, name, price, num, beginTime, endTime, type);
        return true;
    }

//    @RequestMapping(value = "/addSet", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
//    @ResponseBody
//    public boolean addSet(@RequestBody CommodityVO commodityVO) {
//        System.out.println("添加套餐");
//        String code = commodityVO.getCode();
//        String name = commodityVO.getName();
//        double price = commodityVO.getPrice();
//        int num = commodityVO.getNum();
//        Timestamp beginTime = TimestampUtil.getActualTimestamp(commodityVO.getBeginTime());
//        Timestamp endTime = TimestampUtil.getActualTimestamp(commodityVO.getEndTime());
//        String type = commodityVO.getType();
//        restaurantService.releaseCommodityInfo(code, name, price, num, beginTime, endTime, type);
//        return true;
//
//    }
}
