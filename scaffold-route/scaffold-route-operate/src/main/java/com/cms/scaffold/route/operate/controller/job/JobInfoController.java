package com.cms.scaffold.route.operate.controller.job;

import com.cms.scaffold.common.asserts.Assert;
import com.cms.scaffold.common.base.Builder;
import com.cms.scaffold.common.base.ResponseListModel;
import com.cms.scaffold.common.base.ResponseModel;
import com.cms.scaffold.common.constant_manual.BasicsConstantManual;
import com.cms.scaffold.common.exception.BusinessException;
import com.cms.scaffold.common.exception.SystemException;
import com.cms.scaffold.core.spring.SpringContextHolder;
import com.cms.scaffold.job.jobManager.ao.JobInfoAO;
import com.cms.scaffold.job.jobManager.bo.JobInfoBO;
import com.cms.scaffold.job.jobManager.service.JobInfoService;
import com.cms.scaffold.route.operate.controller.BaseController;
import com.cms.scaffold.route.operate.job.utils.InvokeJobUtils;
import com.cms.scaffold.route.operate.job.utils.JobTaskUtil;
import io.swagger.annotations.Api;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author zhangjiahengpoping@gmail.com
 * @Description
 **/
@Controller
@RequestMapping("/job/jobManager")
@Api(tags = "JobInfoController", description = "定时任务配置页面")
public class JobInfoController extends BaseController {

    private static final String ftlPath = "/job/jobManager/";

    @Resource
    private JobInfoService jobInfoService;

    @Resource
    private JobTaskUtil jobTaskUtil;
    /**
     * 任务管理页面
     * @return
     */
    @RequestMapping("/jobManagePage")
    public String jobManagePage(){
        return ftlPath + "jobManagePage";
    }

    /**
     * 任务更新页面
     * @return
     */
    @RequestMapping("/updateJobPage")
    public String updateJobPage(Long id, Model model){
        JobInfoBO jobInfoBO = jobInfoService.selectById(id);
        model.addAttribute("job", jobInfoBO);
        return ftlPath + "updateJobPage";
    }

    /**
     * 任务新增页面
     * @return
     */
    @RequestMapping("/addJobPage")
    public String addJobPage(){
        return ftlPath + "addJobPage";
    }

    /**
     * 获取定时任务分页列表
     * @param jobInfoAo
     * @return
     */
    @RequestMapping("/getJobPageList")
    @ResponseBody
    public ResponseListModel<JobInfoBO> getJobPageList(JobInfoAO jobInfoAo){
        ResponseListModel<JobInfoBO> jobInfoPage = jobInfoService.getJobPageList(jobInfoAo);
        return jobInfoPage;
    }

    /**
     * 新增定时任务并启动
     * @return
     */
    @RequestMapping("/addJob")
    @ResponseBody
    public ResponseModel addJob(JobInfoAO jobInfoAO){
        try {
            Assert.notNull(jobInfoAO.getJobName(), "jobName");
            JobInfoBO jobInfo = jobInfoService.selectOne(jobInfoAO);
            if(null != jobInfo){
                throw new SystemException("该任务名称已经存在");
            }
            jobInfoAO.setJobStatus(BasicsConstantManual.BASICS_SYS_JOB_STATUS_STARTING);
            jobInfoService.insert(jobInfoAO);
            jobTaskUtil.addOrUpdateJob(Builder.build(jobInfoAO, JobInfoBO.class));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return doneSuccess();
    }

    /**
     * 启动/重启所有任务
     * @return
     */
    @RequestMapping("/startAllJobs")
    @ResponseBody
    public ResponseModel startAllJobs(){
        try {
            List<JobInfoBO> list = jobInfoService.findList(new JobInfoAO());
            for (JobInfoBO jobInfo : list) {
                jobTaskUtil.addOrUpdateJob(jobInfo);
                JobInfoAO ao = new JobInfoAO();
                ao.setId(jobInfo.getId());
                ao.setJobStatus(BasicsConstantManual.BASICS_SYS_JOB_STATUS_STARTING);
                jobInfoService.update(ao);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return doneSuccess();
    }

    /**
     * 停止所有任务
     * @return
     */
    @RequestMapping("/stopAllJobs")
    @ResponseBody
    public ResponseModel stopAllJobs() {
        try {
            List<JobInfoBO> list = jobInfoService.findList(new JobInfoAO());
            jobTaskUtil.deleteJobs(list);
            jobInfoService.updateAllJobStatus(BasicsConstantManual.BASICS_SYS_JOB_STATUS_STOP);
        }catch (SchedulerException e) {
            e.printStackTrace();
            return doneError();
        }
        return doneSuccess();
    }

    /**
     * 更新任务执行频率cron
     * @return
     */
    @RequestMapping("/updateJob")
    @ResponseBody
    public ResponseModel updateJob(JobInfoAO jobInfoAO){
        try {
            if(BasicsConstantManual.BASICS_SYS_JOB_STATUS_STARTING.equals(jobInfoAO.getJobStatus())){
                // 如果任务在计划中 就更新
                jobTaskUtil.addOrUpdateJob(Builder.build(jobInfoAO, JobInfoBO.class));
            }
            jobInfoService.update(jobInfoAO);
        }catch (SchedulerException e) {
            e.printStackTrace();
            return doneError();
        }
        return doneSuccess();
    }

    /**
     * 停止一个定时任务（最后一次会执行完成）
     * @return
     */
    @RequestMapping("/stopOneJob")
    @ResponseBody
    public ResponseModel stopOneJob(Long id){
        try {
            JobInfoBO jobInfo = jobInfoService.selectById(id);
            jobTaskUtil.deleteJob(jobInfo.getJobName(), jobInfo.getJobGroup());
            jobInfo.setJobStatus(BasicsConstantManual.BASICS_SYS_JOB_STATUS_STOP);
            jobInfoService.update(Builder.build(jobInfo, JobInfoAO.class));
        }catch (Exception e){
            e.printStackTrace();
            return doneError();
        }
        return doneSuccess();
    }

    /**
     * 删除一个定时任务（最后一次会执行完成）
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseModel delete(Long id){
        try {
            JobInfoBO jobInfo = jobInfoService.selectById(id);
            jobTaskUtil.deleteJob(jobInfo.getJobName(), jobInfo.getJobGroup());
            jobInfoService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return doneError();
        }
        return doneSuccess();
    }

    /**
     * 重启一个定时任务
     * @return
     */
    @RequestMapping("/restartOneJob")
    @ResponseBody
    public ResponseModel restartOneJob(Long id){
        try {
            Assert.notNull(id, "id");
            JobInfoBO jobInfo = jobInfoService.selectById(id);
            jobTaskUtil.addOrUpdateJob(jobInfo);
            jobInfo.setJobStatus(BasicsConstantManual.BASICS_SYS_JOB_STATUS_STARTING);
            jobInfoService.update(Builder.build(jobInfo, JobInfoAO.class));
        } catch (SchedulerException e) {
            e.printStackTrace();
            return doneError();
        }
        return doneSuccess();
    }

    /**
     * 立即执行一次该任务
     */
    @RequestMapping("/runAJobNowOnce")
    @ResponseBody
    public ResponseModel runAJobNowOnce(Long id){
        try {
            JobInfoBO jobInfo = jobInfoService.selectById(id);
            if(BasicsConstantManual.BASICS_SYS_JOB_STATUS_STOP.equals(jobInfo.getJobStatus())){
                // 任务通知状态不能执行一次
                throw new BusinessException("任务停止状态，不能立即执行一次！");
            }
            jobTaskUtil.runAJobNow(Builder.build(jobInfo, JobInfoAO.class));
        } catch (SchedulerException e) {
            e.printStackTrace();
            return doneError();
        }
        return doneSuccess();
    }

    /**
     * 测试调用一次远程方法
     */
    @RequestMapping("/testExecuteTheMethod")
    @ResponseBody
    public ResponseModel testExecuteTheMethod(JobInfoAO jobInfoAO){
        InvokeJobUtils taskUtils = SpringContextHolder.getBean(InvokeJobUtils.class);
        boolean flag = taskUtils.invokMethod(Builder.build(jobInfoAO, JobInfoBO.class));
        if(!flag){
            throw new BusinessException("方法调用失败！请检查任务配置！");
        }
        return doneSuccess();
    }
}
