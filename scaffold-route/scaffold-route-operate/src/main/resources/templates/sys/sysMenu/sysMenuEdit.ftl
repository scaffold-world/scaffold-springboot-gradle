<!DOCTYPE html>
<html>

<head>
    <#include "../../head.ftl">
</head>

<body>
<div class="weadmin-body">
    <form class="layui-form">
        <input type="hidden" name="pid" value="${sysMenu.pid}">
        <input type="hidden" name="id" value="${sysMenu.id!''}">
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="we-red">*</span>菜单名称
            </label>
            <div class="layui-input-inline">
                <input type="text" value="${sysMenu.name!''}" name="name" lay-verify="required|menuName"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="we-red">*</span>链接地址
            </label>
            <div class="layui-input-inline">
                <input type="text" name="url" value="${sysMenu.url!''}" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="we-red">*</span>状态
            </label>
            <div class="layui-input-inline">
                <input type="hidden" id="slct_status" value="${sysMenu.status!''}">
                <@th type="select" nid="basics_use_status" fieldName="status"></@th>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="we-red">*</span>资源类别
            </label>
            <div class="layui-input-inline">
                <input type="hidden" id="slct_resourceType" value="${sysMenu.resourceType!''}">
                <@th type="select" nid="basics_sys_menu" fieldName="resourceType"></@th>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="we-red">*</span>排序
            </label>
            <div class="layui-input-inline">
                <input type="text" name="sort" value="${sysMenu.sort!''}" lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
            </label>
            <button class="layui-btn" lay-filter="edit" lay-submit="">
                提交
            </button>
        </div>
    </form>
</div>
<script>
    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form,
                layer = layui.layer;

        var resourceTypeVal = $('#slct_resourceType').val();
        if(resourceTypeVal){
            $("select[name='resourceType'] option[value="+resourceTypeVal+"]").prop("selected",true);
        }
        var statusVal = $('#slct_status').val();
        if(statusVal){
            $("select[name='status'] option[value="+statusVal+"]").prop("selected",true);
        }
        form.render('select');
        //自定义验证规则
        form.verify({
            menuName: function (value) {
                if (value.length < 2) {
                    return '昵称至少得2个字符';
                }
            },
            pass: [/(.+){6,12}$/, '密码必须6到12位'],
            repass: function (value) {
                if ($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
        });

        //监听提交
        form.on('submit(edit)', function (obj) {
            var f = obj.field;
            // 获得frame索引
            var index = parent.layer.getFrameIndex(window.name);
            //发异步，把数据提交给php
            $.ajax({
                type: 'POST',
                url: _ctx + '/sys/sysMenu/sysMenuSave',
                data: f,
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg('已更新!', {
                            time: 1000
                        });
                    }
                },
                error: function (data) {
                    layer.alert("操作失败：" + data.msg);
                    return false;
                }
            });
            //关闭当前frame
            parent.layer.close(index);
            window.parent.location.reload();
        });

    });
</script>
</body>

</html>