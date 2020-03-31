<%--
  Created by IntelliJ IDEA.
  User: 12631
  Date: 2020/3/16
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../../css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/add.css"/>
</head>
<body>
<div id="app">
    <div class="header">
        <span>删除快递员</span>
    </div>
    <div class="content">
        <table>
            <tr>
                <td class="text-right">手机号码</td><td class="content_right"><input class="input inline-input" placeholder="请输入手机号码"> <span class="btn btn-info" onclick="select()">立即查找</span></td>
            </tr>
        </table>
    </div>


    <div class="header">
        <span>查找信息如下</span>
    </div>
    <div class="content content2" style="display: none" >
        <table>
            <tr>
                <td class="text-right">姓名</td><td class="content_right"><input disabled class="input" placeholder="请输入姓名"></td>
            </tr>
            <tr>
                <td class="text-right">手机号码</td><td class="content_right"><input disabled class="input" placeholder="请输入姓名"></td>
            </tr>
            <tr>
                <td class="text-right">密码</td><td class="content_right"><input disabled class="input" placeholder="请输入姓名"></td>
            </tr>
            <tr>
                <td></td><td class="content_right"><span class="btn btn-info" onclick="deleteUser()">立即删除</span> <span class="btn">重置</span> </td>
            </tr>
        </table>
    </div>
</div>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/layer/layer.js"></script>
<script>
    function deleteUser() {
        layer.load();
        //1.通过ajax将uid发送给servlet服务器
        $.post("../../courier/delete.do","id="+u.id,function (data) {
            layer.closeAll();
            if(data.result=="ok"){
                //删除成功
                layer.msg("<span style='color:#fff'>删除成功</span>");
                $(".content2").fadeOut(1500);
            }else {
                //删除失败
                layer.msg("<span style='color:#fff'>删除失败</span>");
            }
        },"JSON");
    }

    var u =null;
    function select() {
        layer.load();
        //1.获取用户输入的手机号
        var phone = $("input:eq(0)").val();
        //2.通过ajax将手机号码发送到服务器
        $.post("../../courier/select.do","phone="+phone+"&flag=1",function (data) {
            layer.closeAll();
            if(data.result=="ok"){
                u=data.data;
                layer.msg("<span style='color:#fff'>查询成功</span>");
                $(".content2").fadeIn(1500);
                $("input:eq(1)").val(u.username);
                $("input:eq(2)").val(u.phoneNumber);
                $("input:eq(3)").val(u.password);
            }else {
                layer.msg("<span style='color:#fff'>查询失败</span>");
            }
        },"JSON");
    }
</script>
</body>
</html>
