package com.mzy.servlet;

import com.mzy.bean.Message;
import com.mzy.bean.User;
import com.mzy.service.BaseUserService;
import com.mzy.service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //1.    接受客户端发送的四个关键性参数
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String upass = request.getParameter("upass");
        String flag = request.getParameter("flag");
        int flagValue = Integer.parseInt(flag);
        User u = new User(name,upass,phone,flagValue);
        //2.    调用service ， 进行用户的添加
        BaseUserService service = new UserServiceImp();
        int f = service.insert(u);
        //3.    根据service执行的结果， 返回不同的消息
        Message msg = null;
        if(f == 1){
            msg = new Message("ok");
        }else{
            msg = new Message("error");
        }
        String json = msg.toJSON();
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
