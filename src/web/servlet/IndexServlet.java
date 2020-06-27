package web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import biz.GoodsBiz;
import biz.UsersBiz;
import biz.impl.GoodsBizImpl;
import entity.*;

public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //获取前端传递的值
        GoodsBiz goodsBiz = new GoodsBizImpl();
        String strPage = request.getParameter("strPage");
        String keyWord = request.getParameter("keyWord");
        String priceOrder = request.getParameter("priceOrder");

        //获取后端session的值
        HttpSession session = request.getSession();
        Users users = (Users) session.getAttribute("users");


        //分页设置
        int pageNo = 1; //当前页，初始化为第一页
        int pageSize = 5;
        int pageUp = 1; //上一页
        int pageDown = 1; //下一页
        int allPage = goodsBiz.getAllPage(users.getId(),pageSize); // 合计页数
        if (strPage != null && !strPage.equals("")) { //获取当前页
            pageNo = Integer.parseInt(strPage);
        }
        if (pageNo > 1) {
            pageUp = pageNo - 1;
        } else {
            pageUp = pageNo;
        }
        if (pageNo < allPage) {
            pageDown = pageNo + 1;
        } else {
            pageDown = pageNo;
        }

        List list = goodsBiz.getGoodsList(users.getId(),keyWord, priceOrder, pageSize, pageNo);

        //设置共享域
        request.setAttribute("strPage",strPage);
        request.setAttribute("keyWord",keyWord);
        request.setAttribute("priceOrder",priceOrder);
        request.setAttribute("allPage", allPage);
        request.setAttribute("pageDown", pageDown);
        request.setAttribute("pageUp", pageUp);
        request.setAttribute("list", list);

        //转发请求和相应对象到页面
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}