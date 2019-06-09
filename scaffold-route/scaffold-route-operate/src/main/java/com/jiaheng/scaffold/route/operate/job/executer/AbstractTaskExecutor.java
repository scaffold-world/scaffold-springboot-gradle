package com.jiaheng.scaffold.route.operate.job.executer;

import com.jiaheng.scaffold.core.spring.SpringContextHolder;
import com.jiaheng.scaffold.job.jobManager.bo.JobInfoBO;
import com.jiaheng.scaffold.route.operate.job.utils.InvokeJobUtils;
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
