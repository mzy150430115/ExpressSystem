package com.mzy.servlet.wx;

import com.mzy.bean.Message;
import com.mzy.bean.User;
import com.mzy.service.BaseUserService;
import com.mzy.service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //1.接受请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2.将接收到的手机号码 密码 调用service 进行注册
        BaseUserService service = new UserServiceImp();
        int login = service.login(username, password);
        //3.根据登录的结果准备不同的消息回复给ajax
        Message msg = null;
        switch (login){
            case 0:
                //{"result":"ok",data:0}
                msg = new Message("ok",0);
                User u = new User();
                u.setPhoneNumber(username);
                u.setFlag(0);
                request.getSession().setAttribute("user",u);
                break;
            case 1:
                //{"result":"ok",data:1}
                msg = new Message("ok",1);
                User u2 = new User();
                u2.setPhoneNumber(username);
                u2.setFlag(1);
                request.getSession().setAttribute("user",u2);
                break;
            case -1:
                //{"result":"error"}
                msg = new Message("error");
                break;
        }
        String json = msg.toJSON();
        response.getWriter().write(json);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
