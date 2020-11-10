<%@ page import="com.wzy.entity.Goods" %>
<%@ page import="com.wzy.services.impl.GoodsServiceImpl" %>
<%@ page import="java.util.*" %>
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

    HashMap<Integer, Integer> cart = null;
    cart = (HashMap<Integer, Integer>) request.getSession().getAttribute("cart");
    if (cart == null) {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("<h2 style=\"text-align: center;\">我的购物车</h2>" +
                "  <font style=\"text-align: center;size:24\">当前购物车为空,自动返回......");
        response.setHeader("refresh", "2,url=" + path + "/list.jsp");
    }
//    List<Integer> keyList = new ArrayList<Integer>();
    List<Goods> goodsList = new ArrayList<Goods>();
    List<Integer> numList = new ArrayList<Integer>();

    GoodsServiceImpl goodsService = new GoodsServiceImpl();
    for (Integer key :
            cart.keySet()) {
//        keyList.add(key);
        numList.add(cart.get(key));
        goodsList.add(goodsService.queryGoodsById(key));
    }
    request.setAttribute("myCart", goodsList);
    int i = 0;
%>
<html>
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>我的购物车</title>

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
    <h2 style="text-align: center;">我的购物车</h2>
    <hr>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>
                <input type="checkbox" id="all_ck" onclick="allSelect()">
                全选
            </th>
            <th>序号</th>
            <th>商品</th>
            <th>数量</th>
            <th>单价</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${myCart}" var="g" varStatus="no">
            <tr>
                <td><input type="checkbox" name="cbs" value="${g.id}" onclick="sum()"></td>
                <td>${no.count}</td>
                <td>${g.name}</td>
                <td><input size="2" id="num_${g.id}" type="text" value="<%=numList.get(i)%>"></td>
                <td>${g.price}</td>
                <script>
                    var price = ${g.price};
                    var num = <%=numList.get(i)%>;
                    var each_sum = price * num;
                    localStorage.setItem("${g.id}", each_sum);
                </script>

                <td>
                    <a class="btn btn-info btn-sm" onclick="deleteGoods(${g.id})">删除</a>
                    <a class="btn btn-info btn-sm" onclick="updateGoods(${g.id})">修改</a>
                </td>
            </tr>
            <% i++; %>
        </c:forEach>
    </table>

    <hr>
    当前选中商品总价：<label id="goods-sum-label">
    0
</label>元<br>
    <a class="btn btn-default" onclick="deleteSomeGoods()">删除选中项</a>
    <a class="btn btn-default" onclick="clearCart()">清空购物车</a>
    <a class="btn btn-default" href="<%=path %>/list.jsp">继续购物</a>
    <a class="btn btn-primary" onclick="settle()">结算</a>
</div>

<script type="text/javascript">
    function allSelect() {
        var all_ck = document.getElementById("all_ck");
        var checkboxs = document.getElementsByName("cbs");
        var isChecked = all_ck.checked;
        for (var i = 0; i < checkboxs.length; i++) {
            checkboxs[i].checked = isChecked;
        }
        sum();
    }

    function sum() {
        var checkboxs = document.getElementsByName("cbs");
        var ckitems = [];
        var price = 0, num = 0;

        for (var i = 0; i < checkboxs.length; i++) {
            if (checkboxs[i].checked) {
                ckitems[num] = checkboxs[i].value;
                num++;
            }
        }

        if (num < checkboxs.length) {
            document.getElementById("all_ck").checked = false;
        } else {
            document.getElementById("all_ck").checked = true;
        }

        var sum = 0.0;
        for (var i = 0; i < ckitems.length; i++) {
            sum += parseFloat(localStorage.getItem(ckitems[i]));
        }
        document.getElementById("goods-sum-label").innerHTML = sum;
    }

    function deleteSomeGoods() {
        var checkboxs = document.getElementsByName("cbs");
        var ckitems = [];
        var num = 0;
        for (var i = 0; i < checkboxs.length; i++) {
            if (checkboxs[i].checked) {
                ckitems[num] = checkboxs[i].value;
                num++;
            }
        }
        if (ckitems.length == 0) {
            return alert("未选择任何选项！");
        } else {
            if (confirm("是否要删除所选项？")) {
                window.location.href = "<%=request.getContextPath()%>/cart/deleteGoods.cart?ckitems=" + ckitems;
            }
        }
    }

    function deleteGoods(goods_id) {
        if (confirm("是否要删除当前项？")) {
            window.location.href = "<%=request.getContextPath()%>/cart/deleteGoods.cart?ckitems=" + goods_id;
        }
    }

    function updateGoods(goods_id) {
        var new_num = document.getElementById('num_' + goods_id);
        if (new_num.value == null || new_num.value == "" || new_num.value == "0") {
            window.alert("数量不可为空！")
            window.location.reload();
        } else {
            window.location.href = "<%=path %>/cart/updateGoods.cart?ckitems=" + goods_id + "&num=" + new_num.value;
        }

    }

    function clearCart() {
        if (confirm('是否清空购物车？')) {
            window.location.href = '<%=path %>/cart/clearCart.cart';
        }
    }

    function settle() {

        var checkboxs = document.getElementsByName("cbs");
        var ckitems = [];
        var price = 0, num = 0;
        for (var i = 0; i < checkboxs.length; i++) {
            if (checkboxs[i].checked) {
                ckitems[num] = checkboxs[i].value;
                num++;
            }
        }

        if (ckitems.length == 0) {
            return alert("未选择任何选项！");
        } else {
            var sum = document.getElementById("goods-sum-label").innerHTML;
            window.location.href = "<%=path %>/order/settle.order?ckitems=" + ckitems + "&sum=" + sum;
        }

    }

</script>


</body>
</html>
