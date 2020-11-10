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
%>
<html>
<head>
    <title>用户注册</title>

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
<div class="container" style="width: 300px">
    <h2 style="text-align: center">用户注册</h2>
    <hr>
    <form action="<%=request.getContextPath()%>/user/register.user">
        <div class="form-group">
            <label>
                用户名：
            </label>
            <input name="username" type="text" class="form-control" placeholder="请输入用户名" required><br>

            <label>
                密码：
            </label>
            <input name="password" type="password" class="form-control" placeholder="请输入密码" required><br>

            <label>
                电话：
            </label>
            <input name="phone" type="text" class="form-control" placeholder="请输入电话" required><br>

            <label>
                地址：
            </label>
            <input name="address" type="text" class="form-control" placeholder="请输入地址" required><br>
            <div style="color: red">${msg}</div>
            <div align="center">
                <button type="submit" class="btn btn-primary">注册</button>
                <a class="btn btn-default" href="<%=path %>/list.jsp">返回</a>
            </div>
        </div>
    </form>

</div>
</body>
</html>
