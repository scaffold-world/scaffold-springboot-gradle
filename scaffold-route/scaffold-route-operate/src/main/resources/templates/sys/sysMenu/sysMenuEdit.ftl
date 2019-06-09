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
    </form>
</div>
<script>

</script>
</body>

</html>