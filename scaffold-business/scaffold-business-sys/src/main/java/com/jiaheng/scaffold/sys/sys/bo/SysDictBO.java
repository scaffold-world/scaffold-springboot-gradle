package com.jiaheng.scaffold.sys.sys.bo;

import com.jiaheng.scaffold.common.base.BaseBO;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: zhangjiahengpoping@gmail.com
 * @date: 2019-03-12 22:17
 **/
@Getter
@Setter
public class SysDictBO extends BaseBO {
    /** 名称**/
    private String name;

    /** 唯一标识**/
    private String nid;

    /** 父级id**/
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
