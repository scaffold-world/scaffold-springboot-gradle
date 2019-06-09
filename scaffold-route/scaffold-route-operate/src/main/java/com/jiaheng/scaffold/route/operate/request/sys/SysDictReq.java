package com.jiaheng.scaffold.route.operate.request.sys;

import com.jiaheng.scaffold.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: yangdeke@jianbing.com
 * @date: 2019-03-12 22:40
 **/
@Setter
@Getter
public class SysDictReq extends BaseEntity{
    /** 名称**/
    @NotBlank( message = "名称不能为空")
    private String name;

    /** 唯一标识**/
    private String nid;

    /** 父级id**/
    @NotNull( message = "上级目录不能为空")
    private Long pid;

    /** 值**/
    private String value;

    /** 类型  (详情见dict表basics_dict_type) **/
    private Integer type;

    /** 代码**/
    private String code;

    /** java类型 (详情见dict表basics_java_type)**/
    private String javaType;

    /** 排序**/
    private Integer sort;

    /** 状态，(详情见dict表basics_use_status) **/
    private Integer status;

    /** 备注**/
    private String remark;
}
