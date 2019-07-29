package com.cms.scaffold.route.operate.controller.sys;

import com.alibaba.fastjson.JSON;
import com.cms.scaffold.route.operate.controller.BaseController;
import com.cms.scaffold.common.base.Builder;
import com.cms.scaffold.common.base.ResponseModel;
import com.cms.scaffold.common.constant_manual.BasicsConstantManual;
import com.cms.scaffold.common.constant_manual.SysConstant;
import com.cms.scaffold.code.I18nUtil.I18nTransformUtil;
import com.cms.scaffold.route.operate.request.sys.SysMenuReq;
import com.cms.scaffold.route.operate.response.SysMenuResp;
import com.cms.scaffold.route.operate.shiro.ShiroService;
import com.cms.scaffold.sys.sys.ao.SysMenuAO;
import com.cms.scaffold.sys.sys.bo.SysMenuBO;
import com.cms.scaffold.sys.sys.bo.SysRoleMenuBO;
import com.cms.scaffold.sys.sys.domain.SysOperate;
import com.cms.scaffold.sys.sys.domain.SysRole;
import com.cms.scaffold.sys.sys.service.SysMenuService;
import com.cms.scaffold.sys.sys.service.SysRoleMenuService;
import com.cms.scaffold.sys.sys.service.SysRoleOperateService;
import com.cms.scaffold.sys.sys.service.SysRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 商户资源管理菜单Controller
 * Created by zjh on 2018/3/17.
 */
@Controller
@RequestMapping("/sys/sysMenu")
public class SysMenuController extends BaseController {
    public static final String ftlPath = "/sys/sysMenu/";


    @Autowired
    SysMenuService sysMenuService;

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysRoleOperateService sysRoleOperateService;

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    @Resource
    ShiroService shiroService;

    /**
     * 初始化
     *
     * @return
     */
    @RequestMapping("/sysMenuIndex")
    public String sysMenuIndex() {

        return ftlPath + "sysMenuIndex";
    }

    /**
     * 编辑页面
     *
     * @return
     */
    @RequestMapping("/sysMenuEdit")
    public String sysMenuEdit(Model model, SysMenuReq sysMenu) {
        SysMenuResp tempSysMenu = null;

        if (sysMenu.getId() == null) {
            tempSysMenu = Builder.build(sysMenu,SysMenuResp.class);
        } else {
            tempSysMenu =  Builder.build(sysMenuService.selectById(sysMenu.getId()), SysMenuResp.class);
        }
        I18nTransformUtil.transForm(tempSysMenu, "name");
        model.addAttribute("sysMenu", tempSysMenu);

        Subject subject = SecurityUtils.getSubject();


        SysOperate sysOperate =(SysOperate) subject.getSession().getAttribute(BasicsConstantManual.SESSION_ATTRIBUTE_KEY_OPERATOR);

        shiroService.updatePermission();



        return ftlPath + "sysMenuEdit";
    }

    /**
     * 保存菜单记录
     *
     * @param sysMenu
     * @return
     */
    @RequestMapping("/sysMenuSave")
    @ResponseBody
    public ResponseModel sysMenuSaveValid(SysMenuReq sysMenu) {
        sysMenuService.save(Builder.build(sysMenu, SysMenuAO.class));
        Subject subject = SecurityUtils.getSubject();


        SysOperate sysOperate =(SysOperate) subject.getSession().getAttribute(BasicsConstantManual.SESSION_ATTRIBUTE_KEY_OPERATOR);

        shiroService.updatePermission();


        return doneSuccess();
    }

    /**
     * 根据父id查询菜单记录
     *
     * @param parentId
     * @return
     */
    @RequestMapping("/findSysMenuByPid")
    @ResponseBody
    public List<SysMenuResp> findSysMenuByPid(Long parentId) {
        List<SysMenuBO> sysMenus = sysMenuService.findByPid(parentId);
        I18nTransformUtil.transFormList(sysMenus, "name");
        return Builder.buildList(sysMenus,SysMenuResp.class);
    }

    /**
     * 根据id查询所有父类id
     *
     * @param id
     * @return
     */
    @RequestMapping("/findFatherIds")
    @ResponseBody
    public String findFatherIds(Long id) {
        String ids = sysMenuService.findFatherIds(id);

        return ids;
    }


    /**
     * 用户授权菜单展示
     * @param model
     * @param pid
     * @return
     */
    @RequestMapping(value = "/rightMenus")
    @ResponseBody
    public String right(Model model,Long pid) {

        Subject subject = SecurityUtils.getSubject();

        SysOperate sysOperate = (SysOperate) subject.getSession().getAttribute(BasicsConstantManual.SESSION_ATTRIBUTE_KEY_OPERATOR);

        List<SysMenuBO> list = sysMenuService.findByPidAndId(pid, sysOperate.getId());
        I18nTransformUtil.transFormList(list, "name");


        return JSON.toJSONString(Builder.buildList(list,SysMenuResp.class));

    }

    /**
     * 查找全部菜单
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/allMenus")
    @ResponseBody
    public List<SysMenuResp> allMenus(Long id,Long roleId) {
        logger.info("allMenus id:{},ruleId:{}",id,roleId);
        if(id ==null){
            id = 0L;
        }
        List<SysMenuResp> list=new ArrayList<>();
        List<SysMenuBO> voList = sysMenuService.findByPid(id);

        List<SysMenuResp> sysMenuRespList = Builder.buildList(voList,SysMenuResp.class);
        logger.info("allMenus size:",list.size());
        if(roleId == null){
            list = new ArrayList<>();
            return list;
        }

        SysRole sysRole = sysRoleService.findById(roleId);

        for(SysMenuResp sysMenu :sysMenuRespList){
            SysRoleMenuBO sysRoleMenu= sysRoleMenuService.selectByRoleIdAndMenuId(sysRole.getId(),sysMenu.getId());
            if(sysRoleMenu!=null){
                sysMenu.setAutoStatus(SysConstant.MENU_STATUS_OPEN);
            }else{
                sysMenu.setAutoStatus(SysConstant.MENU_STATUS_COLOSE);
            }
        }
        I18nTransformUtil.transFormList(sysMenuRespList, "name");

        return sysMenuRespList;
    }





}
