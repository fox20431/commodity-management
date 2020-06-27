package web.servlet;

import biz.GoodsBiz;
import biz.impl.GoodsBizImpl;
import entity.Goods;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateGoodsPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Goods goods = new Goods();

        String id = request.getParameter("id"); // 第一步获取ID

        GoodsBiz goodsBiz = new GoodsBizImpl();

        goods = goodsBiz.getGoodsById(id);// 根据ID 获取uses对象

        request.setAttribute("goods",goods);

        request.getRequestDispatcher("update_goods.jsp").forward(request, response);;

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
