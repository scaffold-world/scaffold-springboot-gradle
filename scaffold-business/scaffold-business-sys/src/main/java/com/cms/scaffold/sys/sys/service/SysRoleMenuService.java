package com.cms.scaffold.sys.sys.service;

import com.cms.scaffold.sys.sys.bo.SysRoleMenuBO;
import com.cms.scaffold.sys.sys.domain.SysRoleMenu;
import com.cms.scaffold.common.base.BaseService;

import java.util.List;

public interface SysRoleMenuService  extends BaseService<SysRoleMenu> {
    /**
     * 根据角色得到所有菜单
     */
    List<SysRoleMenu> findByRoleId(Long roleId);

    /**
     * 根据角色跟菜单Id查找
     */
    SysRoleMenuBO selectByRoleIdAndMenuId(Long roleId, Long menuId);


    int saveRoleMenu(List<SysRoleMenu> list);


    /**
     * 删除菜单授权
     * @param uuid
     * @param roleId
     */
    void deleteMenu(String uuid, Long roleId);
}
