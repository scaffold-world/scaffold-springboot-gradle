package com.jiaheng.scaffold.route.operate.controller.sys;

import com.jiaheng.scaffold.common.base.Builder;
import com.jiaheng.scaffold.common.base.ResponseListModel;
import com.jiaheng.scaffold.common.base.ResponseModel;
import com.jiaheng.scaffold.common.constant_manual.BasicsConstantManual;
import com.jiaheng.scaffold.common.constant_manual.SysConstant;
import com.jiaheng.scaffold.route.operate.controller.BaseController;
import com.jiaheng.scaffold.route.operate.request.sys.SysMenuReq;
import com.jiaheng.scaffold.route.operate.response.sys.SysMenuResp;
import com.jiaheng.scaffold.route.operate.shiro.ShiroService;
import com.jiaheng.scaffold.sys.sys.ao.SysMenuAO;
import com.jiaheng.scaffold.sys.sys.bo.SysMenuBO;
import com.jiaheng.scaffold.sys.sys.bo.SysRoleMenuBO;
import com.jiaheng.scaffold.sys.sys.domain.SysOperate;
import com.jiaheng.scaffold.sys.sys.domain.SysRole;
import com.jiaheng.scaffold.sys.sys.service.SysMenuService;
import com.jiaheng.scaffold.sys.sys.service.SysRoleMenuService;
import com.jiaheng.scaffold.sys.sys.service.SysRoleOperateService;
import com.jiaheng.scaffold.sys.sys.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 商户资源管理菜单Controller
 * Created by 张嘉恒 on 2018/3/17.
 */
@Controller
@RequestMapping("/sys/sysMenu")
@Api(tags = "SysMenuController", description = "系统菜单配置")
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

    @RequestMapping("/sysMenuIndex")
    public String sysMenuIndex() {
        return ftlPath + "sysMenuIndex";
    }

    @RequestMapping("/sysMenuEdit")
    public String sysMenuEdit(Model model, Long id, Long pid) {
        SysMenuBO menu;
        if(null == id){
            menu = new SysMenuBO();
            menu.setPid(pid);
        }else{
            menu = sysMenuService.selectById(id);
        }
        model.addAttribute("sysMenu", menu);
        return ftlPath + "sysMenuEdit";
    }

    @RequestMapping("/deleteMenusById")
    @ApiOperation(value = "根据id递归删除菜单及子菜单")
    public ResponseModel deleteMenusById(@RequestParam(name = "id") Long id){
        sysMenuService.deleteMenusById(id);
        return doneSuccess();
    }

    @RequestMapping("/sysMenuSave")
    @ResponseBody
    @ApiOperation("保存新增或者修改的菜单")
    public ResponseModel sysMenuSaveValid(SysMenuReq sysMenu) {
        sysMenuService.save(Builder.build(sysMenu, SysMenuAO.class));
        shiroService.updatePermission();
        return doneSuccess();
    }

    @RequestMapping("/findAllMenus")
    @ResponseBody
    @ApiOperation(value = "根据父id查询菜单记录")
    public ResponseListModel<SysMenuBO> findAllMenus(String name) {
        List<SysMenuBO> sysMenus = sysMenuService.findAllMenus(name);
        return new ResponseListModel<>(sysMenus, (long) sysMenus.size());
    }

    @RequestMapping("/findFatherIds")
    @ResponseBody
    public String findFatherIds(Long id) {
        return sysMenuService.findFatherIds(id);
    }


    @RequestMapping(value = "/rightMenus")
    @ResponseBody
    @ApiOperation("根据pid获取到当前登陆用户所有的权限菜单")
    public List<SysMenuResp> right(Long pid) {

        Subject subject = SecurityUtils.getSubject();

        SysOperate sysOperate = (SysOperate) subject.getSession().getAttribute(BasicsConstantManual.SESSION_ATTRIBUTE_KEY_OPERATOR);

        List<SysMenuBO> list = sysMenuService.findByPidAndId(pid, sysOperate.getId());


        return Builder.buildList(list, SysMenuResp.class);

    }

    @RequestMapping(value = "/allMenus")
    @ResponseBody
    public List<SysMenuResp> allMenus(Long id, Long roleId) {
        if (id == null) {
            id = 0L;
        }
        List<SysMenuBO> voList = sysMenuService.findByPid(id);

        List<SysMenuResp> sysMenuRespList = Builder.buildList(voList, SysMenuResp.class);
        if (roleId == null) {
            return new ArrayList<>();
        }

        SysRole sysRole = sysRoleService.findById(roleId);

        for (SysMenuResp sysMenu : sysMenuRespList) {
            SysRoleMenuBO sysRoleMenu = sysRoleMenuService.selectByRoleIdAndMenuId(sysRole.getId(), sysMenu.getId());
            if (sysRoleMenu != null) {
                sysMenu.setAutoStatus(SysConstant.MENU_STATUS_OPEN);
            } else {
                sysMenu.setAutoStatus(SysConstant.MENU_STATUS_COLOSE);
            }
        }

        return sysMenuRespList;
    }


}
