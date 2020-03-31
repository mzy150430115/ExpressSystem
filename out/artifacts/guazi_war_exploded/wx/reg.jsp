<%--
  Created by IntelliJ IDEA.
  User: LWJ
  Date: 2020/1/6
  Time: 21:00
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
    <h3>用户注册</h3>
    <div class="login-form">
        <p>
            <input placeholder="请输入手机号码" class="username input">
        </p>
        <p>
            <input placeholder="请输入密码" type="password" class="password input">
        </p>
        <p>
            <input placeholder="请确认密码" type="password" class="password input">
        </p>
        <p>
            <input type='button' value="点击注册" class="btn" onclick="reg()">
        </p>
        <p>
            <a href="login.jsp"><input type='button' value="已有账号?点击登录" class="btn"></a>
        </p>
    </div>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/layer/layer.js"></script>
    <script>
        function reg(){
            //1.    获取用户输入的帐号密码
            var username = $("input:eq(0)").val();
            var password1 = $("input:eq(1)").val();
            var password2 = $("input:eq(2)").val();
            if(password1 == password2){
                //2.    将用户的账号密码, 通过ajax发送到服务器
                layer.load();
                var start = new Date().getTime();
                $.post("reg.do","username="+username+"&password="+password1,function(data){
                    layer.closeAll();
                    var end = new Date().getTime();
                    if(data.result == "ok"){
                        layer.msg("注册成功");
                         //为了让用户感觉流畅, 所以卡他一下
                        var time = end-start;
                        setTimeout(function(){
                            window.location.href="login.jsp";
                        },time<1000?1000:300)

                    }else{
                        layer.msg("手机号码已存在, 请尝试登陆");
                    }
                },"JSON");
                //3.    接收服务器回复的内容
            }else{
                layer.msg("两次密码输入不一致",function(){});
            }

        }
    </script>
</div>
</body>
</html>
