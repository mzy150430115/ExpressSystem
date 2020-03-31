package com.mzy.servlet.wx;

import com.mzy.bean.Express;
import com.mzy.bean.Message;
import com.mzy.service.BaseExpressService;
import com.mzy.service.BaseUserService;
import com.mzy.service.ExpressServiceImp;
import com.mzy.util.DateFormatUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FindExpressServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.处理传递json时的字符集问题
        response.setContentType("text/json;charset=utf-8");
        //1.获取ajax发送的取件码
        String code = request.getParameter("code");
        //2.通过取件码，调用service查询快递信息
        BaseExpressService service = new ExpressServiceImp();
        Express e = service.findByCode(code);
        //3.根据查询的结果，给ajax回复不同的消息
        Message msg = null;
        if(e == null){
            msg = new Message("error");
        }else {
            String time = DateFormatUtil.format(e.getInTime());
            e.setCode(time);
            msg = new Message("ok",e);
        }
        String json = msg.toJSON();
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
