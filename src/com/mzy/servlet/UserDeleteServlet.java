package com.mzy.servlet;

import com.mzy.bean.Message;
import com.mzy.service.BaseUserService;
import com.mzy.service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求中携带的id值
        String id = request.getParameter("id");
        int idValue = Integer.parseInt(id);
        //2.传递给service 完成删除
        BaseUserService service = new UserServiceImp();
        int f = service.deleteUserById(idValue);
        //3.根据删除的结果 准备不同的消息返回
        Message msg = null;
        if(f==1){
            //删除成功
            msg=new Message("ok");
        }else {
            msg=new Message("error");
        }
        String json = msg.toJSON();
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
