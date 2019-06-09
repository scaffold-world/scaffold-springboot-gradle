<#--<input type="hidden" name="id" value="${sysMenu.id!''}">

<table class="editTable">
    <tr>
        <td class="label">父级资源</td>
        <td>
            <input type="text" name="pid" data-toggle="topjui-combotreegrid" value="${sysMenu.pid!''}" readonly="readonly"
                   data-options="required:true,
                   expandAll:false,
                   idField:'id',
                   treeField:'name',
                   width:450,
                   panelHeight:250,
                   fitColumns:true,
                   columns:[[
                       {field:'name',title:'名称',width:100}
                   ]],
                   url: _ctx+'/sys/sysMenu/findSysMenuByPid?parentId=0',
                   expandUrl: _ctx+'/sys/sysMenu/findSysMenuByPid?parentId={id}',
                   getFatherIdsUrl: _ctx+'/sys/sysMenu/findFatherIds?id={id}'">

        </td>
    </tr>
    <tr>
        <td class="label">资源类型</td>
        <td>
            <input type="text" name="resourceType"  data-toggle="topjui-combobox" value="${sysMenu.resourceType!''}"
                   data-options="
                    required:true,
                    width:450,
                    valueField:'value',
                    data: [
                        {
                            text: '菜单',
                            value: 'menu'
                        },
                        {
                            text: '窗体',
                            value: 'window'
                        },
                        {
                            text: '按钮',
                            value: 'button'
                        },
                        {
                            text: '链接',
                            value: 'url'
                        }
                    ]">
        </td>
    </tr>
    <tr>
        <td class="label">资源名称</td>
        <td><input type="text" name="name" data-toggle="topjui-textbox" value="${sysMenu.name!''}"
                   data-options="required:true,width:450"></td>
    </tr>
    <tr>
        <td class="label">资源地址或标识</td>
        <td><input type="text" name="url" data-toggle="topjui-textbox" value="${sysMenu.url!''}"
                   data-options="width:450"></td>
    </tr>
    <tr>
        <td class="label">是否可展示</td>
        <td>
            <input type="text" name="state"
                   data-toggle="topjui-combobox"
                   value="${sysMenu.state!''}"
                   data-options="required:true,
                   textField:'text',
                   valueField:'value',
                   data: [
                        {
                            text: '展示',
                            value: 'closed'
                        },
                        {
                            text: '关闭',
                            value: 'open'
                        }
                   ]"">
        </td>
    </tr>
    <tr>
        <td class="label">状态</td>
        <td>
            <input type="text" name="status"
                   data-toggle="topjui-combobox"
                   value="${sysMenu.status!''}"
                   data-options="required:true,
                   textField:'text',
                   valueField:'value',
                   data: [
                        {
                            text: '启用',
                            value: '1'
                        },
                        {
                            text: '禁用',
                            value: '0'
                        }
                   ]"">
        </td>
    </tr>
    <tr>
        <td class="label">资源图标</td>
        <td><input type="text" name="iconCls" data-toggle="topjui-textbox" value="${sysMenu.iconCls!''}"
                   data-options="width:450"></td>
    </tr>
    <tr>
        <td class="label">资源排序</td>
        <td><input type="text" name="sort" data-toggle="topjui-numberspinner" value="${sysMenu.sort!''}"
                   data-options="required:true,width:450">
        </td>
    </tr>

</table>-->

<div data-toggle="topjui-layout" data-options="fit:true">
    <div class="topjui-fluid">
        <fieldset>
            <legend>基本信息</legend>
        </fieldset>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">父级资源</label>
                <div class="topjui-input-block">
                    <input type="hidden" name="id" value="${sysMenu.id!''}">
                    <input type="text" name="pid" data-toggle="topjui-combotreegrid" value="${sysMenu.pid!''}" readonly="readonly"
                           data-options="required:true,expandAll:false,
                           prompt:'父级资源',idField:'id',treeField:'name',
                          panelHeight:250,fitColumns:true,
                           columns:[[
                               {field:'name',title:'名称',width:100}
                           ]],
                           url: _ctx+'/sys/sysMenu/findSysMenuByPid?parentId=0',
                           expandUrl: _ctx+'/sys/sysMenu/findSysMenuByPid?parentId={id}',
                           getFatherIdsUrl: _ctx+'/sys/sysMenu/findFatherIds?id={id}'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">资源类型</label>
                <div class="topjui-input-block">
                    <input type="text" name="resourceType" data-toggle="topjui-combobox" value="${sysMenu.resourceType!''}"
                           data-options="required:true,prompt:'资源类型',panelHeight:150,
                            textField:'text',
                            valueField:'value',
                            data: [{text: '菜单',value: 'menu'},
                                {text: '窗体',value: 'window'},
                                {text: '按钮',alue: 'button'},
                                {text: '链接',value: 'url'}]"/>
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">资源名称</label>
                <div class="topjui-input-block">
                    <input type="text" name="name" data-toggle="topjui-textbox" value="${sysMenu.name!''}"
                           data-options="required:true,prompt:'资源名称'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">是否可展示</label>
                <div class="topjui-input-block">
                    <input type="text" name="state"
                           data-toggle="topjui-combobox"
                           value="${sysMenu.state!''}"
                           data-options="required:true,textField:'text',valueField:'value',prompt:'是否可展示',panelHeight:100,
                            data: [{text: '展示',value: 'closed'},{text: '关闭',value: 'open'}]">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">状态</label>
                <div class="topjui-input-block">
                    <input type="text" name="status"
                           data-toggle="topjui-combobox"
                           value="${sysMenu.status!''}"
                           data-options="required:true,textField:'text',valueField:'value',prompt:'状态',panelHeight:100,
                            data: [{text: '启用',value: '1'},{text: '禁用',value: '0'}]">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">资源图标</label>
                <div class="topjui-input-block">
                    <input type="text" name="iconCls" data-toggle="topjui-textbox" value="${sysMenu.iconCls!''}"
                           data-options="prompt:'资源图标'">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm8">
                <label class="topjui-form-label">资源地址或标识</label>
                <div class="topjui-input-block">
                    <input type="text" name="url" data-toggle="topjui-textbox" value="${sysMenu.url!''}"
                           data-options="prompt:'资源地址或标识'">
                </div>
            </div>
            <div class="topjui-col-sm4">
                <label class="topjui-form-label">资源排序</label>
                <div class="topjui-input-block">
                    <input type="text" name="sort" data-toggle="topjui-numberspinner" value="${sysMenu.sort!''}"
                           data-options="required:true,prompt:'资源排序'">
                </div>
            </div>
        </div>


    </div>
</div>