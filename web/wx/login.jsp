<%--
  Created by IntelliJ IDEA.
  User: LWJ
  Date: 2020/1/6
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="css/form.css"/>
</head>
<body>
<div class="main">
    <h3>用户登录</h3>
    <div class="login-form">
        <p>
            <input placeholder="请输入账号" class="username input">
        </p>
        <p>
            <input placeholder="请输入密码" type="password" class="password input">
        </p>
        <p>
            <input type='button' value="点击登录" class="btn" onclick="login()">
        </p>
        <p>
            <a href="reg.jsp"><input type='button' value="还没账号?点击注册" class="btn"></a>
        </p>
    </div>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/layer/layer.js"></script>
    <script>
        function login(){
            layer.load();
            var username = $(".username").val();
            var password = $(".password").val();
            //通过ajax 将账号密码发送到服务器
            $.post("login.do","username="+username+"&password="+password,function(data){
                layer.closeAll();
                if(data.result == "ok"){
                    layer.msg("登录成功, 页面正在跳转");
                    window.location.href="index.jsp";
                }else{
                    layer.msg("账号或密码错误");
                }
            },"JSON");
        }
    </script>
</div>
</body>
</html>

