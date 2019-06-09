package com.jiaheng.scaffold.route.operate.job.executer;

import com.jiaheng.scaffold.core.spring.SpringContextHolder;
import com.jiaheng.scaffold.job.jobManager.bo.JobInfoBO;
import com.jiaheng.scaffold.route.operate.job.utils.InvokeJobUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @Author zhangjiaheng@jianbing.com
 * @Description 执行方法 可并发
 **/
@Component
public class TaskJobExecutor implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobInfoBO jobInfo = (JobInfoBO) context.getMergedJobDataMap().get("JobInfo");
        InvokeJobUtils invokeJobUtils = SpringContextHolder.getBean(InvokeJobUtils.class);
        invokeJobUtils.invokMethod(jobInfo);
    }
}
