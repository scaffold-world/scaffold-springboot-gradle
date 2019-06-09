<#assign base=request.contextPath />
<!doctype html>
<html lang="en">
<head>
	<#include "head.ftl">
</head>
<body class="login-bg">

<div class="login">
    <div class="message">后台管理登录</div>
    <div id="darkbannerwrap"></div>

    <form method="post" class="layui-form" action="/login/check">
        <input name="username" placeholder="用户名" type="text" lay-verify="required" class="layui-input">
        <hr class="hr15">
        <input name="password" lay-verify="required" placeholder="密码" type="password" class="layui-input">
        <hr class="hr15">
        <input class="loginin" value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
    </form>
</div>

<script type="text/javascript">
    layui.use(['form', 'admin'], function () {
        var form = layui.form,
                admin = layui.admin,
                $ = layui.$;
        //监听提交
        form.on('submit(login)', function (data) {
            /*$.ajax({
                type: 'POST',
                url: _ctx + '/login/check',
                data: {
                    username: data.field.username,
                    password: data.field.password
                },
                dataType:  "JSON",
                success: function (data) {
                    console.log("跳转1")
                    if(data.code === 0){
                        console.log("跳转2")
                        window.location.href = _ctx + '/index';
                    }
                }
            });*/
            window.location.href = _ctx + '/index';
        });
    });
</script>
<!-- 底部结束 -->
</body>
</html>