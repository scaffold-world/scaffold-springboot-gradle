package com.cms.scaffold.route.operate.aop;

import com.alibaba.fastjson.JSON;
import com.cms.scaffold.sys.sys.ao.SysOperateLogAO;
import com.cms.scaffold.route.operate.util.UserUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author zhangjiaheng@gmail.com
 * @Description 拦截后台操纵数据库的请求 保存日志记录
 **/
@Aspect
@Component
public class SysOperateLogAspect {

    /** 异步执行对象 **/
    @Autowired
    private AsyncTask asyncTask;

    /**
     * 只拦截operate后台的请求
     */
    @Pointcut(value = "execution(* com.cms.scaffold.controller.*.*(..)) || execution( * com.cms.scaffold.controller.*.*.*(..))")
    public void pointcutOperate(){}

    @AfterReturning(pointcut="pointcutOperate()",returning = "retValue")
    public void saveOperateLog2Datebase(JoinPoint joinPoint, Object retValue) {
        if(retValue instanceof String){
            // 返回值是String，为视图请求，不进行处理
            return;
        }
        //方法名称
        String methodName = joinPoint.getSignature().getName();
        String methodNameLowerCase=methodName.toLowerCase();
        if(     methodNameLowerCase.contains("update") || methodNameLowerCase.contains("save") ||
                methodNameLowerCase.contains("insert") || methodNameLowerCase.contains("add") ||
                methodNameLowerCase.contains("delete") || methodNameLowerCase.contains("remove") ||
                methodNameLowerCase.contains("edit")){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String classType = joinPoint.getTarget().getClass().getName();
            String url = request.getRequestURI();
            String param = null;
            Map<String, String[]> parameterMap = request.getParameterMap();
            if(parameterMap != null && parameterMap.size()>0 ){
                param = JSON.toJSONString(parameterMap);
            }
            String returnParam = null;
            if(retValue!=null){
                returnParam = JSON.toJSONString(retValue);
            }
            SysOperateLogAO sysOperateLog = new SysOperateLogAO();
            sysOperateLog.setTraceId(MDC.get("traceId"));
            sysOperateLog.setClassName(classType);
            sysOperateLog.setRequestUrl(url);
            sysOperateLog.setRequestMethod(methodName);
            sysOperateLog.setRequestParam(param);
            sysOperateLog.setResponseParam(returnParam);
            sysOperateLog.setOperateId(UserUtil.getOperatorFromSession().getId());
            sysOperateLog.setOperateName(UserUtil.getOperatorFromSession().getRealName());
            asyncTask.insertSysOperateLog(sysOperateLog);
        }
    }
}
