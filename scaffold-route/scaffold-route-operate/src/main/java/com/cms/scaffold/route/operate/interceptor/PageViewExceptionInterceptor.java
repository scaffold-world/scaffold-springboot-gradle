package com.cms.scaffold.route.operate.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:全局日志traceId拦截器
 * @author: zhangjiahengpoping@gmail.com
 * @date: 2019-02-26 23:28
 **/
public class PageViewExceptionInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(PageViewExceptionInterceptor.class);
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        logger.info("【-MyInterceptor1,请求处理之后进行调用，但是在视图被渲染之前(controller方法调用之后)-】");
        if(response.getStatus()==500){
            modelAndView.setViewName("/500");
            /*
             * setViewName(String viewName);
             * 为此ModelAndView设置视图名称，由DispatcherServlet通过ViewResolver解析。 将覆盖任何预先存在的视图名称或视图。
             */
        }else if(response.getStatus()==404){
            modelAndView.setViewName("/404");
        }
    }
}
