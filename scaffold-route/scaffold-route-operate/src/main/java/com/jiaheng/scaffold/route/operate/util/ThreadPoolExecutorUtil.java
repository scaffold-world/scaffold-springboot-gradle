package com.jiaheng.scaffold.route.operate.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Author zhangjiaheng
 * @Description
 **/
public class ThreadPoolExecutorUtil {

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public void execute(final Runnable runnable){
        threadPoolTaskExecutor.execute(runnable);
    }
}
