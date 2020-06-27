package web.servlet;

import biz.CartsBiz;
import biz.impl.CartsBizImpl;
import entity.Carts;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelCartsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String goodId = request.getParameter("id");

        CartsBiz cartsBiz = new CartsBizImpl();

        int i = cartsBiz.delCarts(goodId);

        if(i>0){
            response.sendRedirect("carts");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
