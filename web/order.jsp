<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.wzy.entity.UserInfo" %>
<%@ page import="com.wzy.entity.OrderItem" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: wangzy
  Date: 2020/9/21
  Time: 8:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    request.setAttribute("userInfo", userInfo);
    request.setAttribute("sum", request.getSession().getAttribute("sum"));
    ArrayList<OrderItem> orderItems = (ArrayList<OrderItem>) request.getSession().getAttribute("orderItems");
    request.setAttribute("orderItems", orderItems);
%>
<html>
<head>
    <title>确认订单</title>

    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- 1. 导入CSS的全局样式 -->
    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="<%=path%>/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="<%=path%>/js/bootstrap.min.js"></script>

    <script type="text/javascript"></script>
</head>
<body>
<div class="container">
    <h2 style="text-align: center">确认订单</h2>

    用户：<label>${userInfo.username}</label><br>
    电话：<label>${userInfo.phone}</label><br>
    地址：<label>${userInfo.address}</label><br>
    <hr>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>序号</th>
            <th>商品</th>
            <th>数量</th>
            <th>价格</th>
        </tr>
        <c:forEach items="${orderItems}" var="orderItem" varStatus="no">
            <tr>
                <td>${no.count}</td>
                <td>${orderItem.goods.name}</td>
                <td>${orderItem.num}</td>
                <td>${orderItem.price}元</td>
            </tr>

        </c:forEach>

    </table>
    <hr>
    总计：<label>${sum}</label>元<br>
    <a class="btn btn-primary" href="<%=path %>/order/addOrder.order">生成订单</a>
    <a class="btn btn-default" href="<%=path %>/cart.jsp">返回</a>

</div>
</body>
</html>
