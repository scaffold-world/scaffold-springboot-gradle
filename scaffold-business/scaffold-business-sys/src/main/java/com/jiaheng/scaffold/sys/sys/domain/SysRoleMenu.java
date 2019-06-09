package com.jiaheng.scaffold.sys.sys.domain;

import com.jiaheng.scaffold.common.base.BaseEntity;

/**
 * @Author zhangjiaheng@jianbing.com
 * @Description
 **/
public class SysRoleMenu extends BaseEntity {
    /**
     * 角色id
     **/
    private Long roleId;

    /**
     * 菜单id
     **/
    private Long menuId;

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取菜单id
     *
     * @return menu_id - 菜单id
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单id
     *
     * @param menuId 菜单id
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
