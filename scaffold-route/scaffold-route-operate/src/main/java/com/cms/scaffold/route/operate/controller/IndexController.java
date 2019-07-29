package com.cms.scaffold.route.operate.controller;

import com.cms.scaffold.common.base.Builder;
import com.cms.scaffold.common.constant_manual.BasicsConstantManual;
import com.cms.scaffold.common.constant_manual.SysConstant;
import com.cms.scaffold.code.I18nUtil.I18nTransformUtil;
import com.cms.scaffold.route.operate.response.SysMenuResp;
import com.cms.scaffold.sys.sys.bo.SysMenuBO;
import com.cms.scaffold.sys.sys.domain.SysOperate;
import com.cms.scaffold.sys.sys.service.SysMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zjh on 2018/3/16.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    SysMenuService sysMenuService;


    /**
     * 首页展示
     * @param request
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();


        SysOperate sysOperate =(SysOperate) subject.getSession().getAttribute(BasicsConstantManual.SESSION_ATTRIBUTE_KEY_OPERATOR);


        List<SysMenuBO> list =sysMenuService.findByPidAndId(1L,sysOperate.getId());
        I18nTransformUtil.transFormList(list, "name");
        request.setAttribute("menus", Builder.buildList(list, SysMenuResp.class));

        request.setAttribute("sysOperate",sysOperate);
        return "index";
    }

    /**
     * 权限不足跳转
     * @return
     */

    @RequestMapping(value = "/403")
    @ResponseBody
    public String authorized() {

        return SysConstant.TIP_MESSAGE;
    }





}
