package web.servlet;

import biz.UsersBiz;
import biz.impl.UsersBizImpl;
import entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ValidateUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out=response.getWriter();

        String username = request.getParameter("username");

        UsersBiz usersBiz = new UsersBizImpl();

        boolean flag = usersBiz.validateUsername(username);

        String result = null;

        if (flag == true){
            result = "用户名已被占用！";
        }else{
            result = "该用户名可用！";
        }

        response.setContentType("text/html");
        response.getWriter().print(result);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
