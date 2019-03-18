package edu.nju.yummy.Controller;

import edu.nju.yummy.Service.RestaurantService;
import edu.nju.yummy.Service.UserService;
import edu.nju.yummy.VO.UserVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/resSignin")
public class RestaurantSignin {
    @Autowired
    RestaurantService restaurantService;

    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public int login(@RequestBody String json) {
        System.out.println("商家登陆");
        JSONObject jsonObject=new JSONObject(json);
        String code=jsonObject.getString("code");
        String password = jsonObject.getString("password");
        System.out.println(code+" "+password);
        int result = restaurantService.signin(code,password); //0不存在 1错误 2成功
        return result;

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String register(@RequestBody String json) {
        System.out.println("商家注册");
        JSONObject jsonObject=new JSONObject(json);
        String name=jsonObject.getString("name");
        String address=jsonObject.getString("address");
        String type=jsonObject.getString("type");
        String phoneNumber=jsonObject.getString("phoneNumber");
        String password = jsonObject.getString("password");
        System.out.println(json);
        String code = restaurantService.register(name,password,address,type,phoneNumber); //0不存在 1错误 2成功
        System.out.println("code:"+code);
        return code;
    }
}
