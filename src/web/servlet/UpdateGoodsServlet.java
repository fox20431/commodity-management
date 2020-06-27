package web.servlet;

import biz.GoodsBiz;
import biz.impl.GoodsBizImpl;
import entity.Goods;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateGoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Goods goods = new Goods();

        // 将String类型转换为int类型
        goods.setId(Integer.parseInt(request.getParameter("id")));//必须要写，用于定位数据库记录
        goods.setName(request.getParameter("name"));
        goods.setType(request.getParameter("type"));
        goods.setIntro(request.getParameter("intro"));
        // 将String类型转换为int类型
        goods.setPrice(Integer.parseInt(request.getParameter("price")));
        // 调用usersDao 处理修改
        GoodsBiz goodsBiz = new GoodsBizImpl();
        int i = goodsBiz.updateGoodsById(goods);
        // 根据修改结果返回不同页面
        if (i > 0) {
            response.sendRedirect("index");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
