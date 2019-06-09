<div data-toggle="topjui-layout" data-options="fit:true">
    <input type="hidden" name="id" value="${job.id!''}">
    <input type="hidden" name="jobStatus" value="${job.jobStatus!''}">
    <input type="hidden" name="jobName" value="${job.jobName!''}">
    <input type="hidden" name="jobGroup" value="${job.jobGroup!''}">
    <div class="topjui-fluid">
        <fieldset>
            <legend>更新任务配置</legend>
        </fieldset>
        <div class="topjui-row">
            <div class="topjui-col-sm12">
                <label class="topjui-form-label">表达式</label>
                <div class="topjui-input-block">
                        <input type="text" name="cronExpression"  data-toggle="topjui-textbox" value="${job.cronExpression}" data-options="width:200,required:true">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm12">
                <label class="topjui-form-label">是否随程序重启</label>
                <div class="topjui-input-block">
                    <input type="text" name="startWithrun" data-toggle="topjui-combobox" value="${job.startWithrun}"
                           data-options="width:200,required:true,valueField:'value',textField:'name',panelHeight:100,
                           data:[{
                                name: '是',
                                value: '1'
                            },{
                                name: '否',
                                value: '0'
                            }]">
                </div>
            </div>
        </div>
    </div>
</div>