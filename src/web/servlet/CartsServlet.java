package web.servlet;

import biz.CartsBiz;
import biz.GoodsBiz;
import biz.impl.CartsBizImpl;
import biz.impl.GoodsBizImpl;
import entity.Carts;
import entity.Goods;
import entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CartsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //获取前端传递的值
        CartsBiz cartsBiz = new CartsBizImpl();

        //获取后端session的值
        HttpSession session = request.getSession();
        Users users = (Users) session.getAttribute("users");


        List list = cartsBiz.getCartsList(users.getId());

        //设置共享域
        request.setAttribute("list", list);

        //转发请求和相应对象到页面
        request.getRequestDispatcher("carts.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
