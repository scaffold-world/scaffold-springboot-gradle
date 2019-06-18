package com.cms.scaffold.route.operate.controller;

import com.cms.scaffold.common.constant_manual.BasicsConstantManual;
import com.cms.scaffold.common.util.StringUtil;
import com.cms.scaffold.route.operate.util.UserUtil;
import com.cms.scaffold.sys.sys.domain.SysOperate;
import com.cms.scaffold.sys.sys.service.SysOperateService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private SysOperateService sysOperateService;

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request,Model model) throws Exception{
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
    public String loginCheck(String username, String password) {

        if (StringUtil.isBlank(username) || StringUtil.isBlank(password)) {
            return "redirect:/login";
        }

        SysOperate sysOperate = sysOperateService.findByUserName(username);

        //错误次数
        String errorTime = null;

        if (sysOperate != null) {

            if (sysOperate.getStatus() == 2) {
                return "redirect:/login";
            }

            if (Integer.parseInt(errorTime == null ? "0" : errorTime) == 20) {
                sysOperate.setStatus(1l);
                sysOperateService.updateSysOperate(sysOperate);
                return "redirect:/login";
            }
        }
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //token.setRememberMe(true);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "redirect:/login";
        }


        return "redirect:/index";

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