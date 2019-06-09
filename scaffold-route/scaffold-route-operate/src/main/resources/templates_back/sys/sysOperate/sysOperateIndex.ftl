<!DOCTYPE html>
<html>
<#include "../../public/head_tab.ftl"/>
<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div  data-options="region:'center',iconCls:'icon-reload',title:'',fit:false,split:true,border:false,bodyCls:'border_left_right'">
        <table data-toggle="topjui-datagrid"
               data-options="id:'userDatagridId',
                             singleSelect:true,
                             url: _ctx+'/sys/sysOperate/operateList'">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'userName',title:'用户名'"></th>
                <th data-options="field:'realName',title:'真实姓名'"></th>
                <th data-options="field:'mobilePhone',title:'手机号'"></th>
                <th data-options="field:'roleName',title:'角色'"></th>
                <th data-options="field:'status',title:'状态',
                formatter:function(value,row,index){
                    if (value == '0') {
                        return '启用';
                    } else if (value == '1') {
                        return '锁定';
                    } else {
                        return '停用';
                    }
                }"></th>

            </tr>
            </thead>
        </table>
    </div>
</div>

<!--表格工具栏 &ndash;&gt;-->
<div id="userDatagridId-toolbar"
     data-options="grid:{
        type:'datagrid',
        id:'userDatagridId'
     }" class="topjui-toolbar">
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
           extend: '#userDatagridId-toolbar',
           iconCls: 'fa fa-plus',
           btnCls: 'topjui-btn-green',
           dialog:{
               title:'新增',
               id:'articleAddDialog',
               href:_ctx+'/sys/sysOperate/addOperatePage',
               width:700,
               height:500,
               maximizable:false,
               buttonsGroup:[
                   {text:'保存',url: _ctx+'/sys/sysOperate/saveOperate',iconCls:'fa fa-plus',handler:'ajaxForm'}
               ]
       }">开户</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#userDatagridId-toolbar',
            iconCls: 'fa fa-pencil',
            btnCls: 'topjui-btn-red',
            grid: {
                type: 'datagrid',
                id: 'userDatagridId'
            },
            dialog: {
                title:'编辑',
                href: _ctx+'/sys/sysOperate/editOperatePage?operateId={id}',
                width:700,
                height:500,
                buttonsGroup: [
                    {
                        text: '更新',
                        url: _ctx+'/sys/sysOperate/updateOperate',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-green'
                    }
                ]
            }">编辑</a>
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#userDatagridId-toolbar',
            iconCls: 'fa fa-pencil',
            btnCls: 'topjui-btn-purple',
            grid: {
                type: 'datagrid',
                id: 'userDatagridId'
            },
            dialog: {
                title:'修改密码',
                href: _ctx+'/sys/sysOperate/editOperatePwdPage?operateId={id}',
                width:700,
                height:500,
                buttonsGroup: [
                    {
                        text: '更新',
                        url: _ctx+'/sys/sysOperate/updateOperatePwd',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-green'
                    }
                ]
            }">修改密码</a>

    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#userDatagridId-toolbar',
       btnCls:'topjui-btn-brown',
       iconCls:'fa fa-trash',
       url: _ctx+'/sys/sysOperate/resetPwd',
       grid: {uncheckedMsg:'请先勾选要重置的数据',param:'operateId:id'}">重置密码</a>

    <!--查询条件表单&ndash;&gt;-->
    <form id="queryForm" class="search-box">
        <input type="text" name="status" data-toggle="topjui-combobox"
               data-options="id:'status',prompt:'状态',width:100,panelHeight:100,
                     data:[{text: '开启',value: '0' },{text: '锁定',value: '1'},{text: '停用',value: '2'}],
                     icons:[{
                        iconCls:'icon-remove',
                        handler: function(e){
                            $(e.data.target).iCombobox('clear');
                        }
                     }]">
        <a href="javascript:void(0)"
           data-toggle="topjui-menubutton"
           data-options="method:'query',
           iconCls:'fa fa-search',
           btnCls:'topjui-btn-blue',
           form:{id:'queryForm'},
           grid:{type:'datagrid','id':'userDatagridId'}">查询</a>
    </form>
</div>
</body>
</html>
<script type="text/javascript">


</script>