package com.jiaheng.scaffold.route.operate.aop;

import com.jiaheng.scaffold.sys.sys.ao.SysOperateLogAO;
import com.jiaheng.scaffold.sys.sys.service.SysOperateLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author zhangjiahengpoping@gmail.com
 * @Description 异步执行任务类
 **/
@Component
public class AsyncTask {

    @Resource
    private SysOperateLogService sysOperateLogService;

    /**
     * 插入一条操作记录
     */
    @Async
    public void insertSysOperateLog(SysOperateLogAO sysOperateLog){
        sysOperateLogService.insert(sysOperateLog);
    }

}
