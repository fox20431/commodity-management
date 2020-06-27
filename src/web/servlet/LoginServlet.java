package web.servlet;

import biz.*;
import biz.impl.*;
import entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //编码格式
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //声明session引用
        HttpSession session=request.getSession();
        //声明out引用
        PrintWriter out=response.getWriter();

        Users users = new Users();
        users.setUsername(request.getParameter("username"));
        users.setPassword(request.getParameter("password"));
        UsersBiz usersBiz = new UsersBizImpl();
        boolean flag = usersBiz.login(users);


        if(flag == true ){
            Users activeUser = usersBiz.getUsersByUsername(users.getUsername());
            session.setAttribute("users",activeUser);
            response.sendRedirect("./index");
        }else{
            out.print("<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "   <script>alert('用户名或者密码错误！');\n" +
                    "   location.href=\"/login.html\";\n" +
                    "</script>\n");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
