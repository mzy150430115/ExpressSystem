package com.mzy.servlet;

import com.mzy.service.BaseExpressService;
import com.mzy.service.ExpressServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ExpressDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //1.接收要删除的快递id
        String id = request.getParameter("id");
            //2.将id传给service，完成删除
        int idValue = Integer.parseInt(id);
        BaseExpressService service = new ExpressServiceImp();
        boolean success = service.deleteById(idValue);
        String json = null;
        if(success){
            json = "{\"result\":\"ok\"}";
            //成功{"result":"ok"}
        }else {
            //失败{"result":"error"}
            json = "{\"result\":\"error\"}";
        }
        response.getWriter().append(json);
            //3.根据service返回的删除结果（boolean），向浏览器回复json的数据

    }
}
