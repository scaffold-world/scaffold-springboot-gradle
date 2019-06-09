package com.jiaheng.scaffold.route.app.config;


import com.jiaheng.scaffold.route.app.interceptor.ParamInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {




    @Bean
    public ParamInterceptor getParamInterceptor() {
        return new ParamInterceptor();
    }

    /**
     * 注入参数校验拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getParamInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }


}
