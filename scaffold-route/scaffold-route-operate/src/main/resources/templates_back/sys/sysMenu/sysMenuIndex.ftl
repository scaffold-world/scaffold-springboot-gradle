<#assign base=request.contextPath />
<html>
<#include "../../public/head_tab.ftl" >

<body>

<!-- treegrid表格 -->
<table data-toggle="topjui-treegrid"
       data-options="id:'menuTreegrid',
        idField:'id',
        treeField:'name',
        singleSelect:true,
        url: _ctx+'/sys/sysMenu/findSysMenuByPid?parentId=0',
        expandUrl: _ctx+'/sys/sysMenu/findSysMenuByPid?parentId={id}'">
    <thead>
    <tr>
        <th data-options="field:'id',title:'id',checkbox:true"></th>
        <th data-options="field:'name',title:'菜单名称',width:200"></th>
        <th data-options="field:'resourceType',title:'资源类别',formatter: function(value,row,index){
                    if (value == 'menu'){
                        return '菜单';
                    } else if (value == 'window'){
                        return '窗体';
                    } else if (value == 'button'){
                        return '按钮';
                    } else if (value == 'url'){
                        return '链接';
                    } else {
                        return value;
                    }
			    }"></th>
        <th data-options="field:'url',title:'链接地址',width:100"></th>
        <th data-options="field:'pid',title:'父级编号',width:100"></th>
        <th data-options="field:'levelId',title:'层级',width:100,
                    formatter: function(value,row,index){
						if (value == 0){
							return '顶级';
						} else if (value == 1){
							return '子级一层';
						} else if (value == 2){
							return '子级二层';
						} else if (value == 3){
							return '子级三层';
						} else if (value == 4){
							return '子级四层';
						} else if (value == 5){
							return '子级五层';
						} else {
							return '子级N层';
						}
                    }"></th>
        <th data-options="field:'sort',title:'排序',width:100"></th>
        <th data-options="field:'code',title:'代码',width:100"></th>
        <th data-options="field:'status',title:'状态',width:100,
                    formatter: function(value,row,index){
						if (value == 1){
							return '<span style=\'color:green\'>启用</span>';
						} else if (value == 0) {
							return '<span style=\'color:red\'>禁用</span>';
						} else {
						    return '';
						}
                    }"></th>

    </tr>
    </thead>

</table>

<!-- 菜单 表格工具栏 -->
<div id="menuTreegrid-toolbar" class="topjui-toolbar"
     data-options="grid:{
	       type:'treegrid',
           id:'menuTreegrid',
           parentIdField:'pid'
       }" style="display:none">

    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
           extend:'#menuTreegrid-toolbar',
           btnCls:'topjui-btn-green',
           parentGrid:{
               type:'treegrid',
               id:'menuTreegrid',
               unselectedMsg:'请先选中要添加菜单的上级菜单！'
           },
           dialog:{
               id:'menuAddDialog',
               href:_ctx+'/sys/sysMenu/sysMenuEdit?pid={id}',
               buttonsGroup:[
                   {text:'保存',url: _ctx+'/sys/sysMenu/sysMenuSave',iconCls:'fa fa-plus',handler:'ajaxForm'}
               ]
           }">新增</a>

    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
           extend:'#menuTreegrid-toolbar',
           iconCls:'fa fa-pencil',
           btnCls:'topjui-btn-blue',
           parentGrid:{
               type:'treegrid',
               id:'menuTreegrid',
               unselectedMsg:'请先选中要编辑的菜单！'
           },
           dialog:{
               id:'menuEditDialog',
               href:_ctx+'/sys/sysMenu/sysMenuEdit?id={id}',
               buttonsGroup:[
                   {text:'更新',url: _ctx+'/sys/sysMenu/sysMenuSave',iconCls:'fa fa-save',handler:'ajaxForm'}
               ]
           }">编辑</a>


</div>


<script>
    // 自定义方法
    function getChecked() {
        // 提示信息
        $.iMessager.alert('自定义方法', '自定义方法被执行了！', 'messager-info');

        var checkedRows = $('#menuTg').iTreegrid('getChecked');
        console.log(checkedRows);

        var selectedRow = $('#menuTg').iTreegrid('getSelected');
        console.log(selectedRow);
    }
</script>

</body>
</html>