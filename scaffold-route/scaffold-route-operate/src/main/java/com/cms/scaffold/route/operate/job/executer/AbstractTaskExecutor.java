package com.cms.scaffold.route.operate.job.executer;

import com.cms.scaffold.core.spring.SpringContextHolder;
import com.cms.scaffold.job.jobManager.bo.JobInfoBO;
import com.cms.scaffold.route.operate.job.utils.InvokeJobUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * 将execute方法提取到抽象类中
 */
public class AbstractTaskExecutor implements Job {

    public static Class<? extends Job> getExecutorClazz(boolean flag) {
        return flag ? TaskAllowConcurrentExecutor.class : TaskDisallowConcurrentExecutor.class;
    }

    @Override
    public void execute(JobExecutionContext context) {
        JobInfoBO jobInfo = (JobInfoBO) context.getMergedJobDataMap().get("JobInfo");
        InvokeJobUtils invokeJobUtils = SpringContextHolder.getBean(InvokeJobUtils.class);
        invokeJobUtils.invokMethod(jobInfo);
    }
}
