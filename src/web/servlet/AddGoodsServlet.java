package web.servlet;

import biz.GoodsBiz;
import biz.impl.GoodsBizImpl;
import entity.Goods;
import entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddGoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Users users = (Users) session.getAttribute("users");

        // 1. 获取表单提交过来的数据
        Goods goods = new Goods();
        goods.setName(request.getParameter("name"));
        goods.setType(request.getParameter("type"));
        goods.setIntro(request.getParameter("intro"));
        goods.setPrice(Integer.parseInt(request.getParameter("price")));
        goods.setUsersId(users.getId());

        // 2.调用Biz层
        GoodsBiz goodsBiz = new GoodsBizImpl();
        int i = goodsBiz.addGoods(goods);

        //判断是否操作成功，若成功重定向
        if(i>0){
            response.sendRedirect("index");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
