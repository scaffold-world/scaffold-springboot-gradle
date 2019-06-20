package com.cms.scaffold.route.operate.config;

import com.cms.scaffold.route.operate.interceptor.PageViewExceptionInterceptor;
import com.cms.scaffold.route.operate.interceptor.TraceIdInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

/**
 * Created by zhangjiahengpoping@gmail.com on 2018/2/8.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    public TraceIdInterceptor traceIdInterceptor() {
        return new TraceIdInterceptor();
    }

    //使用自定义的LocaleResolver来替换掉默认的LocaleResolver
//    @Bean
//    public LocaleResolver localeResolver(){
//        return new MyLocaleResolver();
//    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver slr = new CookieLocaleResolver();
        //设置默认区域,
        slr.setDefaultLocale(Locale.CHINA);
        slr.setCookieMaxAge(3600);//设置cookie有效期.

        return slr;

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(traceIdInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new PageViewExceptionInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
