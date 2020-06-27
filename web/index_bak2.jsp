<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>index</title>
    <link rel="icon" type="image/png" href="img/icons/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/reset.css">
    <link rel="stylesheet" type="text/css" href="./css/util.css">
    <link rel="stylesheet" type="text/css" href="./css/index.css">

    <script type="text/javascript" src="./js/jquery-3.5.1.min.js"></script>

    <script>
        function postPriceOrder(ob) {
            var priceOrder = document.getElementById("priceOrder");
            priceOrder.value = ob;
            document.getElementById("myFrom").submit();
        }
    </script>

</head>
<body>

<%--功能选项--%>
<div>
    <form action="index" method="post" id="myFrom">
        <input type="hidden" name="priceOrder" id="priceOrder"/>
        <table>
            <tr>
                <td>
                    <input name="keyWord" type="text" placeholder="请输入关键词"/>
                </td>
                <td>
                    <input value="关键字搜索" type="submit"/>
                </td>
                <td>
                    <input value="按价格降序" type="button" onclick="postPriceOrder('yes')"/>
                </td>
                <td>
                    <input value="按价格升序" type="button" onclick="postPriceOrder('no')"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<%--表格展示--%>
<div>
    <table>
        <tr>
            <td>物品编号</td>
            <td>物品名称</td>
            <td>物品类型</td>
            <td>物品介绍</td>
            <td>物品价格</td>
            <td>上架时间</td>
        </tr>

        <c:forEach items="${requestScope.list}" var="g" varStatus="i">
            <tr>
                <td>${g.id}</td>
                <td>${g.name}</td>
                <td>${g.type}</td>
                <td>${g.intro}</td>
                <td>${g.price}</td>
                <td>${g.date}</td>
                <td>
                    <a href="./delGoodsServlet?id=${g.id}">删除</a>&nbsp;&nbsp;
                    <a href="updategoods.jsp?id=${g.id}">修改</a>
                </td>
            </tr>
        </c:forEach>

        <%--换页--%>
        <tr>
            <td colspan="6">
                <a href="index?strPage=1">首页</a>
                <a href="index?strPage=${pageUp}">上一页</a>
                <a href="index?strPage=${pageDown}">下一页</a>
                <a href="index?strPage=${allPage}">末页</a>
                <form action="index" method="get" style="display: inline" >
                    <input type="text" name="strPage" style="width: 30px">
                    <input type="submit" value="go">
                </form>
            </td>
        </tr>

    </table>
</div>


<a href="addgoods.jsp">添加新商品</a>

</body>
</html>