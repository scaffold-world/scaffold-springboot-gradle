package com.jiaheng.scaffold.sys.sys.bo;


import com.jiaheng.scaffold.common.base.BaseBO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysConfigBO extends BaseBO {
    //名称
    private String name;

    //标识
    private String nid;

    //名称对应的值
    private String value;

    //类型 1:系统底层配置信息， 2:各种费率配置信息， 3:邮件/短信配置信息， 4:附加增值功能配置信息， 5:第三方资金托管相关的配置， 6.网站汇付天下相关配置信息， 7:短信模板替换参数配置信息
    private Integer type;

    //状态 0:不启用 1：启用
    private Integer status;

    //备注
    private String remark;

}