<!DOCTYPE html>
<html>
<#include "../../public/head_tab.ftl"/>
<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'center',iconCls:'icon-reload',title:'',fit:false,split:true,border:false,bodyCls:'border_left_right'">
        <table data-toggle="topjui-datagrid"
               data-options="id:'datagridId',
                             singleSelect:true,
                             url: _ctx+'/job/jobManager/getJobPageList'">
            <thead>
            <tr>
                <th data-options="field:'id',title:'编号'"></th>
                <th data-options="field:'jobName',title:'任务名称'"></th>
                <th data-options="field:'jobGroup',title:'任务分组'"></th>
                <th data-options="field:'jobDescription',title:'任务描述'"></th>
                <th data-options="field:'cronExpression',title:'cron表达式'"></th>
                <th data-options="field:'beanClass',title:'类名'"></th>
                <th data-options="field:'methodName',title:'方法名'"></th>
                <th data-options="field:'paramJson',title:'参数'"></th>
                <th data-options="field:'isConcurrentName',title:'是否可以并发'"></th>
                <th data-options="field:'jobStatusName',title:'任务状态'"></th>
                <th data-options="field:'startWithrun',title:'是否随程序重启',formatter:function(value,row,index){
                        if (value == '0') {
                            return '否';
                        } else if (value == '1') {
                            return '是';
                        }
                    }"></th>
                <th data-options="field:'createTime',title:'添加时间'"></th>
                <th data-options="field:'operate',title:'操作',sortable:true,formatter:operateFormatter"></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<!-- 表格工具栏 -->
<div id="datagridId-toolbar" style="height: 30px; padding: 4px; text-align: left;"
     data-options="grid:{
        type:'datagrid',
        id:'datagridId'
     }" class="topjui-toolbar">

    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
       extend: '#datagridId-toolbar',
       iconCls: 'fa fa-plus',
       btnCls: 'topjui-btn-green',
       dialog:{
           id:'activityAwardAddDialog',
           title:'新增任务配置',
           href:_ctx+'/job/jobManager/addTaskPage',
                width:700,
                height:450,
           buttonsGroup:[{
                    text:'测试远程调用',
                    iconCls:'fa fa-star',
                    handler:function(){
                        var obj = $('#activityAwardAddDialog').serializeObject();
                        $.ajax({
                            type: 'POST',
                            url: _ctx+'/job/jobManager/testExecuteTheMethod',
                            data: obj,
                            dataType: 'json',
                            success: function (data) {
                                if (data.code == 200) {
                                    $.iMessager.show({
                                        title: '提示',
                                        msg: '已执行，请查看日志！'
                                    });
                                } else {
                                    $.iMessager.alert(data.title,data.msg,'error');
                                }
                            },
                            error: function () {
                                $.iMessager.alert('提示信息','网络错误','error');
                            }
                        });
                    }
               },
               {
                    text:'新增并启动',
                    url: _ctx+'/job/jobManager/addJob',
                    iconCls:'fa fa-plus',
                    handler:'ajaxForm',
                    btnCls:'topjui-btn-brown'
               }
           ]
       }">新增</a>

    <!--查询条件表单-->
    <form id="queryForm" class="search-box">

        <input type="text" name="jobName" data-toggle="topjui-textbox"
               data-options="id:'jobName',prompt:'任务名称',width:150">


        <a href="javascript:void(0)"
           data-toggle="topjui-menubutton"
           data-options="method:'query',
           iconCls:'fa fa-search',
           btnCls:'topjui-btn-blue',
           form:{id:'queryForm'},
           grid:{type:'datagrid','id':'datagridId'}">查询</a>
        <a href="#"
           data-toggle="topjui-linkbutton"
           data-options="id:'submitBtn',
                   iconCls:'fa fa-trash',
                   btnCls:'topjui-btn-red',
                   form:{
                       id:'queryForm',
                       method:'clear'
                   }">清空</a>

    </form>
</div>
</body>
</html>
<script type="application/javascript">
    function operateFormatter(value, row, index) {
        var htmlstr = ' <button class="layui-btn layui-btn-xs" onclick="restart(\'' + row.id + '\')"> 启动 </button> ';
        htmlstr += ' <button class="layui-btn layui-btn-xs layui-btn-danger" onclick="stop(\'' + row.id + '\')"> 停止 </button> ';
        htmlstr += ' <button class="layui-btn layui-btn-xs layui-btn-danger" onclick="deleteById(\'' + row.id + '\')"> 删除 </button> ';
        htmlstr += ' <button class="layui-btn layui-btn-xs layui-btn-normal" onclick="update(\'' + row.id + '\')"> 更新 </button> ';
        htmlstr += ' <button class="layui-btn layui-btn-xs layui-btn-danger" onclick="runOneTime(\'' + row.id + '\')"> 立即执行一次 </button> ';
        return htmlstr;
    }

    function runOneTime(id){
        $.ajax({
            type: "POST",
            url:_ctx + "/job/jobManager/runAJobNowOnce",
            data: {id:id},
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    // 提示信息
                    $.iMessager.show({
                        title: '提示',
                        msg: '已执行，请查看日志！'
                    });
                } else {
                    $.iMessager.alert(data.title,data.msg,'error');
                }
            },
            error: function () {
                $.iMessager.alert("提示信息","网络错误",'error');
            }
        });
    }

    function deleteById(id) {
        $.ajax({
            type: "POST",
            url:_ctx + "/job/jobManager/delete",
            data: {id:id},
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    // 提示信息
                    $('#datagridId').iDatagrid('reload');
                } else {
                    $.iMessager.alert(data.title,data.msg,'info');
                }
            },
            error: function () {
                $.iMessager.alert("提示信息","网络错误",'error');
            }
        });
    }

    function stop(id) {
        $.ajax({
            type: "POST",
            url:_ctx + "/job/jobManager/stopOneJob",
            data: {id:id},
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    // 提示信息
                    $('#datagridId').iDatagrid('reload');
                } else {
                    $.iMessager.alert(data.title,data.msg,'info');
                }
            },
            error: function () {
                $.iMessager.alert("提示信息","网络错误",'error');
            }
        });
    }

    function restart(id) {
        $.ajax({
            type: "POST",
            url:_ctx + "/job/jobManager/restartOneJob",
            data: {id:id,status:1},
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    // 提示信息
                    $('#datagridId').iDatagrid('reload');
                } else {
                    $.iMessager.alert(data.title,data.msg,'info');
                }
            },
            error: function () {
                $.iMessager.alert("提示信息","网络错误",'error');
            }
        });
    }

    // 修改cron表达式
    function update(id) {
        var $editDialog = $('<form></form>');
        $editDialog.iDialog({
            title: '更新任务配置',
            width: 450,
            height: 300,
            closed: false,
            href: _ctx+'/job/jobManager/updateTaskPage?id=' + id,
            buttons: [{
                text: '保存',
                iconCls: 'fa fa-save',
                btnCls: 'topjui-btn-blue',
                handler: function () {
                    var obj = $editDialog.serializeObject();
                    $.ajax({
                        type: 'POST',
                        url: _ctx+'/job/jobManager/updateJob',
                        data: obj,
                        success: function (data) {
                            if (data.code == 200) {
                                // 提示信息
                                $editDialog.iDialog('close');
                                $('#datagridId').iDatagrid('reload');
                            } else {
                                $.iMessager.alert(data.title, data.msg, 'info');
                            }
                        },
                        error: function () {
                            $.iMessager.alert("提示信息", "网络错误", 'error');
                        }
                    });
                }
            }, {
                text: '关闭',
                iconCls: 'fa fa-close',
                btnCls: 'topjui-btn-red',
                handler: function () {
                    $editDialog.iDialog('close');
                }
            }]
        });
    }
</script>
