package com.cms.scaffold.route.operate.request.sys;

import com.cms.scaffold.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: zhangjiahengpoping@gmail.com
 * @date: 2019-03-12 23:13
 **/
@Getter
@Setter
public class SysMenuReq extends BaseEntity{
    /** 菜单名称**/
    @NotBlank(message = "菜单名称不能为空")
    private String name;

    /** 父级ID**/
    @NotNull(message = "父级ID不能为空")
    private Long pid;

    /** 等级**/
    private Integer levelId;

    /** 链接地址**/
    private String url;

    /** 图标**/
    private String iconCls;

    /** 状态**/
    @NotNull(message = "状态不能为空")
    private Integer status;

    /** 排序**/
    @NotNull(message = "排序不能为空")
    private Integer sort;

    /** 代码**/
    private String code;

    /** 是否可展开**/
    @NotNull(message = "是否可展开不能为空")
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
