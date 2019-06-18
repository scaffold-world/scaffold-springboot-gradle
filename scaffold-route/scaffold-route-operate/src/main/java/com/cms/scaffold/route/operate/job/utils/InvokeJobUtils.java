package com.cms.scaffold.route.operate.job.utils;

import com.cms.scaffold.common.util.StringUtil;
import com.cms.scaffold.core.spring.SpringContextHolder;
import com.cms.scaffold.job.jobManager.bo.JobInfoBO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author zhangjiahengpoping@gmail.com
 * @Description
 **/
@Component
public class InvokeJobUtils {

    protected Logger logger = LoggerFactory.getLogger(InvokeJobUtils.class);


    /**
     * 通过反射调用scheduleJob中定义的方法
     *
     * @param scheduleJob
     */
    public boolean invokMethod(JobInfoBO scheduleJob) {
        Object object = null;
        Class clazz;
        try {
            if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
                object = SpringContextHolder.getBean(Class.forName(scheduleJob.getBeanClass()));
            }
            if (object == null) {
                logger.error("任务名称 = [{}]---------------未启动成功，object为NULL！！！", scheduleJob.getJobName());
                return false;
            }
            clazz = object.getClass();
            Method method = null;
            method = StringUtil.isNotBlank(scheduleJob.getParamJson()) ? clazz.getDeclaredMethod(scheduleJob.getMethodName(), String.class) : clazz.getDeclaredMethod(scheduleJob.getMethodName());
            if (method != null) {
                if (StringUtil.isNotBlank(scheduleJob.getParamJson())) {
                    method.invoke(object, scheduleJob.getParamJson());
                } else {
                    method.invoke(object);
                }
            }
        }catch (Exception e){
            logger.error("任务名称 = [{}]---------------未启动成功，请检查是否配置正确！！！", scheduleJob.getJobName());
            logger.error("程序异常：{}", e);
            return false;
        }
        logger.info("任务名称 = [{}]----------执行成功", scheduleJob.getJobName());
        return true;
    }
}
