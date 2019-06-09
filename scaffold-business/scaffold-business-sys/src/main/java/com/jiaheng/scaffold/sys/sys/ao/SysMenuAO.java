package com.jiaheng.scaffold.sys.sys.ao;

import com.jiaheng.scaffold.common.base.BaseAO;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: zhangjiahengpoping@gmail.com
 * @date: 2019-03-12 23:15
 **/
@Setter
@Getter
public class SysMenuAO extends BaseAO{
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
}
