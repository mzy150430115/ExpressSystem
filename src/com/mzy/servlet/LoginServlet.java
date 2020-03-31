package com.mzy.servlet;

import com.mzy.util.SessionUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/html;charset=utf-8");
        //1.接受请求的参数（账号，密码）
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //2.验证账号密码是否为admin&123
        if("admin".equals(username) && "123".equals(password)){
            //3.管理员登录成功
            //处理方式：通过重定向，向浏览器发送302状态码，以及主页地址
            resp.sendRedirect("index.jsp");
            //将管理员登录成功的标记存储在session中
            SessionUtil.setManagerFlag(req.getSession());
        }else {
            //4.管理员登录失败
            //处理方式：通过resp响应对象，获取打印流，打印相应体内容，打印script响应体内容
            PrintWriter out = resp.getWriter();
            out.println("<script>alert('账号密码登录错误，登录失败！');window.location.href='login.jsp';</script>");
        }
    }
}
