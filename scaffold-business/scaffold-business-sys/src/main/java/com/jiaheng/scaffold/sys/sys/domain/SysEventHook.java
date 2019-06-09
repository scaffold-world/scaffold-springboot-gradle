package com.jiaheng.scaffold.sys.sys.domain;

import com.jiaheng.scaffold.common.base.BaseEntity;

public class SysEventHook extends BaseEntity {
    /** 名称**/
    private String name;

    /** 源事件对象bean**/
    private String serviceBean;

    /** 源事件方法**/
    private String serviceMethod;

    /** 状态:1启动 0禁止  字典代码:basis_status**/
    private String status;

    /** 执行类型  java代表执行代码 sql代表执行**/
    private String executeType;

    /** 执行sql**/
    private String executeSql;

    /** 执行sql参数**/
    private String executeSqlParam;

    /** 执行对象类**/
    private String executeBean;

    /** 执行对象方法**/
    private String executeMethod;

    /** 执行对象参数**/
    private String executeParam;

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取源事件对象bean
     *
     * @return service_bean - 源事件对象bean
     */
    public String getServiceBean() {
        return serviceBean;
    }

    /**
     * 设置源事件对象bean
     *
     * @param serviceBean 源事件对象bean
     */
    public void setServiceBean(String serviceBean) {
        this.serviceBean = serviceBean;
    }

    /**
     * 获取源事件方法
     *
     * @return service_method - 源事件方法
     */
    public String getServiceMethod() {
        return serviceMethod;
    }

    /**
     * 设置源事件方法
     *
     * @param serviceMethod 源事件方法
     */
    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    /**
     * 获取状态:1启动 0禁止  字典代码:basis_status
     *
     * @return status - 状态:1启动 0禁止  字典代码:basis_status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态:1启动 0禁止  字典代码:basis_status
     *
     * @param status 状态:1启动 0禁止  字典代码:basis_status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取执行类型  java代表执行代码 sql代表执行
     *
     * @return execute_type - 执行类型  java代表执行代码 sql代表执行
     */
    public String getExecuteType() {
        return executeType;
    }

    /**
     * 设置执行类型  java代表执行代码 sql代表执行
     *
     * @param executeType 执行类型  java代表执行代码 sql代表执行
     */
    public void setExecuteType(String executeType) {
        this.executeType = executeType;
    }

    /**
     * 获取执行sql
     *
     * @return execute_sql - 执行sql
     */
    public String getExecuteSql() {
        return executeSql;
    }

    /**
     * 设置执行sql
     *
     * @param executeSql 执行sql
     */
    public void setExecuteSql(String executeSql) {
        this.executeSql = executeSql;
    }

    /**
     * 获取执行sql参数
     *
     * @return execute_sql_param - 执行sql参数
     */
    public String getExecuteSqlParam() {
        return executeSqlParam;
    }

    /**
     * 设置执行sql参数
     *
     * @param executeSqlParam 执行sql参数
     */
    public void setExecuteSqlParam(String executeSqlParam) {
        this.executeSqlParam = executeSqlParam;
    }

    /**
     * 获取执行对象类
     *
     * @return execute_bean - 执行对象类
     */
    public String getExecuteBean() {
        return executeBean;
    }

    /**
     * 设置执行对象类
     *
     * @param executeBean 执行对象类
     */
    public void setExecuteBean(String executeBean) {
        this.executeBean = executeBean;
    }

    /**
     * 获取执行对象方法
     *
     * @return execute_method - 执行对象方法
     */
    public String getExecuteMethod() {
        return executeMethod;
    }

    /**
     * 设置执行对象方法
     *
     * @param executeMethod 执行对象方法
     */
    public void setExecuteMethod(String executeMethod) {
        this.executeMethod = executeMethod;
    }

    /**
     * 获取执行对象参数
     *
     * @return execute_param - 执行对象参数
     */
    public String getExecuteParam() {
        return executeParam;
    }

    /**
     * 设置执行对象参数
     *
     * @param executeParam 执行对象参数
     */
    public void setExecuteParam(String executeParam) {
        this.executeParam = executeParam;
    }
}