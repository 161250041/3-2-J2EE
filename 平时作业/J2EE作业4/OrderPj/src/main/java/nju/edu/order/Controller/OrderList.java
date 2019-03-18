package nju.edu.order.Controller;


import nju.edu.order.Action.Business.OrderListBean;
import nju.edu.order.Factory.ServiceFactory;
import nju.edu.order.Service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/orderlist")
public class OrderList extends HttpServlet {
    OrderService orderService = ServiceFactory.getOrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userID=session.getAttribute("user").toString();
            ArrayList<String> validOrders= orderService.getValidOrdersByUserID(userID);
            ArrayList<String> invalidOrders= orderService.getnotValidOrdersByUserID(userID);

            OrderListBean validOrderListBean = new OrderListBean();
            OrderListBean invalidOrderListBean = new OrderListBean();
            validOrderListBean.setOrdersList(validOrders);
            invalidOrderListBean.setOrdersList(invalidOrders);

            ServletContext context = getServletContext();
            session.setAttribute("validOrderListBean", validOrderListBean);
            session.setAttribute("invalidOrderListBean", invalidOrderListBean);
            context.getRequestDispatcher("/OrderList.jsp").forward(request, response);

        }else{
            response.setContentType("text/html");
            response.setCharacterEncoding("GB2312"); //消除response对象通过ISO表编码中文时的乱码（改为通过GB2312码表）
            PrintWriter printWriter = response.getWriter();
            RequestDispatcher dispatcher = request.getRequestDispatcher("signin.html");
            dispatcher.include(request, response);
            printWriter.print("<small style=\"font-family:arial;color:red;font-size:2px;position: absolute;margin-left:20px;\">            请先登陆！<small>");

        }

    }

}