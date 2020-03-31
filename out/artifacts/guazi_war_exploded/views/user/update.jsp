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
        <span>修改用户信息</span>
    </div>
    <div class="content content2" style="display: none">
        <table>
            <tr>
                <td class="text-right">手机号码</td><td class="content_right"><input class="input inline-input" placeholder="请输入手机号码"> <span class="btn btn-info" onclick="select()">立即查找</span></td>
            </tr>
        </table>
    </div>


    <div class="header">
        <span>查找信息如下</span>
    </div>
    <div class="content">
        <table>
            <tr>
                <td class="text-right">姓名</td><td class="content_right"><input class="input" placeholder="请输入姓名"></td>
            </tr>
            <tr>
                <td class="text-right">手机号码</td><td class="content_right"><input class="input" placeholder="请输入手机号码"></td>
            </tr>
            <tr>
                <td class="text-right">密码</td><td class="content_right"><input class="input" placeholder="请输入密码"></td>
            </tr>
            <tr>
                <td></td><td class="content_right"><span class="btn btn-info" onclick="updateUser()">立即修改</span> <span class="btn">重置</span> </td>
            </tr>
        </table>
    </div>
</div>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/layer/layer.js"></script>
<script>
    function updateUser() {
        layer.load();
        //准备用户原id
        var id = u.id;
        //新输入的用户信息
        var name = $("input:eq(1)").val();
        var phone = $("input:eq(2)").val();
        var password = $("input:eq(3)").val();

        //通过ajax 将修改的信息发送给服务器
        $.post("../../courier/update.do","id="+id+"&name"+name+"&phone"+phone+"&password"+password,function (data) {
            layer.closeAll();
            if(data.result=="ok"){
                    layer.msg("<span style='color:#fff'>修改成功</span>");
                    $(".content2").fadeOut(1500);
            }else {
                layer.msg("<span style='color:#fff'>修改失败</span>");

            }
        },"JSON");


    }
    var u =null;
    function select() {
        layer.load();
        //1.获取用户输入的手机号
        var phone = $("input:eq(0)").val();
        //2.通过ajax将手机号码发送到服务器
        $.post("../../user/select.do","phone="+phone+"&flag=0",function (data) {
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
