<%@ page import="java.util.ArrayList" %>
<%@ page import="edu.nju.onlineStock.Action.OrderListBean" %>
<%--
  Created by IntelliJ IDEA.
  User: hyh
  Date: 2019-01-05
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单列表</title>
</head>
<body>
    <button onclick="window.location.href='CommodityList.jsp'">返回商品列表</button>
    <p>==================有效订单=========================</p>
    <%
        ArrayList<String> validOrderList = ((OrderListBean) session.getAttribute("validOrderListBean")).getOrdersList();
        ArrayList<String> invalidOrderList = ((OrderListBean) session.getAttribute("invalidOrderListBean")).getOrdersList();
        for(String string:validOrderList) {

    %>
    <p><%=string%></p>
    <%
        }
    %>
    <p>==================无效订单=========================</p>
    <%
        for(String string:invalidOrderList) {

    %>
    <p><%=string%></p>
    <%
        }
    %>
</body>
</html>
