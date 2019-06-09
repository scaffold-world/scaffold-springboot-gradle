package com.jiaheng.scaffold.sys.sys.bo;

import com.jiaheng.scaffold.common.base.BaseBO;
import lombok.Getter;
import lombok.Setter;

/**
* 
* @author: Mybatis Generator
* @date: 2019-03-04 15:17:03
*/
@Getter
@Setter
public class SysOperateLogBO extends BaseBO {
    /** 追踪日志id**/
    private String traceId;

    /** 类名**/
    private String className;

    /** 请求的地址**/
    private String requestUrl;

    /** 请求的方法名**/
    private String requestMethod;

    /** 操作人ID**/
    private Long operateId;

    /** 操作人姓名**/
    private String operateName;

    /** 请求的参数**/
    private String requestParam;

    /** 返回参数**/
    private String responseParam;
}