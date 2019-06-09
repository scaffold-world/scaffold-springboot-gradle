<!DOCTYPE html>
<html>
<head>
<#include "../../public/head_index.ftl"/>
</head>

<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'west',title:'',split:true,border:false,width:'60%',iconCls:'fa fa-sitemap',headerCls:'border_right',bodyCls:'border_right'">
        <!-- datagrid表格 -->
        <table data-toggle="topjui-datagrid"
               data-options="id:'userDg',
                    idField:'id',
                    url: _ctx+'/sys/sysRole/sysRoleIndex',
                    childGrid:{
                        param:'roleId:id',
                        grid:[
                            {type:'treegrid',id:'orgnizationDatagrid',syncReload:false},
                        ]
			        },
                    filter: [{
                        field: 'userName',
                        type: 'textbox',
                        op: ['contains', 'equal', 'notequal', 'less', 'greater']
                    },{
                        field: 'sex',
                        type: 'combobox',
                        options: {
                            valueField: 'value',
                            textField: 'label',
                            data: [{
                                label: '男',
                                value: '1'
                            }, {
                                label: '女',
                                value: '2'
                            }]
                        },
                        op: ['contains', 'equal', 'notequal', 'less', 'greater']
                    },{
                        field: 'post',
                        type: 'combobox',
                        options: {
                            valueField: 'value',
                            textField: 'label',
                            multiple: true,
                            data: [{
                                label: 'CEO',
                                value: 'CEO'
                            }, {
                                label: 'COO',
                                value: 'COO'
                            }, {
                                label: 'CTO',
                                value: 'CTO'
                            }]
                        },
                        op: ['contains', 'equal', 'notequal', 'less', 'greater']
                    }]">
            <thead>
            <tr>
                <th data-options="field:'id',title:'id',checkbox:true"></th>
                <th data-options="field:'name',title:'角色名',sortable:true"></th>
                <th data-options="field:'remark',title:'描述',sortable:true"></th>
                <th data-options="field:'addTime',title:'添加时间',sortable:true,
                    formatter:function(value,row,index){
                    if(value != null){
                    return timestamp2Datetime(value,'Y-m-d');
                }
                }"
                ></th>
                <th data-options="field:'updateTime',title:'更新时间',sortable:true,
                   formatter:function(value,row,index){
                    if(value != null){
                    return timestamp2Datetime(value,'Y-m-d');
                }
                }

                "></th>
                <th data-options="field:'status',title:'状态',sortable:true,
                    formatter:function(value,row,index){
                        if (value == '1') {
                            return '启用';
                        } else if (value == '2') {
                            return '锁定';
                        } else {
                            return '停用';
                        }
                    }"></th>

            </tr>
            </thead>
        </table>
    </div>
    <div data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:false,bodyCls:'border_left'">

        <!-- treegrid表格 -->
        <table data-toggle="topjui-treegrid"
               data-options="id:'orgnizationDatagrid',
			   idField:'id',
			   treeField:'text',
			   singleSelect:false,
			   url: _ctx+'/sys/sysMenu/allMenus',
			   expandUrl: _ctx+'/sys/sysMenu/allMenus'
			">
            <thead>
            <tr>
                <th data-options="field:'uuid',title:'uuid',checkbox:true"></th>
                <th data-options="field:'text',title:'菜单名称'"></th>
                <th data-options="field:'autoStatus',title:'状态',sortable:true,
                    formatter:function(value,row,index){
                        if (value == '1') {
                            return '已授权';
                        }else if(value=='0'){
                            return '未授权';
                        }
                      }"
                ></th>

            </tr>
            </thead>
        </table>


    </div>
</div>

<!-- 表格工具栏开始 -->
<div id="userDg-toolbar" class="topjui-toolbar"
     data-options="grid:{
           type:'datagrid',
           id:'userDg'
       }">
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
       extend: '#userDg-toolbar',
       iconCls: 'fa fa-plus',
       btnCls: 'topjui-btn-green',
       dialog:{
           id:'userAddDialog',
           href:_ctx+'/sys/sysRole/addRole',
           width:500,
           height:400,
           buttonsGroup:[
               {text:'保存',url:'saveRole',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-brown'}
           ]
       }">新增</a>

</div>
<div id="orgnizationDatagrid-toolbar" class="topjui-toolbar"
     data-options="grid:{
           type:'datagrid',
           id:'orgnizationDatagrid'
       }">
    <a href="javascript:void(0)" data-toggle="topjui-menubutton" data-options="method:'doAjax',
	   iconCls:'fa fa-plus',
	   btnCls:'topjui-btn-green',
	   url: _ctx+'/sys/sysRoleMenu/addRoleMenu?roleId={parent.id}',
   	   confirmMsg:'确定要执行该操作吗？',
   	   parentGrid:{
   	       type:'treegrid',
	   	   id:'userDg',
	   	   uncheckedMsg:'请勾选左边的分组'
   	   },
	   grid:{
	       type:'treegrid',
	   	   id:'orgnizationDatagrid',
           parentIdField:'id',
           uncheckedMsg:'请选中授权的菜单或操作',
	   }" >赋予授权</a>


    <a href="javascript:void(0)" data-toggle="topjui-menubutton" data-options="method:'doAjax',
	   iconCls:'fa fa-plus',
	   btnCls:'topjui-btn-green',
	   url: _ctx+'/sys/sysRoleMenu/deleteMenu?roleId={parent.id}',
   	   confirmMsg:'确定要执行该操作吗？',
   	   parentGrid:{
   	       type:'treegrid',
	   	   id:'userDg',
	   	   uncheckedMsg:'请勾选左边的分组'
   	   },
	   grid:{
	       type:'treegrid',
	   	   id:'orgnizationDatagrid',
           parentIdField:'id',
           uncheckedMsg:'请选中授权的菜单或操作',
	   }">删除授权</a>

</div>
</div>
<!-- 表格工具栏结束 -->
</body>
</html>