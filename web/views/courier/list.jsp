<%@ page import="com.mzy.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mzy.service.UserServiceImp" %>
<%@ page import="com.mzy.util.DateFormatUtil" %><%--
  Created by IntelliJ IDEA.
  User: 12631
  Date: 2020/3/16
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../../css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/list.css"/>
</head>
<body>
<%
    List<User> users = new UserServiceImp().findCouriers();

%>
<table class="tab tab-hover">
    <tr class="active">
        <th>编号</th><th>姓名</th><th>手机号码</th><th>密码</th><th>注册时间</th><th>上次登陆时间</th><th>操作</th>
    </tr>
    <%
        for(User u:users){
            pageContext.setAttribute("u",u);
    %>
    <tr>
        <td>${u.id}</td>
        <td>${u.username}</td>
        <td>${u.phoneNumber}</td>
        <td>${u.password}</td>
        <td><%=DateFormatUtil.format(u.getCreateTime())%></td>
        <td><%=u.getLoginTime()==null?"未登录过":DateFormatUtil.format(u.getLoginTime())%></td>
        <td><span class="btn-danger" onclick="deleteUser(${u.id},this)">删除</span></td>
    </tr>

    <%
        }
    %>


</table>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/layer/layer.js"></script>
<script>
    function deleteUser(uId,span){
        layer.load();
        //1.    通过ajax 将uId 发送到服务器servlet
        $.post("../../courier/delete.do","id="+uId,function(data){
            layer.closeAll();
            if(data.result == "ok"){
                //删除成功、
                layer.msg("<span style='color:#fff'>删除成功</span>");
                $(span).parent().parent().remove();
            }else{
                //删除失败
                layer.msg("<span style='color:#fff'>删除失败</span>");
            }
        },"JSON");
    }
</script>
</body>
</html>

