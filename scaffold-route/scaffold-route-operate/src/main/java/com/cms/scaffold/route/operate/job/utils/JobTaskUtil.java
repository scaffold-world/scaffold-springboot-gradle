package com.cms.scaffold.route.operate.job.utils;

import com.cms.scaffold.job.jobManager.ao.JobInfoAO;
import com.cms.scaffold.job.jobManager.bo.JobInfoBO;
import com.cms.scaffold.route.operate.job.executer.AbstractTaskExecutor;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author zhangjiahengpoping@gmail.com
 * @Description
 **/
@Component
public class JobTaskUtil {

    protected Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Resource
    private Scheduler scheduler;

    /** 支持并发执行定时任务*/
    private static final String IS_CONCURRENT = "1";

    /**
     * 添加任务
     * @param job
     * @throws SchedulerException
     */
    public void addOrUpdateJob(JobInfoBO job) throws SchedulerException {
        if (job == null) {
            return;
        }

        logger.info("add scheduler:{}",scheduler.toString());

        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(),job.getJobGroup());
        CronTrigger trigger = (CronTrigger)scheduler.getTrigger(triggerKey);
        // 不存在，创建一个
        if(trigger == null){
            Class<? extends Job> clazz = AbstractTaskExecutor.getExecutorClazz(IS_CONCURRENT.equals(job.getIsConcurrent().toString()));

            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();

            jobDetail.getJobDataMap().put("JobInfo", job);

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

            trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
        }else{
            logger.info("rescheduleJob : {}", job.getJobName());
            // Trigger已存在，那么更新相应的定时设置
            // 增加：withMisfireHandlingInstructionDoNothing()方法
            // 1，不触发立即执行
            // 2，等待下次Cron触发频率到达时刻开始按照Cron频率依次执行
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression()).withMisfireHandlingInstructionDoNothing();

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     * @throws SchedulerException
     */
    public List<JobInfoAO> getAllJob() throws SchedulerException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<JobInfoAO> jobList = new ArrayList<JobInfoAO>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                JobInfoAO job = new JobInfoAO();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setJobDescription("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        }
        return jobList;
    }

    /**
     * 所有正在执行的job（已经开始调用还没有执行完成的）
     *
     * @return
     * @throws SchedulerException
     */
    public List<JobInfoAO> getRunningJob() throws SchedulerException {
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<JobInfoAO> jobList = new ArrayList<JobInfoAO>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            JobInfoAO job = new JobInfoAO();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            job.setJobName(jobKey.getName());
            job.setJobGroup(jobKey.getGroup());
            job.setJobDescription("触发器:" + trigger.getKey());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
            jobList.add(job);
        }
        return jobList;
    }

    /**
     * 暂停一个job (可能导致堆积，重新开始会执行多个)
     *
     * @param JobInfo
     * @throws SchedulerException
     */
    public void pauseJob(JobInfoAO JobInfo) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(JobInfo.getJobName(), JobInfo.getJobGroup());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复一个job
     *
     * @param JobInfo
     * @throws SchedulerException
     */
    public void resumeJob(JobInfoAO JobInfo) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(JobInfo.getJobName(), JobInfo.getJobGroup());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除一个job
     *
     * @throws SchedulerException
     */
    public void deleteJob(String jobName, String jobGroup) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        scheduler.deleteJob(jobKey);
    }

    /**
     * 删除job根据list
     * @param jobs
     * @throws SchedulerException
     */
    public void deleteJobs(List<JobInfoBO> jobs) throws SchedulerException {
        List<JobKey> keys = new ArrayList<>();
        for (JobInfoBO job:jobs) {
            JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
            keys.add(jobKey);
        }

        scheduler.deleteJobs(keys);
    }

    /**
     * 立即执行job
     *
     * @param JobInfo
     * @throws SchedulerException
     */
    public void runAJobNow(JobInfoAO JobInfo) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(JobInfo.getJobName(), JobInfo.getJobGroup());
        scheduler.triggerJob(jobKey);
    }
}
