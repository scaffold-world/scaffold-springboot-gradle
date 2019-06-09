<!DOCTYPE html>
<html>
<#include "../../public/head_tab.ftl"/>
<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div  data-options="region:'center',iconCls:'icon-reload',title:'',fit:false,split:true,border:false,bodyCls:'border_left_right'">
        <table data-toggle="topjui-datagrid"
               data-options="id:'configDatagridId',
                             singleSelect:true,
                             url: _ctx+'/sys/sysConfig/sysConfigIndexList'"">
        <thead>
        <tr>
            <th data-options="field:'id',title:'id',checkbox:true"></th>
            <th data-options="field:'name',title:'名称'"></th>
            <th data-options="field:'nid',title:'标识'"></th>
            <th data-options="field:'value',title:'值'"></th>
            <@th field='type' title='类型' width='120' nid='basics_sys_config_type'></@th>
            <@th field='status' title='状态' width='80' nid='basics_use_status'></@th>
            <th data-options="field:'remark',width:150,title:'说明'"></th>
        </tr>
        </thead>
        </table>
    </div>
</div>

<!--表格工具栏 &ndash;&gt;-->
<div id="configDatagridId-toolbar" style="height: 30px; padding: 4px; text-align: left;"
     data-options="grid:{
        type:'datagrid',
        id:'configDatagridId'
     }" class="topjui-toolbar">

    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#configDatagridId-toolbar',
            iconCls: 'fa fa-pencil',
            btnCls: 'topjui-btn-red',
            grid: {
                type: 'datagrid',
                id: 'configDatagridId'
            },
            dialog: {
                title:'编辑参数设置',
                href: _ctx+'/sys/sysConfig/sysConfigEditPage?sysConfigId={id}',
                width:700,
                height:500,
                buttonsGroup: [
                    {
                        text: '更新',
                        url: _ctx+'/sys/sysConfig/sysConfigUpdate',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-brown'
                    }
                ]
            }">编辑</a>

    <!--查询条件表单&ndash;&gt;-->
    <form id="queryForm" class="search-box" >
        <input type="text" name="name" data-toggle="topjui-textbox"
               data-options="id:'name',prompt:'名称',width:150">
        <input type="text" name="nid" data-toggle="topjui-textbox"
               data-options="id:'nid',prompt:'标识',width:150">
        <input type="text" name="type" data-toggle="topjui-combobox"
               data-options="id:'type',valueField:'value',textField:'text',prompt:'类型',width:150,
               url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_sys_config_type',
               icons:[{
                    iconCls:'icon-remove',
                    handler: function(e){
                        $(e.data.target).iCombobox('clear');
                    }
                }]">
        <input type="text" name="status" data-toggle="topjui-combobox"
               data-options="id:'status',valueField:'value',textField:'text',prompt:'状态',width:150,panelHeight:100,
               url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_use_status',
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
           grid:{type:'datagrid','id':'configDatagridId'}">查询</a>
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

<script>
</script>
</html>