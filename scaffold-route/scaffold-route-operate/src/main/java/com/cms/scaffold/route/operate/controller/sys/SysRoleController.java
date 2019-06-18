package com.cms.scaffold.route.operate.controller.sys;


import com.cms.scaffold.common.base.ResponseListModel;
import com.cms.scaffold.common.base.ResponseModel;
import com.cms.scaffold.common.util.DateUtil;
import com.cms.scaffold.route.operate.controller.BaseController;
import com.cms.scaffold.sys.sys.domain.SysRole;
import com.cms.scaffold.sys.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


@Controller
@RequestMapping("/sys/sysRole")
public class SysRoleController extends BaseController {
    public static final String ftlPath = "/sys/sysRole/";

    @Autowired
    SysRoleService sysRoleService;

    @RequestMapping("/sysRoleIndex")
    @ResponseBody
    public ResponseListModel<SysRole> sysRoleIndex(SysRole sysRoleReq) {
        ResponseListModel<SysRole> list = sysRoleService.findAll(sysRoleReq);
        return list;
    }

    @RequestMapping(value = "/sysRole")
    public String right(SysRole sysRole) {
        return ftlPath + "sysRole";

    }

    @RequestMapping(value = "/addRole")
    public String addRole() {
        return ftlPath + "addRole";

    }

    @RequestMapping(value = "/saveRole")
    @ResponseBody
    public ResponseModel addRole(String name, String remark) {

        SysRole sysRole = new SysRole();
        sysRole.setName(name);
        sysRole.setRemark(remark);
        sysRole.setStatus(1L);
        sysRole.setAddTime(DateUtil.rollHour(new Date(),-1));
        sysRole.setUpdateTime(DateUtil.rollHour(new Date(),-1));

        sysRoleService.saveRole(sysRole);

        return doneSuccess();

    }

}
