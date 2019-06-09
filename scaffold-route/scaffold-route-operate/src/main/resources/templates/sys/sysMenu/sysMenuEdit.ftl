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
                <span class="we-red">*</span>父级资源ID
            </label>
            <div class="layui-input-inline">
                <input type="text" lay-verify="required" autocomplete="off" class="layui-input" value="${sysMenu.pid!''}" readonly>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="we-red">*</span>菜单名称
            </label>
            <div class="layui-input-inline">
                <input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="we-red">*</span>链接地址
            </label>
            <div class="layui-input-inline">
                <input type="text" name="url" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="we-red">*</span>状态
            </label>
            <div class="layui-input-inline">
                <@th type="select" nid="basics_use_status" fieldName="status"></@th>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="we-red">*</span>资源类别
            </label>
            <div class="layui-input-inline">
                <@th type="select" nid="basics_sys_menu" fieldName="resourceType"></@th>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
            </label>
            <button  class="layui-btn" lay-filter="add" lay-submit="">
                提交
            </button>
        </div>
    </form>
</div>
<script>

</script>
</body>

</html>