package nju.edu.order.servlets;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MyListner implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

        Integer onlinenum =(Integer)(httpSessionEvent.getSession().getServletContext().getAttribute("onlinenum")) ;//游客

        if (onlinenum == null) {
            httpSessionEvent.getSession().getServletContext().setAttribute("totalnum", 0);
            httpSessionEvent.getSession().getServletContext().setAttribute("onlinenum", 0);
        }

        String totalstr=httpSessionEvent.getSession().getServletContext().getAttribute("totalnum").toString();

        int num=Integer.parseInt(totalstr);
        httpSessionEvent.getSession().getServletContext().setAttribute("totalnum",num+1);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
    }
}
