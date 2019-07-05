package com.cms.scaffold.common.constant_manual;

public class SysConstant {

    /**
     * 403提示语
     */
    public static final String TIP_MESSAGE = "你没有操作权限!";

    /**
     * 账户帐号密码为空
     */
    public static final String USERNAME_PASSWORD_ISNULL = "用户名或密码不能为空";

    /**
     * 商户状态相关
     */
    public static final String PARTNER_STATUS_LOCK = "商户已经锁定或停用，请联系管理员";

    /**
     * 个人帐号相关
     */
    public static final String USER_STATUS_LOCK = "您的账号已被锁定或被停用，请联系管理员";

    public static final String USER_STATUS_STOP = "您的账号已被停用，请联系管理员";

    public static final String USERNAME_OR_PASSWORD_ERROR = "您的账号或密码输入错误";

    public static final String RESEVER_PASSWORD = "111111";

    /**
     * 菜单相关
     */
    public static final Integer MENU_STATUS_OPEN = 1;

    public static final Integer MENU_STATUS_COLOSE = 0;

    public static final String EXISIST_MENU = "请勿重复添加";

    /**
     * 设备类型 移动端
     */
    public static final String USER_DEVICE_MOBILE = "MOBILE";

    /**
     * 设备类型 移动端
     */
    public static final String USER_DEVICE_PC = "PC";

    /**
     * 个人开户同步重定向地址
     */
    public static final String PERSONAL_REGISTER_REDIRETURL = "personal.register.redirectUrl";
    /**
     * 企业开户同步重定向地址
     */
    public static final String ENTERPRISE_REGISTER_REDIRETURL = "enterprise.register.redirectUrl";

    /**
     * 个人开户异步地址
     */
    public static final String PERSONAL_REGISTER_URL = "personal.register.url";

    /**
     * 个人信息披露地址
     */
    public static final String PERSONAL_INFORMATION_DISCLOSURE_URL = "personal.information.disclosure.url";


    /**
     * 网关还款同步重定向地址
     */
    public static final String GATEWAY_REPAY_REDIRETURL = "gateway.repay.redirectUrl";

    /**
     * 新网网关接口同步重定向地址
     */
    public static final String XINGWANG_GATEWAY_REDIRECTURL = "xinwang.gateway.redirectUrl";

}
