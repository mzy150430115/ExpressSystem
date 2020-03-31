<%@ page import="com.mzy.util.SessionUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  boolean flag = SessionUtil.getManagerFlag(session);
  if(!flag){
    //未登录的用户
    %>
    <script>
       alert("请先登录");
       window.location.href="login.jsp";
    </script>
<%
    return;
  }
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="assets/css/layui.css">
  <link rel="stylesheet" href="assets/css/admin.css">
  <link rel="icon" href="/favicon.ico">
  <title>瓜子快递管理后台</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header custom-header">

    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item slide-sidebar" lay-unselect>
        <a href="javascript:;" class="icon-font"><i class="ai ai-menufold"></i></a>
      </li>
    </ul>

    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">admin</a>
        <dl class="layui-nav-child">
          <dd><a href="#">帮助中心</a></dd>
          <dd><a href="login.html">退出</a></dd>
        </dl>
      </li>
    </ul>
  </div>

  <div class="layui-side custom-admin">
    <div class="layui-side-scroll">

      <div class="custom-logo">
        <img src="assets/images/logo.gif" alt=""/>
        <h1>瓜子快递</h1>
      </div>
      <ul id="Nav" class="layui-nav layui-nav-tree">
        <li class="layui-nav-item">
          <a href="javascript:;">
            <i class="layui-icon">&#xe68e;</i>
            <em>主页</em>
          </a>
          <dl class="layui-nav-child">
            <dd><a href="views/console.jsp"><i class=1"layui-icon">&#xe665;</i> 控制台</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">
            <i class="layui-icon">&#xe664;</i>
            <em>快递员管理</em>
          </a>
          <dl class="layui-nav-child">
            <dd><a href="views/courier/list.jsp"><i class="layui-icon">&#xe60a;</i> 快递员列表</a></dd>
            <dd><a href="views/courier/add.jsp"><i class="layui-icon">&#xe654;</i> 快递员录入</a></dd>
            <dd><a href="views/courier/update.jsp"><i class="layui-icon">&#xe642;</i> 快递员修改</a></dd>
            <dd><a href="views/courier/delete.jsp"><i class="layui-icon">&#xe640;</i> 快递员删除</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">
            <i class="layui-icon">&#xe857;</i>
            <em>快件管理</em>
          </a>
          <dl class="layui-nav-child">
            <dd><a href="views/express/list.jsp"><i class="layui-icon">&#xe60a;</i> 快件列表</a></dd>
            <dd><a href="views/express/add.jsp"><i class="layui-icon">&#xe654;</i> 快件录入</a></dd>
            <dd><a href="views/express/update.jsp"><i class="layui-icon">&#xe642;</i> 快件修改</a></dd>
            <dd><a href="views/express/delete.jsp"><i class="layui-icon">&#xe640;</i> 快件删除</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">
            <i class="layui-icon">&#xe770;</i>
            <em>用户管理</em>
          </a>
          <dl class="layui-nav-child">
            <dd><a href="views/user/list.jsp"><i class="layui-icon">&#xe60a;</i> 用户列表</a></dd>
            <dd><a href="views/user/add.jsp"><i class="layui-icon">&#xe654;</i> 用户增加</a></dd>
            <dd><a href="views/user/update.jsp"><i class="layui-icon">&#xe642;</i> 用户修改</a></dd>
            <dd><a href="views/user/delete.jsp"><i class="layui-icon">&#xe640;</i> 用户删除</a></dd>
          </dl>
        </li>
      </ul>

    </div>
  </div>

  <div class="layui-body">
    <div class="layui-tab app-container" lay-allowClose="true" lay-filter="tabs">
      <ul id="appTabs" class="layui-tab-title custom-tab"></ul>
      <div id="appTabPage" class="layui-tab-content"></div>
    </div>
  </div>

  <div class="layui-footer">
    <p>©2019 <a href="http://www.xxxxx.cn/" target="_blank">xxxx公司版权声明</a></p>
  </div>

  <div class="mobile-mask"></div>
</div>
<script src="assets/layui.js"></script>
<script src="js/index.js" data-main="home"></script>
</body>
</html>
