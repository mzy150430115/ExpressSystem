<%--
  Created by IntelliJ IDEA.
  User: 12631
  Date: 2020/3/13
  Time: 10:10
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
        <span>删除快递信息</span>
    </div>
    <div class="content">
        <table>
            <tr>
                <td class="text-right">运单号</td><td class="content_right"><input class="input inline-input" placeholder="请输入运单号码"> <span class="btn btn-info" onclick="find()">立即查找</span></td>
            </tr>
        </table>
    </div>


    <div class="header">
        <span>查找信息如下</span>
    </div>
    <div class="content content2" style="display: none">
        <table>
            <tr>
                <td class="text-right">快递单号</td><td class="content_right"><input readonly class="input" placeholder="请输入姓名"></td>
            </tr>
            <tr>
                <td class="text-right">快递公司</td><td class="content_right"><input readonly class="input" placeholder="请输入姓名"></td>
            </tr>
            <tr>
                <td class="text-right">收货人姓名</td><td class="content_right"><input readonly class="input" placeholder="请输入姓名"></td>
            </tr>
            <tr>
                <td class="text-right">手机号码</td><td class="content_right"><input readonly class="input" placeholder="请输入姓名"></td>
            </tr>
            <tr>
                <td class="text-right">快递状态</td><td class="content_right"><input readonly disabled type="radio" name="status">已签收  <input readonly disabled name="status" type="radio">未签收</td>
            </tr>
            <tr>
                <td></td><td class="content_right"><span class="btn btn-info" onclick="deleteE()">立即删除</span> <span class="btn">重置</span> </td>
            </tr>
        </table>
    </div>
</div>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/layer/layer.js"></script>
<script>
    var e = null;
    function find(){
        layer.load();
        //获取要查询的快递单号
        var number = $("input:eq(0)").val();
        //将单号通过ajax发送到服务器上
        $.post("../../express/select.do","number="+number,function (data){
            layer.closeAll();
            //console.log(data);
            if(data.result == "ok"){
                layer.msg("<span style='color:#fff'>查询成功</span>");
                 e = data.data;
                $("input:eq(1)").val(e.eNumber);
                $("input:eq(2)").val(e.company);
                $("input:eq(3)").val(e.username);
                $("input:eq(4)").val(e.userphone);
                if(e.status == 0){
                    $("input:eq(6)").attr("checked","checked");
                }else {
                    $("input:eq(5)").attr("checked","checked");
                }
                $(".content").fadeIn(1500);

            }else {
                layer.msg("<span style='color:#fff'>查询失败！单号不存在</span>");
            }
        },"JSON");


    }

    function deleteE(eId,span) {
        layer.load();
        $.get("../../express/delete.do","id="+e.id,function(data) {
            layer.closeAll();
            if(data.result == "ok"){
                //alert("<span style='color: #fff'>删除成功</span>");
                layer.msg("<span style='color: #fff'>删除成功</span>");
                $(".content2").fadeOut(1500);
            }else {
                //alert("删除失败");
                layer.msg("<span style='color: #fff'>删除失败</span>");
            }
        },"JSON");
        //$(span).parent().parent().remove();


    }
</script>

</body>
</html>
