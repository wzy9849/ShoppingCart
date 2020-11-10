<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wangzy
  Date: 2020/9/21
  Time: 8:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

    /*UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    OrderService orderService = new OrderServiceImpl();
    List<Order> orderList = orderService.queryHistoryOrder(userInfo);
    request.setAttribute("orderList",orderList);*/

%>
<html>
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>历史订单</title>
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
    <h2 style="text-align: center">历史订单</h2>
    <hr>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>序号</th>
            <th>订单号</th>
            <th>创建时间</th>
        </tr>

        <c:forEach items="${orderList}" var="order" varStatus="c">
        <tr>
            <td>${c.count}</td>
            <td><a href="<%=path%>/order/queryDetail.order?order_id=${order.id}">${order.order_no}</a></td>
            <td>${order.createTime}</td>

            </c:forEach>
    </table>
    <hr>
    <a class="btn btn-default" href="<%=path %>/list.jsp">返回</a>
</div>

</body>
</html>
