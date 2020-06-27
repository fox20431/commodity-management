<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>index</title>

    <link rel="icon" type="image/png" href="img/icons/favicon.ico"/>

    <link rel="stylesheet" type="text/css" href="./css/reset.css">
    <link rel="stylesheet" type="text/css" href="./css/util.css">
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/index.css">

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
                <a class="nav-link" href="./index">主页</a>

            </li>
            <li class="nav-item active">
                <a class="nav-link" href="./add_goods.jsp">添加商品</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="./addCarts">收藏</a>
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
            <h2 class="display-4 m-b-40">修改商品类型</h2>

                <form method="post" action="./updateGoodsServlet">
                    <table class="table table-bordered table-hover ">
                        <tbody>
                        <tr id="first-row">
                            <td>名称</td>
                            <td>内容</td>
                        </tr>
                        <tr>
                            <td>物品编号</td>
                            <td>
                                <input type="text" value="<c:out value="${goods.id}" default="null_value"></c:out>" readonly="readonly" name="id">

                            </td>
                        </tr>

                        <tr>
                            <td>物品名称</td>
                            <td>
                                <input type="text" value="<c:out value="${goods.name}" default="null_value"></c:out>" name="name">
                            </td>
                        </tr>
                        <tr>
                            <td>物品类型</td>
                            <td>
                                <input type="text" value="<c:out value="${goods.type}" default="null_value"></c:out>" name="type">
                            </td>
                        </tr>
                        <tr>
                            <td>物品介绍</td>
                            <td>
                                <input type="text" value="<c:out value="${goods.intro}" default="null_value"></c:out>" name="intro">
                            </td>
                        </tr>
                        <tr>
                            <td>物品价格</td>
                            <td>
                                <input type="text" value="<c:out value="${goods.price}" default="null_value"></c:out>" name="price">
                            </td>
                        </tr>
                        <tr>
                            <td>上架时间</td>
                            <td>
                                <input type="text" value="<c:out value="${goods.date}" default="null_value"></c:out>" readonly="readonly" name="date" >
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><button class="btn btn-dark">提交</button></td>
                        </tr>

                        </tbody>
                    </table>
                </form>

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


