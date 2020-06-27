<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>index</title>

    <link rel="icon" type="image/png" href="img/icons/favicon.ico"/>

    <link rel="stylesheet" type="text/css" href="./css/util.css">
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/main.css">

    <style>

        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>


    <script>
        function postPriceOrder(ob) {
            var priceOrder = document.getElementById("priceOrder");
            priceOrder.value = ob;
            document.getElementById("form-order").submit();
        }
    </script>


</head>
<body>

<%--导航栏--%>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <span class="navbar-brand">商品管理</span>
    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="./index ">主页</a>

            </li>
            <li class="nav-item active">
                <a class="nav-link" href="./add_goods.jsp">添加商品</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="./carts">收藏</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="#">待开发</a>
            </li>
        </ul>
        <span class="navbar-brand">欢迎您，<c:out value="${users.username}" default="null_name"></c:out>！</span>
        <a href="./logout" class="navbar-brand">退出</a>
    </div>
</nav>

<main role="main">

    <%--商品展示--%>
    <div class="jumbotron">
        <div class="container  text-center">
            <h2 class="display-4 m-b-40">商品列表</h2>

            <table class="table table-bordered table-hover ">
                <tbody>
                    <tr id="first-row">
                        <td>物品编号</td>
                        <td>物品名称</td>
                        <td>物品类型</td>
                        <td>物品介绍</td>
                        <td>物品价格</td>
                        <td>上架时间</td>
                        <td colspan="3">操作</td>
                    </tr>

                    <c:forEach items="${requestScope.list}" var="g" varStatus="x">
                    <tr>
                        <%--<td>${x.index+1}</td>--%>
                        <td>${g.id}</td>
                        <td>${g.name}</td>
                        <td>${g.type}</td>
                        <td>${g.intro}</td>
                        <td>${g.price}</td>
                        <td>${g.date}</td>
                        <td>
                            <a href="./addCartsServlet?id=${g.id}">收藏</a>
                        </td>
                        <td>
                            <a href="./updateGoods?id=${g.id}">修改</a>
                        </td>
                        <td>
                            <a href="./delGoodsServlet?id=${g.id}">删除</a>&nbsp;&nbsp;
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        <span>第<c:out value="${strPage}" default="1"></c:out>页</span>
        </div>
    </div>

    <%--展示功能选择--%>
    <div class="container">
        <div class="row text-center">
            <div class="col-md-4">
                <h2>翻页</h2>
                <p>用于翻页和指定页面跳转</p>
                <div class="m-t-40 m-b-20">
                    <a class="btn btn-dark" href="./index?strPage=1&priceOrder=<c:out value="${priceOrder}"></c:out>&keyWord=<c:out value="${keyWord}"></c:out>">首页</a>
                    <a class="btn btn-dark" href="./index?strPage=${pageUp}&priceOrder=<c:out value="${priceOrder}"></c:out>&keyWord=<c:out value="${keyWord}"></c:out>">上一页</a>
                    <a class="btn btn-dark" href="./index?strPage=${pageDown}&priceOrder=<c:out value="${priceOrder}"></c:out>&keyWord=<c:out value="${keyWord}"></c:out>">下一页</a>
                    <a class="btn btn-dark" href="./index?strPage=${allPage}&priceOrder=<c:out value="${priceOrder}"></c:out>&keyWord=<c:out value="${keyWord}"></c:out>">末页</a>
                </div>

                <div>
                    <form action="index" method="get" style="display: inline" >
                        <input type="text" name="strPage" id="input-jump">
                        <input type="submit" value="Go" class="btn btn-dark">
                    </form>
                </div>

            </div>
            <div class="col-md-4">
                <h2>查询</h2>
                <p>通过商品名称或商品类型进行查询</p>
                <div class="m-t-40">
                    <form action="./index" method="get">
                        <input class="m-b-20" id="input-search" name="keyWord" type="text" placeholder="请输入关键词"/>
                        <br>
                        <input type="submit" class="btn btn-dark" value="Go" >
                    </form>
                </div>

            </div>
            <div class="col-md-4">
                <h2>排序</h2>
                <p>按照价格排序升序或降序排列</p>
                <div class="m-t-40">
                    <form action="./index" method="get" id="form-order">
                        <input  type="hidden" name="priceOrder" id="priceOrder"/>
                        <input class="btn btn-dark m-b-20" value="按价格降序" type="button" onclick="postPriceOrder('yes')"/>
                        <input class="btn btn-dark m-b-20" value="按价格升序" type="button" onclick="postPriceOrder('no')"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>

<%--结尾注释--%>
<hr>
<footer class="container">
    <p style="text-align: right">2020年创客课设</p>
</footer>

</body>
</html>
