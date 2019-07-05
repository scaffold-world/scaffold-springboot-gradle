package com.cms.scaffold.route.operate.controller;

import com.cms.scaffold.common.base.ResponseModel;
import com.cms.scaffold.common.constant_manual.BasicsConstantManual;
import com.cms.scaffold.common.constant_manual.SysConstant;
import com.cms.scaffold.common.util.StringUtil;
import com.cms.scaffold.sys.sys.domain.SysOperate;
import com.cms.scaffold.sys.sys.service.SysOperateService;
import com.cms.scaffold.route.operate.util.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private SysOperateService sysOperateService;

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, Model model) throws Exception {
        Object operator = UserUtil.getPrincipal();

        // 如果已经登录，则跳转到管理首页
        if (operator != null) {
            Subject subject = SecurityUtils.getSubject();
            //如果后面有用到session 保证session信息
            if (subject.isRemembered()) {
                SysOperate current_user = UserUtil.getOperatorFromSession();
                if (current_user == null) {
                    subject.getSession().setAttribute(BasicsConstantManual.SESSION_ATTRIBUTE_KEY_OPERATOR, current_user);
                }
            }
            return "redirect:" + "/index";
        }

        if (request.getServerName().indexOf("47.110.32.90") > -1) {
            return "login";
        }

        return "login";
    }


    /**
     * 登录检测
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login/check")
    @ResponseBody
    public ResponseModel loginCheck(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password) {

        if (StringUtil.isBlank(username) || StringUtil.isBlank(password)) {
            return doneError(SysConstant.USERNAME_PASSWORD_ISNULL);
        }

        SysOperate sysOperate = sysOperateService.findByUserName(username);

        //错误次数
        String errorTime = null;

        if (sysOperate != null) {

            if (sysOperate.getStatus() == 1 || sysOperate.getStatus() == 2) {
                return doneError(SysConstant.USER_STATUS_LOCK);
            }

            if (Integer.parseInt(errorTime == null ? "0" : errorTime) == 20) {
                sysOperate.setStatus(1l);
                sysOperateService.updateSysOperate(sysOperate);
                return doneError(SysConstant.USER_STATUS_LOCK);
            }
        }
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //token.setRememberMe(true);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return doneError(SysConstant.USERNAME_OR_PASSWORD_ERROR);
        }
        Cookie cookies[] = request.getCookies();
        String language = "zh_CN";
        for(Cookie cookie : cookies){
            // 从cookie中获取到语言类型 登录的时候就进行设置
            if("org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE".equals(cookie.getName())){
                language = cookie.getValue();
            }
        }
        UserUtil.getSession().setAttribute(BasicsConstantManual.SESSION_ATTRIBUTE_KEY_LANGUAGE, language);
        // 这个实际上是 CookieLocaleResolver
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        Locale locale = Locale.getDefault();
        if(language!=null&&language.length()==5){
            String[] split = language.split("_");
            locale = new Locale(split[0],split[1]);
        }
        localeResolver.setLocale(request, response, locale);
        return doneStatus(200, "", "index");

    }

    /**
     * 注销
     */
    @RequestMapping(value = "/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return "login";
    }
}