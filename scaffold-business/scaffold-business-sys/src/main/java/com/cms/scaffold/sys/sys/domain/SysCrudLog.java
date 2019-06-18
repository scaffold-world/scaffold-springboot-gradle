package com.cms.scaffold.sys.sys.domain;

import com.cms.scaffold.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysCrudLog extends BaseEntity {
    /** 追踪日志id**/
    private String traceId;

    /** 英文表名**/
    private String tableEnName;

    /** 中文表名**/
    private String tableCnName;

    /** 表主键id**/
    private Long pkId;

    /** 表外键id**/
    private Long fkId;

    /** 操作类型 字典代码:base_operate_type**/
    private String operateType;

    /** 字段**/
    private String field;

    /** 字段对应的字典**/
    private String fieldDict;

    /** 老值**/
    private String valueOld;

    /** 新值**/
    private String valueNew;

    /** 数据的字典值**/
    private String valueDict;
}