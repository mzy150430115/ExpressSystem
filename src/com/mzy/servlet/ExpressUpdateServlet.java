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

@WebServlet(name = "ExpressUpdateServlet")
public class ExpressUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.设置请求体的字符集
        request.setCharacterEncoding("UTF-8");
        //1.获取请求中用于修改快递的五个参数
        //-快递id
        String id = request.getParameter("id");
        int idValue = Integer.parseInt(id);
        //新的快递数据
        String number = request.getParameter("number");
        String company = request.getParameter("company");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        // 使用新的快递数据 组装一个对象
        Express e = new Express();
        e.setENumber(number);
        e.setCompany(company);
        e.setUsername(name);
        e.setUserphone(phone);
        //2.调用service 执行修改
        BaseExpressService service = new ExpressServiceImp();
        //3.根据修改的结果 准备不同的json数据
        boolean flag = service.updateById(idValue,e);
        Message msg = null;
        if(flag==true){
            msg = new Message("ok");
        }else {
            msg = new Message("error");
        }
        String json = msg.toJSON();
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
