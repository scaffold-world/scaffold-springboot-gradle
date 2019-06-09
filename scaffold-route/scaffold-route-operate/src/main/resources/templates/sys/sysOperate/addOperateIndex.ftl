<!DOCTYPE html>
<html>

<head>
    <#include "../../head.ftl">
</head>

<body>
<div class="weadmin-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="we-red">*</span>登录名
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_username" name="userName" lay-verify="required|nikename" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                请设置至少5个字符，将会成为您唯一的登录名
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_sex" class="layui-form-label">角色</label>
            <div class="layui-input-block" id="L_role">
                <#list list as item><input type="radio" name="roleId" value="${item.id!''}" title="${item.name!''}" checked></#list>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="we-red">*</span>姓名
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_realname" name="realName" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                请输入您的真实姓名
            </div>
        </div>

        <div class="layui-form-item">
            <label for="L_email" class="layui-form-label">
                <span class="we-red">*</span>手机
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_phone" name="mobilePhone" lay-verify="required|phone" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_pass" class="layui-form-label">
                <span class="we-red">*</span>密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_pass" name="pwd" required="" lay-verify="pass" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                6到16个字符
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
                <span class="we-red">*</span>确认密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_repass" name="repass" required="" lay-verify="repass" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
            </label>
            <button class="layui-btn" lay-filter="add" lay-submit="">增加</button>
        </div>
    </form>
</div>
<script>
    layui.use(['form', 'layer'], function() {
        $ = layui.jquery;
        var form = layui.form,
                layer = layui.layer;

        //自定义验证规则
        form.verify({
            nikename: function(value) {
                if(value.length < 2) {
                    return '昵称至少得2个字符';
                }
            },
            pass: [/(.+){6,12}$/, '密码必须6到12位'],
            repass: function(value) {
                if($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
        });

        //监听提交
        form.on('submit(add)', function(obj) {
            var f = obj.field;
            // 获得frame索引
            var index = parent.layer.getFrameIndex(window.name);
            //发异步，把数据提交给php
            $.ajax({
                type: 'POST',
                url: _ctx + '/sys/sysOperate/saveOperate',
                data: f,
                success: function (data) {
                    if(data.code !== 0){
                        layer.alert("增加失败");
                        return false;
                    }
                },
                error: function (data) {
                    layer.alert("增加失败");
                    return false;
                }
            });
            window.parent.location.reload();
            //关闭当前frame
            parent.layer.close(index);
        });

    });
</script>
</body>

</html>