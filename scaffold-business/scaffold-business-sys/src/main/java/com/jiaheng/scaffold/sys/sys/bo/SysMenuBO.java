package com.jiaheng.scaffold.sys.sys.bo;

import com.jiaheng.scaffold.common.base.BaseBO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description:
 * @author: yangdeke@jianbing.com
 * @date: 2019-03-12 23:22
 **/
@Setter
@Getter
public class SysMenuBO extends BaseBO{

    /** 菜单名称**/
    private String name;

    /** 父级ID**/
    private Long pid;

    /** 等级**/
    private Integer levelId;

    /** 链接地址**/
    private String url;

    /** 图标**/
    private String iconCls;

    /** 状态**/
    private Integer status;

    /** 排序**/
    private Integer sort;

    /** 代码**/
    private String code;

    /** 是否可展开**/
    private String state;

    /** 资源类型**/
    private String resourceType;

    /** 添加用户**/
    private String addUser;

    /** 修改用户**/
    private String updateUser;

    /** 备注**/
    private String remark;

    /**
     * 将子菜单查出来
     */
    private List<SysMenuBO> sysMenuBOList;
}
