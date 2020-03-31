package com.mzy.servlet;

import com.mzy.bean.Express;
import com.mzy.service.BaseExpressService;
import com.mzy.service.ExpressServiceImp;
import com.mzy.util.SendSms;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExpressAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           //设置字符集
            request.setCharacterEncoding("UTF-8");
            //从请求中获取四个快递的参数
        String number = request.getParameter("number");
        String company = request.getParameter("company");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        //调用service，完成数据的存储
        Express e = new Express(number,name,phone,company,"-1");
        BaseExpressService service = new ExpressServiceImp();
        String code = service.insert(e);
        //判断返回的数据是否是null
        String json = null;
        if(code==null){
            //插入失败，取件码重复
            //1.准备json数据，返回给ajax    {"result":"error"};
            json = "{\"result\":\"error\"};";
        }else {
            //插入成功
            //1.发送短信
            SendSms.send(phone,code);
            //2.准备json返回给ajax
            json = "{\"result\":\"ok\"}";
        }
        //给浏览器回复json
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
