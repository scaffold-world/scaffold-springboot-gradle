package com.cms.scaffold.sys.sys.ao;

import com.cms.scaffold.common.base.BaseAO;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: zjh
 * @date: 2019-03-12 22:18
 **/
@Setter
@Getter
public class SysDictAO extends BaseAO {
    /** 名称**/
    private String name;

    /** 中文对应的国际化标识ID**/
    private String i18nNid;

    /** 唯一标识**/
    private String nid;

    /** 父级id**/
    private Long pid;

    /** 值**/
    private String value;

    /** 类型  (详情见dict表basics_dict_type) **/
    private Long type;

    /** 代码**/
    private String code;

    /** java类型 (详情见dict表basics_java_type)**/
    private String javaType;

    /** 排序**/
    private Long sort;

    /** 状态，(详情见dict表basics_use_status) **/
    private Long status;

    /** 备注**/
    private String remark;

    /** 关联字典id**/
    private String relateId;
}
