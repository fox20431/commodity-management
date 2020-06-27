package web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/*
登陆验证过滤器
 */

public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //强制转换
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        //获取资源请求路径
        String uri = request.getRequestURI();

        //选择放行对象
        if(uri.contains("/login.html")||uri.contains("/loginServlet")||uri.contains("/register.html")||uri.contains("/validateUsernameServlet")||uri.contains("/addUserServlet")||uri.contains("/css/")||uri.contains("/fonts/")||uri.contains("/img/")||uri.contains("/js/")||uri.contains("/vendor/")){
            chain.doFilter(req, resp);
        }else{
            Object user = request.getSession().getAttribute("users");
            if(user!=null){
                chain.doFilter(req, resp);
            }else{
                response.sendRedirect("/login.html");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
