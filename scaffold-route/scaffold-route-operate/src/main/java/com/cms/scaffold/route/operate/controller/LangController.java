package com.cms.scaffold.route.operate.controller;

import com.cms.scaffold.common.base.ResponseModel;
import com.cms.scaffold.common.constant_manual.BasicsConstantManual;
import com.cms.scaffold.route.operate.util.UserUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @description: 语言切换Controller
 * @author: zjh
 * @date: 2019-03-12 17:17
 **/
@RestController
@RequestMapping("/lang")
public class LangController extends BaseController{


    @RequestMapping(value = "/changeLanguage")
    public ResponseModel changeLanguage(HttpServletRequest request, HttpServletResponse response, String lang) {
        // 实际上是 CookieLocaleResolver
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

        Locale locale = Locale.getDefault();
        if(lang!=null&&lang.length()==5){
            String[] split = lang.split("_");
            locale = new Locale(split[0],split[1]);
        }
        UserUtil.getSession().setAttribute(BasicsConstantManual.SESSION_ATTRIBUTE_KEY_LANGUAGE, lang);
        localeResolver.setLocale(request, response, locale);
        return doneSuccess();
    }
}
