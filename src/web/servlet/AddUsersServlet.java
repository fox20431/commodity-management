package web.servlet;

import biz.UsersBiz;
import biz.impl.UsersBizImpl;
import entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        Users users = new Users();

        users.setUsername(request.getParameter("username"));
        users.setPassword(request.getParameter("password"));
        users.setEmail(request.getParameter("email"));

        UsersBiz usersBiz = new UsersBizImpl();

        int i = usersBiz.addUsers(users);

        if (i>0){
            response.sendRedirect("./login.html");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
