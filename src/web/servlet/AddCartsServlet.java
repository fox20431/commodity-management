package web.servlet;

import biz.CartsBiz;
import biz.impl.CartsBizImpl;
import entity.Carts;
import entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddCartsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Users users = (Users) session.getAttribute("users");

        int goodsId = Integer.parseInt(request.getParameter("id"));
        int usersId = users.getId();

        Carts carts = new Carts();

        carts.setGoodsId(goodsId);
        carts.setUsersId(usersId);

        CartsBiz cartsBiz = new CartsBizImpl();

        cartsBiz.addCarts(carts);

        response.sendRedirect("/index");

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
