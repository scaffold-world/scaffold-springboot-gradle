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
                    <input type="hidden" name="id"  value="${sysOperate.id}">
                    <input type="text" name="" data-toggle="topjui-textbox" readonly="readonly"
                            value="${sysOperate.userName!''}" data-options="prompt:'用户名'">

                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">真实姓名</label>
                <div class="topjui-input-block">
                    <input type="text" name="realName" data-toggle="topjui-textbox"
                           value="${sysOperate.realName!''}" data-options="required:true,prompt:'真实姓名'">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm12">
                <label class="topjui-form-label">角色(默认)</label>
                <div class="topjui-input-block">
                    <div data-toggle="topjui-radio">
                        <#list list as item>
                        <#if item.id==sysRoleOperate.roleId><input type="radio" data-toggle="topjui-radiobutton" name="roleId" label="${item.name}" value="${item.id}" checked="checked">
                        <#else>
                            <input type="radio" data-toggle="topjui-radiobutton" name="roleId" label="${item.name}" value="${item.id}">
                        </#if>
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
                           data-options="id:'status',prompt:'状态',prompt:'状态',panelHeight:100,
                     data:[
                           {text: '开启',value: '0'
                            <#if sysOperate.status==0>, selected:true</#if>},
                           {text: '锁定',value: '1'
                            <#if sysOperate.status==1> ,selected:true</#if>},
                           {text: '停用',value: '2'
                            <#if sysOperate.status==2>, selected:true</#if>}
                     ]">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">联系电话</label>
                <div class="topjui-input-block">
                    <input type="text" name="mobilePhone" data-toggle="topjui-textbox"
                           data-options="required:true,prompt:'联系电话'" value="${sysOperate.mobilePhone!''}">
                </div>
            </div>
        </div>

    </div>