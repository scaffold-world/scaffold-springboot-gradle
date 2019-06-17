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
        <a href="javascript:void(0)">资源管理</a>
        <#--<a><cite>资源管理</cite></a>-->
      </span>
        <a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float: right"
           href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">&#x1002;</i></a>
    </div>
</div>
<div class="weadmin-body">
    <div class="layui-row" style="padding-bottom: 10px">
        <div class="layui-inline">
            <input type="text" name="name" placeholder="请输入资源名" id="menu-name" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-btn-group">
            <button class="layui-btn" onclick="query()"><i class="layui-icon">&#xe615;</i></button>
            <button class="layui-btn" onclick="addMenuDialog('添加菜单',_ctx + '/sys/sysMenu/sysMenuEdit?pid=',750,480)"><i
                        class="layui-icon"></i> 新增
            </button>
            <button class="layui-btn layui-btn-warm" onclick="openAll()"> 展开全部
            </button>
        </div>
    </div>
    <div style="height: 100%">
        <div class="dBody">
            <table class="layui-hidden" id="menuTable" lay-filter="menuTable"
            </table>
        </div>
    </div>
</div>
<script>
    var editObj = null, ptable = null, treeGrid = null, tableId = 'menuTable', layer = null;
    var $ = null;
    layui.use(['jquery', 'treeGrid', 'layer', 'form'], function () {
        $ = layui.jquery;
        var form = layui.form;
        treeGrid = layui.treeGrid;//很重要
        layer = layui.layer;
        ptable = treeGrid.render({
            id: tableId
            , elem: '#' + tableId
            , url: _ctx + '/sys/sysMenu/findAllMenus'
            , idField: 'id'//必須字段
            , treeId: 'id'//树形id字段名称
            , treeUpId: 'pid'//树形父id字段名称
            , treeShowName: 'name'//以树形式显示的字段
            , heightRemove: [".dHead", 10]//不计算的高度,表格设定的是固定高度，此项不生效
            , height: '100%'
            , isFilter: false
            , iconOpen: false//是否显示图标【默认显示】
            , isOpenDefault: false//节点默认是展开还是折叠【默认展开】
            , loading: true
            , method: 'post'
            , isPage: false
            , cols: [[
                {type: 'numbers'}
                , {type: 'radio'}/*
                , {type: 'checkbox', sort: true}*/
                , {field: 'name', width: 200, title: '资源名称', edit: 'text', sort: true}
                , {
                    field: 'resourceType',
                    width: 100,
                    title: '资源类别', <@th type="templet" nid="basics_sys_menu" fieldName="resourceType"></@th>
                }
                , {field: 'url', width: 200, title: '链接地址'}
                , {field: 'sort', width: 80, title: '排序'}
                , {field: 'code', width: 200, title: '代码'}
                , {
                    field: 'status', width: 80, title: '状态', templet: function (d) {
                        if (d.status === 1) {
                            return "启用";
                        } else if (d.status === 0) {
                            return "停用";
                        }
                    }
                }
                , {
                    width: 100, title: '操作', align: 'center', templet: function (d) {
                        var html = '<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>';
                        html += '<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>';
                        return html;
                    }
                }
            ]]
            , parseData: function (res) {//数据加载后回调
                return res;
            }
            , onClickRow: function (index, o) {
            }
            , onDblClickRow: function (index, o) {
            }
            , onCheck: function (obj, checked, isAll) {//复选事件
            }
            , onRadio: function (obj) {//单选事件
            }
        });

        treeGrid.on('tool(' + tableId + ')', function (obj) {
            if (obj.event === 'del') {//删除行
                del(obj);
            } else if (obj.event === "edit") {//编辑行
                WeAdminShow("编辑菜单", _ctx + "/sys/sysMenu/sysMenuEdit?id=" + obj.data.id, 750, 480);
            }
        });

        window.query = function () {
            var menuname = $('#menu-name').val();
            treeGrid.query(tableId, {
                where: {name: menuname}
            });
        };

        window.addMenuDialog = function (title, url, width, height) {
            var data = treeGrid.radioStatus(tableId);
            if (data.id) {
                WeAdminShow(title, url + data.id, width, height);
            } else {
                layer.msg('请先选中父级菜单！', {
                    icon: 1,
                    time: 1000
                });
            }
        }
    });

    function del(obj) {
        layer.confirm("你确定删除数据吗？如果存在下级节点则一并删除，此操作不能撤销！", {icon: 3, title: '提示'},
            function (index) {//确定回调
                var id = obj.data.id;
                $.ajax({
                    url: _ctx + "/sys/sysMenu/deleteMenusById",
                    type: "POST",
                    data: {
                        id: id
                    },
                    success: function (data) {
                        console.log("data.code " + data.code + " " + (data.code === 0));
                        if (data.code === 0) {
                            obj.del();
                            layer.close(index);
                        } else {
                            layer.msg(data.msg, {
                                icon: 1,
                                time: 1000
                            });
                        }
                    }
                });
            }, function (index) {//取消回调
                layer.close(index);
            }
        );
    }


    var i = 1000000;

    //添加
    function add(pObj) {
        var pdata = pObj ? pObj.data : null;
        var param = {};
        param.name = '水果' + Math.random();
        param.id = ++i;
        param.pId = pdata ? pdata.id : null;
        treeGrid.addRow(tableId, pdata ? pdata[treeGrid.config.indexName] + 1 : 0, param);
    }

    function openorclose() {
        var map = treeGrid.getDataMap(tableId);
        var o = map['102'];
        treeGrid.treeNodeOpen(tableId, o, !o[treeGrid.config.cols.isOpen]);
    }


    function openAll() {
        var treedata = treeGrid.getDataTreeList(tableId);
        treeGrid.treeOpenAll(tableId, !treedata[0][treeGrid.config.cols.isOpen]);
    }

    function getCheckData() {
        var checkStatus = treeGrid.checkStatus(tableId)
            , data = checkStatus.data;
        layer.alert(JSON.stringify(data));
    }

    function radioStatus() {
        var data = treeGrid.radioStatus(tableId)
        layer.alert(JSON.stringify(data));
    }

    function getCheckLength() {
        var checkStatus = treeGrid.checkStatus(tableId)
            , data = checkStatus.data;
        layer.msg('选中了：' + data.length + ' 个');
    }

    function reload() {
        treeGrid.reload(tableId, {
            page: {
                curr: 1
            }
        });
    }
</script>

</body>
</html>