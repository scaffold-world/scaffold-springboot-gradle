<div data-toggle="topjui-layout" data-options="fit:true">
    <fieldset>
        <legend>参数设置</legend>
    </fieldset>
    <input type="hidden" name="id" value="${sysConfig.id!''}">
    <div class="topjui-row">
        <div class="topjui-col-sm10">
            <label class="topjui-form-label">状态</label>
            <div class="topjui-input-block">
                <input type="text" name="status"
                       data-toggle="topjui-combobox"
                       value="${sysConfig.status!''}"
                       data-options="required:true,
                           textField:'text',
                           valueField:'value',
                           url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_use_status'">
            </div>
        </div>
    </div>
    <div class="topjui-row">
        <div class="topjui-col-sm10">
            <label class="topjui-form-label">类型</label>
            <div class="topjui-input-block">
                <input type="text" name="type"
                       data-toggle="topjui-combobox"
                       value="${sysConfig.type!''}"
                       data-options="required:true,
                           textField:'text',
                           valueField:'value',
                           url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_sys_config_type'">
            </div>
        </div>
    </div>
    <div class="topjui-row">
        <div class="topjui-col-sm10">
            <label class="topjui-form-label">标识</label>
            <div class="topjui-input-block">
                <input type="text" name="nid" data-toggle="topjui-textbox"
                       data-options="required:true" value="${sysConfig.nid!''}">
            </div>
        </div>
    </div>
    <div class="topjui-row">
        <div class="topjui-col-sm10">
            <label class="topjui-form-label">名称</label>
            <div class="topjui-input-block">
                <input type="text" name="name" data-toggle="topjui-textbox"
                       data-options="required:false" value="${sysConfig.name!''}">
            </div>
        </div>
    </div>
    <div class="topjui-row">
        <div class="topjui-col-sm10">
            <label class="topjui-form-label">值</label>
            <div class="topjui-input-block">
                <input type="text" name="value" data-toggle="topjui-textbox"
                       data-options="required:false,multiline:true,height:90" value='${sysConfig.value!""}'>
            </div>
        </div>
    </div>
    <div class="topjui-row">
        <div class="topjui-col-sm10">
            <label class="topjui-form-label">说明</label>
            <div class="topjui-input-block">
                <input type="text" name="remark" data-toggle="topjui-textbox"
                       data-options="required:false,multiline:true,height:90" value="${sysConfig.remark!''}">
            </div>
        </div>
    </div>
</div>