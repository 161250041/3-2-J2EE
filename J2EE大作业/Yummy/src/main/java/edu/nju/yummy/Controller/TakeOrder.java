package edu.nju.yummy.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.nju.yummy.Service.UserService;
import edu.nju.yummy.Util.TimestampUtil;
import edu.nju.yummy.VO.OrderVO;
import edu.nju.yummy.VO.UserVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-03-10 10:53
 * @Modified By: hyh
 */
@Controller
@RequestMapping("/takeOrder")
public class TakeOrder {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/take", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String takeOrder(@RequestBody OrderVO order) {
        System.out.println("下单");
        System.out.println("邮箱 " + order.getEmail());
        System.out.println("商品 "+order.getCommodity());
        System.out.println("总价:"+order.getPrice());
        System.out.println("addressId:"+order.getAddressId());
        Timestamp timestamp = TimestampUtil.getActualTimestamp(order.getExpectedDeliveryTime());
        System.out.println(timestamp);
        System.out.println(order.getRestaurantId());
        String result=userService.takeOrder(order.getEmail(),order.getPrice(),order.getCommodity(),order.getAddressId(), timestamp,order.getRestaurantId());
        return result;//orderId,actualPrice
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public int pay(@RequestBody String json) {   //0 密码错误  -1 欠费  1 成功

        System.out.println("支付");
        JSONObject jsonObject=new JSONObject(json);
        int orderId=jsonObject.getInt("orderId");
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        System.out.println(orderId+" "+email+" "+password);

        int result = 0;
        if (password.equals(userService.getOBPassword(email))) { //密码正确
            double actualPrice = userService.getOrder(orderId).getActualPrice();
            System.out.println("actualPrice"+actualPrice);
            if (userService.getBalance(email)>=actualPrice) {//余额充足
                result = 1;
                userService.pay(orderId, actualPrice, email);
            }else{
                result = -1;
            }
        }
        return result;
    }

    @RequestMapping(value = "/notPay", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean notPay(@RequestBody String orderidjson) {   //0 密码错误  -1 欠费  1 成功

        System.out.println("取消未支付订单");
        JSONObject jsonObject=new JSONObject(orderidjson);
        int orderId=jsonObject.getInt("orderId");
        System.out.println("orderId"+orderId);
        return userService.cancelNotPaidOrder(orderId);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public double cancelOrder(@RequestBody String orderidjson) {   //0 密码错误  -1 欠费  1 成功

        System.out.println("退款");
        JSONObject jsonObject=new JSONObject(orderidjson);
        int orderId=jsonObject.getInt("orderId");
        System.out.println("orderId"+orderId);

        double refund=userService.cancelOrder(orderId);
        return refund;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean confirmOrder(@RequestBody String orderidjson) {   //0 密码错误  -1 欠费  1 成功
        System.out.println("收货");
        JSONObject jsonObject=new JSONObject(orderidjson);
        int orderId=jsonObject.getInt("orderId");
        System.out.println("orderId"+orderId);
        return userService.confirmReceipt(orderId);
    }
}
