package com.jiaheng.scaffold.route.operate.interceptor;

import com.jiaheng.scaffold.common.util.TraceIdUtil;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:全局日志traceId拦截器
 * @author: yangdeke@jianbing.com
 * @date: 2019-02-26 23:28
 **/
public class TraceIdInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = TraceIdUtil.traceId();

        MDC.put("traceId",traceId);

        return true;
    }

}
