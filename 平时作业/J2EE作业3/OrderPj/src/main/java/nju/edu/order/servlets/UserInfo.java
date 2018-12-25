package nju.edu.order.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/userinfo")
public class UserInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext Context= getServletContext();
        PrintWriter printWriter = response.getWriter();
        int onlinenum=Integer.parseInt(Context.getAttribute("onlinenum").toString());
        int totalnum=Integer.parseInt(Context.getAttribute("totalnum").toString());
        printWriter.write("在线人数：" + onlinenum + " 游客人数：" + (totalnum-onlinenum) + " 总人数:" + totalnum);

    }

}
