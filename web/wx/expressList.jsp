<%@ page import="com.mzy.bean.User" %>
<%@ page import="com.mzy.service.ExpressServiceImp" %>
<%@ page import="com.mzy.service.BaseExpressService" %>
<%@ page import="com.mzy.bean.Express" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.mzy.util.DateFormatUtil" %><%--
  Created by IntelliJ IDEA.
  User: LWJ
  Date: 2020/1/6
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    //找到当前登录的用户
    User user = (User)session.getAttribute("user");
    if(user==null){
        response.sendRedirect("login.jsp");
        return;
    }

    String phone = user.getPhoneNumber();
    // 根据登录的用户手机号码. 通过service 查询用户的所有快递
    BaseExpressService service = new ExpressServiceImp();
    //未取件
    List<Express> data1 = service.findByUserPhone(phone);
    //已取件
    List<Express> data2 = new ArrayList<>();
    for(int i=0;i<data1.size();i++){
       if(data1.get(i).getStatus() == 1){
           Express e = data1.remove(i);
           data2.add(e);
           i--;
       }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <style type="text/css">
        body{
            margin: 0;
            padding: 0;
            background-color: #eeeeee;
            font-family: "楷体";
        }
        .item{
            margin: 20px;
            margin-bottom: 50px;
        }
        .imte_top{
            text-align: center;
        }
        .imte_top>span{
            font-size:14px;
            display: inline-block;
            padding: 5px 12px;
            background-color: #ddd;
            color:#fff;
            border-radius: 5px;
        }
        .item_content{
            background-color: #fff;
            padding: 25px 15px;
            margin-top: 6px;
        }
        .item_content_top_1{
            font-size: 24px;
            font-weight: bold;
        }
        .item_content_top_2{
            color:#eee;
            font-size: 14px;
            margin: 5px 0px;
        }
        .item_content_top_3{
            margin: 10px 0px;
        }
        .item_content_top_4{
            margin: 10px 0px;
        }
        .item_content_bottom>a{
            text-decoration: none;
            color:#33e;
        }
    </style>
</head>
<body>

<div class="content">

    <%
        for(Express e:data1){
            %>
    <div class="item">
        <div class="imte_top"><span><%=DateFormatUtil.format(e.getInTime())%></span></div>
        <div class="item_content">
            <div class="item_content_top">
                <div class="item_content_top_1">取件通知</div>
                <div class="item_content_top_2"><%=DateFormatUtil.format(e.getInTime())%></div>
                <div class="item_content_top_3"><您有一个包裹></您有一个包裹>到了!</div>
                <div class="item_content_top_4">
                    取件码:<span style="color:#05a"><%=e.getCode()%></span><br>
                    快递公司:<%=e.getCompany()%><br>
                    运单号码:<%=e.getENumber()%><br>
                    站点电话:13843838438<br>
                    取件地址:南工程好轻松快件集散中心<br>
                </div>
            </div>
            <hr>
            <div class="item_content_bottom">
                <a href="personQRcode.jsp?code=<%=e.getCode()%>">二维码</a>
            </div>
        </div>
    </div>



            <%
        }
    %>


</div>
<div class="content">
    <%
        for(Express e:data2){
            %>
    <div class="item">
        <div class="imte_top"><span><%=DateFormatUtil.format(e.getOutTime())%></span></div>
        <div class="item_content">
            <div class="item_content_top">
                <div class="item_content_top_1">取件成功通知</div>
                <div class="item_content_top_2"><%=DateFormatUtil.format(e.getOutTime())%></div>
                <div class="item_content_top_3">您有一个包裹取出了!</div>
                <div class="item_content_top_4">
                    快递公司:<%=e.getCompany()%><br>
                    运单号码:<%=e.getENumber()%><br>
                    站点电话:13843838438<br>
                </div>
            </div>
            <hr>
            <div class="item_content_bottom">
                <a href="#">有疑问 ? 点了也没用</a>
            </div>
        </div>
    </div>

            <%
        }
    %>



</div>
</body>
</html>
