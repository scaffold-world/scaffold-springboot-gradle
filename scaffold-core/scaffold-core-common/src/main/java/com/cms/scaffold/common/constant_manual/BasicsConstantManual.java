package com.cms.scaffold.common.constant_manual;

public class BasicsConstantManual {
    /**
     * session KEY:操作人
     */
    public static String SESSION_ATTRIBUTE_KEY_OPERATOR = "operator";
    /**
     * session KEY:操作人Id
     */
    public static String SESSION_ATTRIBUTE_KEY_OPERATOR_ID = "operatorId";
    /**
     * session KEY:语言
     */
    public static String SESSION_ATTRIBUTE_KEY_LANGUAGE = "language";


    /**
     * "0"
     */
    public static String ZERO = "0";

    /**
     * "1"
     */
    public static String ONE = "1";

    /**
     * "2"
     */
    public static String TWO = "2";


    /**
     * 5
     */
    public static Integer FIVE = 5;


    /**
     * session5小时失效
     */
    public static Integer SESSION_TIMES = 18000;

    // 待删除 生成自动的常量类
    /*********************** 定时任务配置start **************************************/
    /*********************** 任务状态start **************************************/
    /**
     * 计划执行中
     */
    public static final Integer BASICS_SYS_JOB_STATUS_STARTING = 1;
    /**
     * 未执行
     */
    public static final Integer BASICS_SYS_JOB_STATUS_STOP = 0;
    /*********************** 任务状态end **************************************/
    /*********************** 是否支持并发start **************************************/
    /**
     * 可以并发
     */
    public static final Integer BASICS_SYS_JOB_IS_CONCURRENT_CANNOT = 1;
    /**
     * 不能并发
     */
    public static final Integer BASICS_SYS_JOB_IS_CONCURRENT_CAN = 0;
    /*********************** 是否支持并发end **************************************/
    /*********************** 定时任务配置end **************************************/
}
