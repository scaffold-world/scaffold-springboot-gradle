<div data-toggle="topjui-layout" data-options="fit:true">
    <div class="topjui-fluid">
        <input type="hidden" name="id" value="${sysI18n.id!''}">
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">模块</label>
                <div class="topjui-input-block">
                    <input type="text" name="model" data-toggle="topjui-textbox" value="${sysI18n.model!''}"
                           data-options="required:true,prompt:'模块'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">名称</label>
                <div class="topjui-input-block">
                    <input type="text" name="name" data-toggle="topjui-textbox" value="${sysI18n.name!''}"
                           data-options="required:true,prompt:'名称'">
                </div>
            </div>
        </div>

        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">默认内容</label>
                <div class="topjui-input-block">
                    <input type="text" name="text" data-toggle="topjui-textbox" value="${sysI18n.text!''}"
                           data-options="required:true,prompt:'内容'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">中文内容</label>
                <div class="topjui-input-block">
                    <input type="text" name="zhCh" data-toggle="topjui-textbox" value="${sysI18n.zhCh!''}"
                           data-options="required:true,prompt:'中文内容'">
                </div>
            </div>
        </div>


        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">英文内容</label>
                <div class="topjui-input-block">
                    <input type="text" name="enUs" data-toggle="topjui-textbox" value="${sysI18n.enUs!''}"
                           data-options="required:true,prompt:'英文内容'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">印尼内容</label>
                <div class="topjui-input-block">
                    <input type="text" name="idId" data-toggle="topjui-textbox" value="${sysI18n.idId!''}"
                           data-options="required:true,prompt:'印尼内容'">
                </div>

            </div>
        </div>


    </div>
</div>