package com.cms.scaffold.job.jobManager.ao;

import com.cms.scaffold.common.base.BaseAO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
* 
* @author: Mybatis Generator
* @date: 2019-02-28 19:51:51
*/
@Getter
@Setter
public class JobInfoAO extends BaseAO {
    /** 任务名称**/
    private String jobName;

    /** 任务分组**/
    private String jobGroup;

    /** 任务描述**/
    private String jobDescription;

    /** 是否随着程序启动自动启动任务 0否 1是**/
    private Integer startWithrun;

    /** 1正在运行 0已经停止**/
    private Integer jobStatus;

    /** cron表达式**/
    private String cronExpression;

    /** 任务执行时调用哪个类的方法 包名+类名**/
    private String beanClass;

    /** Spring bean 名**/
    private String springId;

    private String methodName;

    /** 方法执行需要的参数，配置为json**/
    private String paramJson;

    /** 任务是否可以并发(一个还没完就执行下一个） 1可以 0不可以**/
    private Integer isConcurrent;

    private Date createTime;
}