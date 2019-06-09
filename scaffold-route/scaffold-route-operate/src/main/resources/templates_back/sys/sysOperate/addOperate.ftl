<div data-toggle="topjui-layout" data-options="fit:true">
<#--    <div data-options="region:'center',title:'',fit:false,border:false,bodyCls:'border_right_bottom'">
        <div data-toggle="topjui-tabs" data-options="fit:false,border:false">-->
    <div class="topjui-fluid">
        <fieldset>
            <legend>基本信息</legend>
        </fieldset>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">用户名</label>
                <div class="topjui-input-block">
                    <input type="text" name="userName" data-toggle="topjui-textbox"
                           data-options="required:true,prompt:'用户名'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">账户密码</label>
                <div class="topjui-input-block">
                    <input type="text" name="pwd" data-toggle="topjui-textbox"
                           data-options="required:true,prompt:'账户密码'" class="passwordbox-f textbox-f">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">真实姓名</label>
                <div class="topjui-input-block">
                    <input type="text" name="realName" data-toggle="topjui-textbox"
                           data-options="required:true,prompt:'真实姓名'">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm12">
                <label class="topjui-form-label">角色(默认)</label>
                <div class="topjui-input-block">
                    <div data-toggle="topjui-radio">
                        <#list list as item>
                            <input type="radio" data-toggle="topjui-radiobutton" name="roleId" label="${item.name}" value="${item.id}" checked="checked">
                        </#list>
                    </div>
                </div>
            </div>
        </div>

        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">状态</label>
                <div class="topjui-input-block">
                    <input type="text" name="status" data-toggle="topjui-combobox"
                           data-options="id:'status',required:true,prompt:'状态',panelHeight:100,
                     data:[
                           {
                               text: '开启',
                               value: '0'
                           },
                           {
                               text: '锁定',
                               value: '1'
                           },
                             {
                               text: '停用',
                               value: '2'
                           }
                     ]">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">联系电话</label>
                <div class="topjui-input-block">
                    <input type="text" name="mobilePhone" data-toggle="topjui-textbox"
                           data-options="required:true,prompt:'联系电话'">
                </div>
            </div>
        </div>

    </div>