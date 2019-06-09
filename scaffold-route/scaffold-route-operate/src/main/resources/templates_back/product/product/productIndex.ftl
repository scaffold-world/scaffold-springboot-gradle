<!DOCTYPE html>
<html>
<#include "../../public/head_tab.ftl"/>
<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'center',iconCls:'icon-reload',title:'',fit:false,split:true,border:false,bodyCls:'border_left_right'">
        <table data-toggle="topjui-datagrid"
               data-options="id:'productDatagridId',
                             singleSelect:true,
                             url: _ctx+'/product/product/productPageList'">
            <thead>
            <tr>
                <th data-options="field:'id',title:'ID',width:80"></th>
                <th data-options="field:'name',title:'名称',width:200"></th>
                <th data-options="field:'linkNum',title:'链接数量'"></th>
                <th data-options="field:'sort',title:'权重',editor:{type:'numberbox',options:{required:true,height:28}}"></th>
                <th data-options="field:'productTagName',title:'产品标签'"></th>
                <th data-options="field:'remark',title:'描述'"></th>
                <th data-options="field:'amountLimitDesc',title:'额度'"></th>
                <th data-options="field:'loanTerm',title:'借款期限'"></th>
                <th data-options="field:'interestRateDesc',title:'利率'"></th>
                <th data-options="field:'receiveMoneyDesc',title:'下款时间',width:260"></th>
                <th data-options="field:'loanSuccessNumber',title:'借款成功人数'"></th>
                <@th field='onshelfStatus' title='状态' nid='basis_onshelf_status'></@th>
                <th data-options="field:'handle',title:'操作',sortable:true,formatter:operateFormatter,align:'center'"></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div id="productDatagridId-toolbar"
     data-options="grid:{
        type:'datagrid',
        id:'productDatagridId'
     }" class="topjui-toolbar">

    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
           extend: '#productDatagridId-toolbar',
           iconCls: 'fa fa-plus',
           btnCls: 'topjui-btn-green',
           dialog:{
               title:'新增产品',
               id:'articleAddDialog',
               href:_ctx+'/product/product/productAdd',
               width:800,
               height:700,
               maximizable:false,
               buttonsGroup:[
                   {text:'保存',url: _ctx+'/product/product/productSave',iconCls:'fa fa-plus',handler:'ajaxForm'}
               ]
       }">新增产品</a>

    <!--查询条件表单-->
    <form id="queryForm" class="search-box">
        <input type="text" name="uuid" data-toggle="topjui-textbox"
               data-options="id:'uuid',prompt:'产品ID',width:150">
        <input type="text" name="name" data-toggle="topjui-textbox"
               data-options="id:'name',prompt:'名称',width:150">
        <input type="text" name="app" data-toggle="topjui-textbox"
               data-options="id:'app',prompt:'app',width:150">
        <input type="text" name="onshelfStatus" data-toggle="topjui-combobox"
               data-options="id:'onshelfStatus',prompt:'状态',width:200,panelHeight:200,value:'',
                url: _ctx+'/sys/sysDict/findDictValues?selectName=basis_onshelf_status',
                 onLoadSuccess: function(data){
                    $('#onshelfStatus').iCombobox('setValue', '');
                    $('#onshelfStatus').iCombobox('setText', '');
                 },
               icons:[{
                    iconCls:'icon-remove',
                    handler: function(e){
                        $(e.data.target).iCombobox('clear');
                        $(e.data.target).iCombobox('setValue', '');
                        $(e.data.target).iCombobox('setText', '');
                    }
                }]">
        <a href="javascript:void(0)"
           data-toggle="topjui-menubutton"
           data-options="method:'query',
           iconCls:'fa fa-search',
           btnCls:'topjui-btn-blue',
           form:{id:'queryForm'},
           grid:{type:'datagrid','id':'productDatagridId'}">查询</a>
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

<script>
    </script>
</body>
</html>