package com.jiaheng.scaffold.route.operate.controller;

import com.jiaheng.scaffold.common.base.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @description: 语言切换Controller
 * @author: zhangjiahengpoping@gmail.com
 * @date: 2019-03-12 17:17
 **/
@RestController
@RequestMapping("/lang")
public class LangController extends BaseController{


    @RequestMapping(value = "/changeLanguage")
    public ResponseModel changeLanguage(HttpServletRequest request, HttpServletResponse response, String lang) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

        Locale locale = Locale.getDefault();
        if(lang!=null&&lang.length()==5){
            String[] split = lang.split("_");
            locale = new Locale(split[0],split[1]);
        }
        localeResolver.setLocale(request, response, locale);

        return doneSuccess();
    }
}
