package com.jiaheng.scaffold.route.operate.controller.sys;


import com.jiaheng.scaffold.common.base.ResponseModel;
import com.jiaheng.scaffold.common.constant_manual.SysConstant;
import com.jiaheng.scaffold.common.util.DateUtil;
import com.jiaheng.scaffold.route.operate.controller.BaseController;
import com.jiaheng.scaffold.route.operate.shiro.MyShiroRealm;
import com.jiaheng.scaffold.sys.sys.bo.SysRoleMenuBO;
import com.jiaheng.scaffold.sys.sys.domain.SysRoleMenu;
import com.jiaheng.scaffold.sys.sys.service.SysMenuService;
import com.jiaheng.scaffold.sys.sys.service.SysRoleMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sys/sysRoleMenu")
public class SysRoleMenuController extends BaseController {
    @Autowired
    SysRoleMenuService sysRoleMenuService;

    @Autowired
    SysMenuService sysMenuService;

    @RequestMapping(value = "/addRoleMenu")
    @ResponseBody
    public ResponseModel addRoleMenu(String  uuid, Long roleId){
        List<SysRoleMenu> list = new ArrayList<>();

        uuid = uuid.replaceAll("'","");

        String [] inputUuid = uuid.split(",");

        for(String s:inputUuid){
            SysRoleMenuBO exist = sysRoleMenuService.selectByRoleIdAndMenuId(roleId,Long.parseLong(s));
            if(exist!=null){
                continue;
            }
            SysRoleMenu roleMenu= new SysRoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(Long.parseLong(s));
            roleMenu.setAddTime(DateUtil.rollHour(new Date(),-1));
            roleMenu.setUpdateTime(DateUtil.rollHour(new Date(),-1));
            list.add(roleMenu);
        }
        if(list.size()==0){
            return doneSuccess(SysConstant.EXISIST_MENU);
        }
        sysRoleMenuService.saveRoleMenu(list);

        //刷新url拦截
        //重新刷新shrio中的url
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();

        MyShiroRealm realm = (MyShiroRealm)rsm.getRealms().iterator().next();

        realm.clearCachedAuthorization();

        return doneSuccess();
    }


    @RequestMapping(value = "/deleteMenu")
    @ResponseBody
    public ResponseModel deleteMenu(String  uuid, Long roleId){

        uuid = uuid.replaceAll("'","");

        sysRoleMenuService.deleteMenu(uuid,roleId);

        return doneSuccess();
    }
}
