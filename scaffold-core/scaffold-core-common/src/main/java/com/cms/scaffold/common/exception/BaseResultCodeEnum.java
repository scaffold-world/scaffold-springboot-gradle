package com.cms.scaffold.common.exception;

public enum BaseResultCodeEnum {

    FAIL("操作失败", 0, 300004),
    SUCCESS("操作成功", 1, 300004),
    FILED_NOT_FOUND("参数不正确", 3, 300002),

    PARAM_ERROR("参数异常", 1000, 300001),
    PARAM_NULL_ERROR("参数不允许为null", 1001, 300001),
    PARAM_EMPTY_ERROR("参数不允许为空", 1002, 300001),
    RECORD_ALREADY_EXISTS("记录已存在", 1003, 300004),
    RECORD_NO_ACCOUT_OPENED("未开通账户", 1004, 300001),

    PARAM_DEFINED("系统内部错误，请联系管理员", 9999, 300001),
    /**
     * 10000系统异常
     **/
    SYSTEM_USER_NOT_LOGIN("用户TOKEN过期", 10100, 300004),
    SYSTEM_TOKEN_NOT_EXIST("Token不存在", 10101, 300004),
    SYSTEM_UUID_NOT_FOUND("UUID不存在", 10102, 300004),
    SYSTEM_TOKEN_AUTH_FAIL("Token认证失败", 10103, 300004),
    SYSTEM_COOKIE_NOT_EXIST("cookie不存在", 10104, 300004),
    SYSTEM_UNKNOWN_ERROR("系统未知异常", 10105, 300004),

    SYSTEM_MENU_PID_NOT_CHANGE("菜单父ID不允许变更", 10200, 300004),
    SYSTEM_LOGIN_FACEBOOK_ERROR("FACEBOOK短信验证异常", 10201, 300004),


    /**
     * 20000开头用户异常
     **/
    USER_NOT_FOUND("用户不存在", 20001, 300004),
    USER_ERROR("用户名或密码错误", 20026, 300004),
    USER_ERROR_LOGIN("手机号或密码错误", 20027, 300004),
    USER_MESSAGE_ERROR("验证码有误", 20028, 300004),
    USER_PASSWORD_UPDATE_ERROR("密码更新失败", 20029, 300004),
    USER_TOKEN_ERROR("用户token失效",20030,300004),


    /**
     * 30000开头商户异常
     **/

    /**
     * 40000开头订单异常
     */

    /**
     * 50000开头券异常
     */


    /**
     * 投标
     */


    /**
     * 60000开头app版本更新异常
     */
    APP_VESION_NOT_EXIST_ERROR("app 版本不存在", 60001, 300004),
    APP_VESION_EXPIRED_ERROR("app 版本失效", 60002, 300004),
    PRODUCT_TRACELINK_NOT_EXIST_ERROR("链接已失效", 60003, 300004),
    THIRD_PRODUCT_REDIRECT_URL_NOT_EXIST_ERROR("第三方用户产品跳转地址未配置", 60006, 300004),
    AUTO_LOGIN_SET_DCUSTOM_ERROR("设置自定义参数发生异常", 60004, 300004),
    AUTO_LOGIN_PARAMS_NOT_EXIST_ERROR("未配置免登录接口必要参数", 60005, 300004),

    UNKNOW_ERROR("未知错误", 99999, 300004);



    /**
     * other
     */
    private String message;
    private Integer code;
    private Integer outCode;

    public Integer getOutCode() {
        return outCode;
    }

    BaseResultCodeEnum(String message, Integer code, Integer outCode) {
        this.message = message;
        this.code = code;
        this.outCode = outCode;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public static BaseResultCodeEnum getResultCodeEnum(Integer code) {
        for (BaseResultCodeEnum resultCodeEnum : BaseResultCodeEnum.values()) {
            if (code.equals(resultCodeEnum.getCode())) {
                return resultCodeEnum;
            }
        }
        return BaseResultCodeEnum.UNKNOW_ERROR;
    }
}
