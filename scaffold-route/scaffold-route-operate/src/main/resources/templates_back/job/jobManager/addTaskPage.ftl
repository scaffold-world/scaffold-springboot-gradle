<div data-toggle="topjui-layout" data-options="fit:true">
    <div class="topjui-fluid" style="padding: 0; margin-right: 30px">
        <fieldset>
            <legend>更新任务配置</legend>
        </fieldset>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">任务名称</label>
                <div class="topjui-input-block">
                    <input type="text" name="jobName" data-toggle="topjui-textbox" value="" data-options="required:true,prompt:'任务名称'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">任务分组</label>
                <div class="topjui-input-block">
                    <input type="text" name="jobGroup" data-toggle="topjui-textbox" value="" data-options="required:true,prompt:'任务分组'">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">cron表达式</label>
                <div class="topjui-input-block">
                    <input type="text" name="cronExpression" data-toggle="topjui-textbox" value="" data-options="required:true,prompt:'cron表达式'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">方法名称</label>
                <div class="topjui-input-block">
                    <input type="text" name="methodName" data-toggle="topjui-textbox" value="" data-options="required:true,prompt:'方法名称'">
                </div>
            </div>
        </div>

        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">方法参数</label>
                <div class="topjui-input-block">
                    <input type="text" name="paramJson" data-toggle="topjui-textbox" value="" data-options="prompt:'若方法无参，请勿填写'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">是否可以并发</label>
                <div class="topjui-input-block">
                    <input type="text" name="isConcurrent" data-toggle="topjui-combobox" value="0"
                           data-options="id:'isConcurrent',prompt:'是否可以并发',required:true,panelHeight:100,
                     url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_sys_job_is_concurrent'">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm10">
                <label class="topjui-form-label">任务类名</label>
                <div class="topjui-input-block">
                    <input type="text" name="beanClass" data-toggle="topjui-textbox" value="" data-options="required:true,prompt:'任务类名'">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm10">
                <label class="topjui-form-label">任务描述</label>
                <div class="topjui-input-block">
                    <input type="text" name="jobDescription" data-toggle="topjui-textbox" value="" data-options="required:true,prompt:'任务描述'">
                </div>
            </div>

        </div>

    </div>
</div>