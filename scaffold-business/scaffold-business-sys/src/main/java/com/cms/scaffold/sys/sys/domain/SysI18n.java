package com.cms.scaffold.sys.sys.domain;

import com.cms.scaffold.common.annotation.TableName;
import com.cms.scaffold.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
* 
* @author: Mybatis Generator
* @date: 2019-03-12 16:44:23
*/
@Getter
@Setter
@TableName(name = "sys_i18n")
public class SysI18n extends BaseEntity {
    /** 模块**/
    private String model;

    /** 名称**/
    private String name;

    /** 内容**/
    private String text;

    /** 中文内容**/
    private String zhCn;

    /** 英文内容**/
    private String enUs;

    /** 印尼内容**/
    private String inId;
}