package com.jiaheng.scaffold.route.operate.job.executer;

import org.quartz.DisallowConcurrentExecution;

/**
 * @Author zhangjiaheng@jianbing.com
 * @Description 执行方法 不可并发
 **/
@DisallowConcurrentExecution
public class TaskDisallowConcurrentExecutor extends AbstractTaskExecutor {}
