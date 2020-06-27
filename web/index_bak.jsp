<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>

<%@ page import="dao.*" %>
<%@ page import="entity.*" %>
<%@ page import="util.*" %>
<%@ page import="dao.impl.GoodsDaoImpl" %>

<%
    request.setCharacterEncoding("UTF-8");

    //使用session，登录后才可以访问该页面
    Users users=(Users)session.getAttribute("users");
    if (users==null){
        response.sendRedirect("getUser.jsp");
    }

    //分页逻辑
    String strPage = request.getParameter("strPage");
    GoodsDaoImpl gDao = new GoodsDaoImpl();//后面要用到
    int pageSize=3;//每页展示记录数
    int pageNo=1;//后面赋值把该变量变为当前页
    int pageUp=1;//上一页
    int pageDown=1;//下一页
    int allPage=gDao.getAllPage(pageSize);
    if(strPage!=null&&!"".equals(strPage)){
        pageNo=Integer.parseInt(strPage);
    }
    if(pageNo>1){
        pageUp = pageNo-1;
    }else{
        pageUp = pageNo;
    }
    if(pageNo<allPage){
        pageDown =pageNo+1;
    }else{
        pageDown = pageNo;
    }

    //展示条件
    String keyWord = request.getParameter("keyWord");
    String price = request.getParameter("price");
    if (keyWord != null) {
        //trim方法用于删除字符串头尾空格
        keyWord = keyWord.trim();
    }

%>

<script>
    function changePrice(ob) {
        var price = document.getElementById("price");
        price.value = ob;
        document.getElementById("myfrom").submit();
    }
</script>

<span>SessionID:<%=session.getId()%></span>
<br>

<%
    if (users != null) {
        out.print("<span>欢迎"+users.getUsername()+"！</span>");
    }
%>

<a href="logout.jsp">退出登录</a>

<%--功能选项--%>
<form action="index_bak.jsp" method="post" id="myfrom">
    <input type="hidden" name="price" id="price" value=""/>
    <table>
        <tr>
            <td>
                <input name="keyWord" type="text" value="<%=Tools.nullToString(keyWord)%>" placeholder="请输入关键词"/>
            </td>
            <td>
                <input value="关键字搜索" type="submit"/>
            </td>
            <td>
                <input value="按价格降序" type="button" onclick="changePrice('yes')"/>
            </td>
            <td>
                <input value="按价格升序" type="button" onclick="changePrice('no')"/>
            </td>
        </tr>
    </table>
</form>


<%--表格展示--%>
<table>
    <tr>
        <td>物品编号</td>
        <td>物品名称</td>
        <td>物品类型</td>
        <td>物品介绍</td>
        <td>物品价格</td>
        <td>上架时间</td>
    </tr>

    <%
        List list = gDao.getGoodsList(keyWord, price,pageSize,pageNo);
        for (int i = 0; i < list.size(); i++) {
            //拆箱 把object类型还原成Goods类型
            Goods goods = (Goods) list.get(i);
    %>

    <tr>
        <td><%=goods.getId() %>
        </td>
        <td><%=goods.getName() %>
        </td>
        <td><%=goods.getType() %>
        </td>
        <td><%=goods.getIntro() %>
        </td>
        <td><%=goods.getPrice() %>
        </td>
        <td><%=goods.getDate() %>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/servlet/delgoods?id=<%=goods.getId()%>">删除</a>&nbsp;&nbsp;
            <a href="updategoods.jsp?id=<%=goods.getId()%>">修改</a>
        </td>
    </tr>
    <%
        }
    %>

    <tr>
        <td colspan="6">
            <a href="index_bak.jsp?strPage=1">首页</a>
            <a href="index_bak.jsp?strPage=<%=pageUp%>">上一页</a>
            <a href="index_bak.jsp?strPage=<%=pageDown%>">下一页</a>
            <a href="index_bak.jsp?strPage=<%=allPage%>">末页</a>
            <form action="index_bak.jsp" method="get" style="display: inline" >
                <input type="text" name="strPage" style="width: 30px">
                <input type="submit" value="go">
            </form>
        </td>
    </tr>

</table>

<a href="addgoods.jsp">添加新商品</a>
