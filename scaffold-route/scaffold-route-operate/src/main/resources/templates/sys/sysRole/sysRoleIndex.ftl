<#assign base=request.contextPath />
<!doctype html>
<html lang="en">

<head>
    <#include "../../head.ftl">
</head>

<body>
<div class="weadmin-nav">
    <div class="dHead">
        <span class="layui-breadcrumb">
        <a href="javascript:void(0)">权限配置</a>
        <a href="javascript:void(0)">角色管理</a>
      </span>
        <a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float: right"
           href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">&#x1002;</i></a>
    </div>
</div>
<div class="weadmin-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 we-search">
            角色搜索：
            <div class="layui-inline">
                <@th type="select" nid="basics_use_status" fieldName="status"></@th>
            </div>
            <div class="layui-inline">
                <input type="text" name="userName" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
            <button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <div class="weadmin-block">
        <button class="layui-btn" onclick="WeAdminShow('添加角色',_ctx + '/sys/sysRole/addOperateIndex',750,480)"><i
                    class="layui-icon"></i>添加
        </button>
    </div>
    <table class="layui-table"
           lay-data="{height:315, url: _ctx + '/sys/sysRole/operateList', page:true, id:'operateTable'}"
           lay-filter="operateTable">
        <thead>
        <tr>
            <th lay-data="{field:'id', sort: true}">ID</th>
            <th lay-data="{field:'userName'}">用户名</th>
            <th lay-data="{field:'realName'}">真实姓名</th>
            <th lay-data="{field:'mobilePhone'}">手机号</th>
            <th lay-data="{field:'status',<@th type="templet" nid="basics_use_status" fieldName="status"></@th>}">状态
            </th>
            <th lay-data="{field:'roleName'}">角色</th>
            <th lay-data="{field:'operate', templet:function(d){return operateTemplate(d);}}">操作</th>
        </tr>
        </thead>
    </table>
</div>
<script type="text/javascript">
    layui.use(['laydate', 'jquery', 'admin', 'table', 'form'], function () {
        var laydate = layui.laydate, $ = layui.jquery, admin = layui.admin, table = layui.table, form = layui.form;

        window.operateTemplate = function (d) {
            var status = d.status;
            var html = "";
            if (status === 1) {
                html +=  "<button class='layui-btn layui-btn-xs layui-btn-radius' onclick='member_stop(" + d.id + ",2)'>停用</button>";
            } else {
                html +=  "<button class='layui-btn layui-btn-xs layui-btn-radius' onclick='member_stop(" + d.id + ",1)'>启用</button>";
            }
            html +=  "<button class='layui-btn layui-btn-xs layui-btn-normal' onclick='member_del(this," + d.id + ")'>删除</button>";
            return html;
        };

        form.on('submit(sreach)', function (data) {
            var obj = data.field;
            tableReload(obj);
            return false;
        });

        window.tableReload = function (searchData) {

            table.reload('operateTable', {
                page: {
                    curr: 1
                },
                where: searchData
            });
        };

        /*用户-停用*/
        window.member_stop = function (id, status) {
            var confirmTip;
            if (status === 2) {
                confirmTip = '确认要停用吗？';
            } else {
                confirmTip = '确认要启用吗？';
            }
            layer.confirm(confirmTip, function () {
                $.ajax({
                    url: _ctx + "/sys/sysRole/updateStatus",
                    data: {
                        id: id,
                        status: status
                    },
                    type: "POST",
                    success: function (data) {
                        if (data.code === 0) {
                            layer.msg('已更新!', {
                                time: 1000
                            });
                            tableReload(null);
                        } else {
                            layer.msg('错误：' + data.msg, {
                                icon: 5,
                                time: 1000
                            });
                        }
                    }
                });
            });
        }

        window.member_del = function (obj, id) {
            layer.confirm('确认要删除吗？', function(index) {
                //发异步删除数据
                $.ajax({
                    url: _ctx + "/sys/sysRole/deleteRole",
                    data: {
                        id: id
                    },
                    type: "POST",
                    success: function (data) {
                        if (data.code === 0) {
                            $(obj).parents("tr").remove();
                            layer.msg('已删除!', {
                                icon: 1,
                                time: 1000
                            });
                        } else {
                            layer.msg('错误：' + data.msg, {
                                icon: 5,
                                time: 1000
                            });
                        }
                    }
                });
            });
        }
    });

</script>
</body>

</html>