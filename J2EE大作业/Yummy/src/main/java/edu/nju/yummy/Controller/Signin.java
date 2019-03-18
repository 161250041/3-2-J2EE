package edu.nju.yummy.Controller;

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
@RequestMapping("/signin")
public class Signin {

//    UserService userService = ServiceFactory.getUserService();
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public int login(@RequestBody UserVO user) {

        System.out.println("登录");
        System.out.println("邮箱" + user.getEmail());
        System.out.println("用户密码" + user.getPassword());
        int signin = userService.signin(user.getEmail(), user.getPassword());//0代表该用户不存在 1代表密码错误 2代表登陆成功

        return signin;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public int register(@RequestBody UserVO user) {

        System.out.println("注册");
        System.out.println("邮箱" + user.getEmail());
        System.out.println("用户密码" + user.getPassword());
        int result = userService.regist(user.getEmail(), user.getPassword());
        return result;
    }

    @RequestMapping(value = "/active", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean active(@RequestBody String userjson) {

        System.out.println("激活");
        JSONObject jsonObject=new JSONObject(userjson);
        String email=jsonObject.getString("email");
        String code = jsonObject.getString("code");
        System.out.println(email+" "+code);
        boolean result = userService.verify(email,code);
        return result;
    }


//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request,response);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        System.out.println("进入signin页面");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//
//        System.out.println("email=" + email);
//        System.out.println("password=" + password);
//
//
//        response.setContentType("text/html");
//        response.setCharacterEncoding("GB2312"); //消除response对象通过ISO表编码中文时的乱码（改为通过GB2312码表）
//        PrintWriter printWriter = response.getWriter();
//
//        int signin = userService.signin(email, password);//0代表该用户不存在 1代表密码错误 2代表登陆成功
//
//        RequestDispatcher dispatcher;
//
//        if (signin == 0) {    //用户不存在
//            dispatcher = request.getRequestDispatcher("signin.html");
//            dispatcher.include(request, response);
//            printWriter.print("<small style=\"font-family:arial;color:red;font-size:2px;position: absolute;margin-left:20px;\">           该用户不存在！<small>");
//
//        } else if (signin == 2) {
//            HttpSession session = request.getSession();
//            int userId=userService.getUserId(email);
//
//            session.setAttribute("userId", userId); // 登录成功，向session存入一个登录标记
//
//            response.setContentType("text/html");
//            response.setCharacterEncoding("GB2312"); //消除response对象通过ISO表编码中文时的乱码（改为通过GB2312码表）
//
//            ServletContext context = getServletContext();
////            session.setAttribute("commodityListBean", commodityListBean);
//            context.getRequestDispatcher("/CommodityList.jsp").forward(request, response);
//
//        } else {
//            dispatcher = request.getRequestDispatcher("signin.html");
//            dispatcher.include(request, response);
//            printWriter.print("<small style=\"font-family:arial;color:red;font-size:2px;position: absolute;margin-left:20px;\">            密码错误！<small>");
//        }
//
//    }

}
