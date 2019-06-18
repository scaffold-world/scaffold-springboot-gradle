package com.cms.scaffold.route.operate.controller;

import com.cms.scaffold.common.base.Builder;
import com.cms.scaffold.common.constant_manual.BasicsConstantManual;
import com.cms.scaffold.common.constant_manual.SysConstant;
import com.cms.scaffold.route.operate.response.sys.SysMenuResp;
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
 * Created by 张嘉恒 on 2018/3/16.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    SysMenuService sysMenuService;


    /**
     * 首页展示
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();


        SysOperate sysOperate = (SysOperate) subject.getSession().getAttribute(BasicsConstantManual.SESSION_ATTRIBUTE_KEY_OPERATOR);

        if(null == sysOperate){
            return "login";
        }

        List<SysMenuBO> list = sysMenuService.findByPidAndId(1L, sysOperate.getId());

        String menuList = "";

        for (SysMenuBO bo : list) {
            menuList = menuList + "<ul id='nav' class='nav-" + bo.getId() + "'>" + getChildTreeData(findByPidAndId(bo.getId(), sysOperate.getId()), request) + "</ul>";
        }

        request.setAttribute("menus", Builder.buildList(list, SysMenuResp.class));
        request.setAttribute("menuList", menuList);

        request.setAttribute("sysOperate", sysOperate);

        return "index";
    }

    /**
     * 生成左侧树形菜单 递归
     */
    private String getChildTreeData(List<SysMenuBO> list, HttpServletRequest request) {
        String html = "";
        if(null == list){
            return html;
        }
        for (int j = 0; j < list.size(); j++) {
            SysMenuBO menu = list.get(j);
            if ("menu".equalsIgnoreCase(menu.getResourceType())) {
                html = html + "<li><a href='javascript:;'><i class='iconfont'>" + menu.getIconCls() + "</i>" +
                        "<cite>" + menu.getName() + "</cite><i class='iconfont nav_right'>&#xe697;</i>" +
                        "</a><ul class='sub-menu'>" + getChildTreeData(menu.getSysMenuBOList(), request) + "</ul></li>";
            } else if ("window".equalsIgnoreCase(menu.getResourceType())) {
                html = html + "<li><a _href='" + request.getContextPath() + menu.getUrl() + "'><i class='iconfont'>&#xe6a7;</i>" +
                        "<cite>" + menu.getName() + "</cite></a></li>";
            }
        }
        return html;
    }

    /**
     * 递归查询出所有菜单及子菜单
     */
    private List<SysMenuBO> findByPidAndId(Long pid, Long operateId) {
        List<SysMenuBO> list = sysMenuService.findByPidAndId(pid, operateId);
        for (SysMenuBO bo : list) {
            if ("menu".equalsIgnoreCase(bo.getResourceType())) {
                bo.setSysMenuBOList(findByPidAndId(bo.getId(), operateId));
            }
        }
        return list;
    }

    /**
     * 权限不足跳转
     *
     * @return
     */
    @RequestMapping(value = "/403")
    @ResponseBody
    public String authorized() {

        return SysConstant.TIP_MESSAGE;
    }

    @RequestMapping(value = "/404")
    public String notFound() {

        return "404";
    }


}
