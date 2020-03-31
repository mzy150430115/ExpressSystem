package com.mzy.servlet;

import com.mzy.bean.Express;
import com.mzy.bean.Message;
import com.mzy.service.BaseExpressService;
import com.mzy.service.ExpressServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ExpressServlet")
public class ExpressSelectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.字符集
        response.setContentType("text/json;charset=utf-8");
        //1.接受要查询的单号信息
        String number = request.getParameter("number");
        //2.调用service,查询
        BaseExpressService service = new ExpressServiceImp();
        Express e = service.findByENumber(number);
        //3.根据查询的结果 准备不同的JSON格式数据
        Message msg = null;
        if(e == null){
            //查询失败{"result":"error"}
            msg = new Message("error");
        }else {
            //查询成功
            msg = new Message("ok",e);
        }
        String json = msg.toJSON();
        //4.将准备好的json数据 返回给浏览器
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
