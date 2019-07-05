package com.cms.scaffold.sys.sys.bo;

import com.cms.scaffold.common.base.BaseBO;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: zjh
 * @date: 2019-03-12 23:53
 **/
@Setter
@Getter
public class SysRoleMenuBO extends BaseBO{
    /**
     * 角色id
     **/
    private Long roleId;

    /**
     * 菜单id
     **/
    private Long menuId;
}
