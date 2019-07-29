package com.cms.scaffold.code.config;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * @description:获取国际化信息服务类
 * @author: zjh
 * @date: 2019-03-12 00:21
 **/
@Component
public class LocaleMessageSourceService {

    @Resource
    private MessageSource messageSource;


    /**
     * @param code ：对应messages配置的key.
     * @return
     */
    public String getMessage(String code) {
        return getMessage(code, null);
    }


    /**
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @return
     */
    public String getMessage(String code, Object[] args) {
        return getMessage(code, args, "");
    }


    /**
     * @param code           ：对应messages配置的key.
     * @param args           : 数组参数.
     * @param defaultMessage : 没有设置key的时候的默认值.
     * @return
     */

    public String getMessage(String code, Object[] args, String defaultMessage) {
        //这里使用比较方便的方法，不依赖request.
        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(code, args, defaultMessage, locale);
    }
}
