<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
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

    int i = 0;
%>
<html>
<head>
    <title>我的订单</title>

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
    <h2 style="text-align: center">我的订单</h2>
    <label>${userInfo.username}</label>用户，
    <label>${order.order_no}</label>号订单,创建时间:
    <label>${order.createTime}</label>
    <hr>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>序号</th>
            <th>商品</th>
            <th>单价</th>
            <th>数量</th>
            <th>价格</th>
        </tr>
        <c:forEach items="${order.orderItemSet}" var="orderItem" varStatus="c">
            <tr>
                <td><%=i + 1%>
                </td>
                <td>${orderItem.goods.name}</td>
                <td>${orderItem.goods.price}元</td>
                <td>${orderItem.num}</td>
                <td>${orderItem.price}元</td>
            </tr>

            <% i++; %>
        </c:forEach>

    </table>
    <hr>
    共：<label><%=i%>
</label>条，总共<label>${order.sum_price}</label>元<br>
    <a class="btn btn-default" href="<%=path %>/order/queryHistory.order">返回</a>

</div>
</body>
</html>
