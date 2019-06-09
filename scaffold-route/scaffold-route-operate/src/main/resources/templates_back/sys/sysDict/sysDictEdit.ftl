<#--<input type="hidden" name="id" value="${sysDict.id!''}">

<table class="editTable">
    <tr>
        <td class="label">父级资源:</td>
        <td>
            <input type="text" name="pid" data-toggle="topjui-combotreegrid" value="${sysDict.pid!''}" readonly="readonly"
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
                   url: _ctx+'/sys/sysDict/findSysDictByPid?parentId=0',
                   expandUrl: _ctx+'/sys/sysDict/findSysDictByPid?parentId={id}',
                   getFatherIdsUrl: _ctx+'/sys/sysDict/findFatherIds?id={id}'">

        </td>
    </tr>
    <tr>
        <td class="label">字典类型:</td>
        <td><input id="dictType" type="text" name="type" data-toggle="topjui-combobox" value="${sysDict.type!''}"
                  data-options="required:true,
                    textField:'text',
                    valueField:'value',
                    url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_dict_type',
                    onSelect: function(rec){
                        if(rec.value==1){
                            $('#dictNid').iTextbox({
                                prompt:'字典类型为目录，必填值',
                                required:true,
                                readonly:false
                            });
                            $('#dictValue').iTextbox({
                                value:'',
                                prompt:'字典类型为目录，不填值',
                                required:false,
                                readonly:true
                            });
                            $('#dictCode').iTextbox({
                                value:'',
                                prompt:'字典类型为目录，不填值',
                                required:false,
                                readonly:true
                            });
                            $('#dictJavaType').iTextbox({
                                value:'',
                                prompt:'字典类型为目录，不填值',
                                required:false,
                                readonly:true
                            });
                        }else{
                             $('#dictNid').iTextbox({
                                value:'',
                                prompt:'字典类型为值，不填值',
                                required:false,
                                readonly:true
                            });
                            $('#dictValue').iTextbox({
                                prompt:'字典类型为值，必填值',
                                required:true,
                                readonly:false
                            });
                            $('#dictCode').iTextbox({
                                prompt:'字典类型为值，必填值',
                                required:true,
                                readonly:false
                            });
                            $('#dictJavaType').iTextbox({
                                prompt:'字典类型为值，必填值',
                                required:true,
                                readonly:false
                            });
                        }
             }"></td>
    </tr>
    <tr>
        <td class="label">名称:</td>
        <td><input  type="text" name="name" data-toggle="topjui-textbox" value="${sysDict.name!''}"
                   data-options="required:true,width:450"></td>
    </tr>
    <tr>
        <td class="label">nid:</td>
        <td><input id="dictNid" type="text" name="nid" data-toggle="topjui-textbox" value="${sysDict.nid!''}"
                   data-options="width:450"></td>
    </tr>
    <tr>
        <td class="label">值:</td>
        <td><input id="dictValue" type="text" name="value" data-toggle="topjui-textbox" value="${sysDict.value!''}"
                   data-options="width:450"></td>
    </tr>
    <tr>
        <td class="label">代码:</td>
        <td><input id="dictCode" type="text" name="code" data-toggle="topjui-textbox" value="${sysDict.code!''}"
                   data-options="required:true,width:450"></td>
    </tr>
    <tr>
        <td class="label">java类型:</td>
        <td>
            <input id='dictJavaType' type="text" name="javaType" data-toggle="topjui-combobox"
                   value="${(sysDict.javaType)!''}"
                   data-options="
            required:true,
            width:450,
            valueField:'value',
            url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_java_type'">
        </td>
    </tr>
    <tr>
        <td class="label">状态:</td>
        <td>
            <input type="text" name="status"
                   data-toggle="topjui-combobox"
                   value="${sysDict.status!'1'}"
                   data-options="required:true,
                   textField:'text',
                   valueField:'value',
                   url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_use_status'">
        </td>
    </tr>

    <tr>
        <td class="label">字典排序:</td>
        <td><input type="text" name="sort" data-toggle="topjui-numberspinner" value="${sysDict.sort!''}"
                   data-options="width:450">
        </td>
    </tr>


</table>-->

<div data-toggle="topjui-layout" data-options="fit:true" style="overflow-y: scroll">
    <div class="topjui-fluid" style="padding: 0; margin-right: 30px;">
        <fieldset>
            <legend>添加信息</legend>
        </fieldset>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">父级资源</label>
                <div class="topjui-input-block">
                    <input type="hidden" name="id" value="${sysDict.id!''}">
                    <input type="text" name="pid" data-toggle="topjui-combotreegrid" value="${sysDict.pid!''}" readonly="readonly"
                           data-options="required:true,expandAll:false,idField:'id',prompt:'父级资源',
                           treeField:'name',
                           panelHeight:250,
                           fitColumns:true,
                           columns:[[
                               {field:'name',title:'名称',width:100}
                           ]],
                           url: _ctx+'/sys/sysDict/findSysDictByPid?parentId=0',
                           expandUrl: _ctx+'/sys/sysDict/findSysDictByPid?parentId={id}',
                           getFatherIdsUrl: _ctx+'/sys/sysDict/findFatherIds?id={id}'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">字典类型</label>
                <div class="topjui-input-block">
                    <input id="dictType" type="text" name="type" data-toggle="topjui-combobox" value="${sysDict.type!''}"
                           data-options="required:true,prompt:'字典类型',
                            panelHeight:100,
                            textField:'text',
                            valueField:'value',
                            url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_dict_type',
                            onSelect: function(rec){
                                if(rec.value==1){
                                    $('#dictNid').iTextbox({
                                        prompt:'字典类型为目录，必填值',
                                        required:true,
                                        readonly:false
                                    });
                                    $('#dictValue').iTextbox({
                                        value:'',
                                        prompt:'字典类型为目录，不填值',
                                        required:false,
                                        readonly:true
                                    });
                                    $('#dictCode').iTextbox({
                                        value:'',
                                        prompt:'字典类型为目录，不填值',
                                        required:false,
                                        readonly:true
                                    });
                                    $('#dictJavaType').iTextbox({
                                        value:'',
                                        prompt:'字典类型为目录，不填值',
                                        required:false,
                                        readonly:true
                                    });
                                }else{
                                     $('#dictNid').iTextbox({
                                        value:'',
                                        prompt:'字典类型为值，不填值',
                                        required:false,
                                        readonly:true
                                    });
                                    $('#dictValue').iTextbox({
                                        prompt:'字典类型为值，必填值',
                                        required:true,
                                        readonly:false
                                    });
                                    $('#dictCode').iTextbox({
                                        prompt:'字典类型为值，必填值',
                                        required:true,
                                        readonly:false
                                    });
                                    $('#dictJavaType').iTextbox({
                                        prompt:'字典类型为值，必填值',
                                        required:true,
                                        readonly:false
                                    });
                                }
                            }">
                </div>
            </div>

        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">名称</label>
                <div class="topjui-input-block">
                    <input  type="text" name="name" data-toggle="topjui-textbox" value="${sysDict.name!''}"
                            data-options="required:true,prompt:'名称'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">nid</label>
                <div class="topjui-input-block">
                    <input id="dictNid" type="text" name="nid" data-toggle="topjui-textbox" value="${sysDict.nid!''}"
                           data-options="prompt:'nid'">
                </div>
            </div>

        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">值</label>
                <div class="topjui-input-block">
                    <input id="dictValue" type="text" name="value" data-toggle="topjui-textbox" value="${sysDict.value!''}"
                           data-options="prompt:'值'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">代码</label>
                <div class="topjui-input-block">
                    <input id="dictCode" type="text" name="code" data-toggle="topjui-textbox" value="${sysDict.code!''}"
                           data-options="required:true,prompt:'代码'">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">java类型</label>
                <div class="topjui-input-block">
                    <input id='dictJavaType' type="text" name="javaType" data-toggle="topjui-combobox"
                           value="${(sysDict.javaType)!''}"
                           data-options="
            required:true,prompt:'java类型',
            valueField:'value',
            url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_java_type'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">状态</label>
                <div class="topjui-input-block">
                    <input type="text" name="status"
                           data-toggle="topjui-combobox"
                           value="${sysDict.status!'1'}"
                           data-options="required:true,prompt:'状态',
                           panelHeight:100,
                           textField:'text',
                           valueField:'value',
                           url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_use_status'">
                </div>
            </div>
        </div>

        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">字典排序</label>
                <div class="topjui-input-block">
                    <input type="text" name="sort" data-toggle="topjui-numberspinner" value="${sysDict.sort!''}"
                           data-options="prompt:'字典排序'">
                </div>
            </div>

        </div>
    </div>
</div>
