<%@ page import="com.wzy.services.impl.GoodsServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wzy.entity.Goods" %>
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
    int i = 1;
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    GoodsServiceImpl goodsService = new GoodsServiceImpl();
    List<Goods> goodsList = goodsService.queryAllGoods();
    request.setAttribute("goods", goodsList);


%>
<html>
<head>
    <title>商品列表</title>
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
    <h2 style="text-align: center">商品列表</h2>
    <div style="float: right">
        <a class="btn btn-default" style="background-color: #4cae4c;color: white" href="<%=path %>/login.jsp">登录</a>
        <a class="btn btn-primary" href="<%=path %>/register.jsp">注册</a>
    </div>
    <br/>
    <br>
    <div style="text-align: right">
        <%
            if (session.getAttribute("userInfo") != null) {
        %>
        <label id="user-name-label">
            欢迎&nbsp;${userInfo.username}&nbsp;用户
        </label>
        <a href="<%=request.getContextPath()%>/user/exit.user">注销</a>
        <a href="<%=path %>/order/queryHistory.order">历史订单</a>
        <% } %>
    </div>

    <hr>
    <table border="1" class="table table-bordered table-hover">

        <tr class="success">
            <th>序号</th>
            <th>商品</th>
            <th>价格</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${goods}" var="g">
            <tr>
                <td><%=i++%>
                </td>
                <td>${g.name}</td>
                <td>${g.price}</td>
                <td><a class="btn btn-info" onclick="addCart(${g.id})">加入购物车</a></td>

            </tr>
        </c:forEach>

    </table>
    <hr>
    <a class="btn btn-primary" onclick="queryCart()">查看购物车</a>


</div>


<script type="text/javascript">
    function addCart(goods_id) {
        <%
        if (request.getSession().getAttribute("userInfo") == null){
        %>
        window.alert("尚未登录，请先注册或登录！")
        <%
        }else {
            %>

        if (confirm('是否加入购物车？')) {
            window.location.href = "<%=request.getContextPath()%>/cart/addGoods.cart?goods_id=" + goods_id;
        }
        <%
    }
    %>

    }


    function queryCart() {
        <%
        if (request.getSession().getAttribute("userInfo") == null){
        %>
        window.alert("尚未登录，请先注册或登录！")

        <%
        }else {
            %>
        window.location.href = "<%=path %>/cart.jsp";
        <%
    }
    %>

    }


</script>
</body>
</html>
