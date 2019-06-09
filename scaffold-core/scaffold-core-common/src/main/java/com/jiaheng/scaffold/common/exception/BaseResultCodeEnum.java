package com.jiaheng.scaffold.common.exception;

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

    SYSTEM_CONFIG_NOT_FOUND("配置参数未找到", 10200, 300002),
    SYSTEM_MAP_CONVERT_ERROR("对象与map转型异常", 10201, 300004),
    SYSTEM_GATEWAY_FAILED("网关参数初始化失败", 10202, 300002),
    SYSTEM_PARAMETER_INPUT_ERROR("传入参数异常", 10203, 300002),
    SYSTEM_BANK_LIMIT_NOT_SET("系统支付渠道限额设置异常", 10204, 300004),
    SYSTEM_ACCOUNT_LOG_NOT_FOUND("资金日志类型未找到", 10207, 300004),
    SYSTEM_VALIDATE_NOT_FOUND("签名参数不完整", 10209, 300002),
    SYSTEM_LOTTRY_TIME_NO("抽奖次数不足", 10205, 300004),
    SYSTEM_LOTTRY_DATE_NO("抽奖未开始", 10206, 300004),
    SYSTEM_VALIDATE_NOT_PASS("签名验证失败", 10207, 300003),
    SYSTEM_TIME_NOT_PASS("API请求过期", 10208, 300004),
    SYSTEM_LOTTRY_DATE_END("活动已经结束", 10209, 300004),
    SYSTEM_LOTTRY_DATE_START("活动未开启", 10210, 300004),
    SYSTEM_RSA_DECRYPT_PUBLIC("解密字符串异常，请检查公钥", 10211, 300004),
    SYSTEM_DEAL_NOW("已正在处理中,请耐心等待",10212,300004),
    SYSTEM_ACCOUNT_OPEN("静默开户已被关停",10213,300004),
    SYSTEM_MARKET_NOT_EXISTS("渠道未配置", 10214, 300004),



    /**
     * 20000开头用户异常
     **/
    USER_NOT_FOUND("用户不存在", 20001, 300004),
    USER_PHONE_NONE("未通过手机认证", 20002, 300004),
    USER_RECHARGE_SMALL("充值的金额不能小于1元！", 20003, 300004),
    USER_NOT_TENDER("该用户不是投资人", 20004, 300004),
    USER_NOT_REGISTER("未开通银行存管", 20005, 300004),
    USER_MONEY_NOT_ENOUGH("可用余额不足", 20006, 300004),
    USER_HAS_OPEN_ACCOUNT("用户已开户", 20007, 300004),
    USER_NOT_BIND_CARD("未绑定银行卡", 20008, 300004),
    USER_NOT_TENDER_EXPERIENCE("没有投资体验金资格", 20009, 300004),
    USER_HAS_TENDERED_EXPERIENCE("已经投资过体验金", 20010, 300004),
    USER_HAS_EXPERIENCE("您有体验金未使用", 20011, 300004),
    MOBILE_PHONE_REPEAT("该用户重复", 20012, 300004),
    BANK_UN_IDENTIFY("银行卡不识别", 20013, 300004),
    USER_HAS_BIND_REALAUTH("用户已实名认证", 20014, 300007),
    USER_HAS_FOUND("用户已存在", 20015, 300004),
    USER_NOT_RISK_EVALUATION("用户未进行风险测评", 20016, 300004),
    TRUSTEE_USER_NOT_FOUND("受托用户不存在", 20017, 30004),
    USER_NOT_BORROW("该用户不是借款人", 20018, 300004),
    USER_NOT_BORROW_AUTHORIZED("用户未进行授权",20019, 300004),
    USER_NOT_FIND_PROTOCOL("支付公司传入有误",20020, 300004),
    PAY_CHANNEL_FULL("您已经完成所有协议签约,",20021, 300005),
    PAY_CHANNEL_EMPTY("暂无支付公司可以签约,请联系客服",20022, 300004),
    USER_NOT_RIGHT_TAG_TYPE("您无权限投资此类型团标，请更换投资项目",20023, 300004),
    USER_HAS_ACTIVITY_EXPERIENCE("已经领取过该活动的体验金", 20024, 300004),
    USER_IS_OLD_ACTIVITY_EXPERIENCE("抱歉亲，您已经是51有钱老用户，不能参加此活动", 20025, 300004),
    USER_ERROR("用户名或密码错误", 20026, 300004),
    USER_ERROR_LOGIN("手机号或密码错误", 20027, 300004),
    USER_MESSAGE_ERROR("验证码有误", 20028, 300004),
    USER_PASSWORD_UPDATE_ERROR("密码更新失败", 20029, 300004),
    USER_TOKEN_ERROR("用户token失效",20030,300004),


    /**
     * 30000开头商户异常
     **/
    MERCH_SYS_MENU_PID_NOT_CHANGE("菜单父ID不允许变更", 300001, 300004),
    MERCH_ADMIN_NOT_ALLOW_OPERATE("超级管理员不允许操作", 300002, 300004),
    MERCH_ALREADY_SAME_CARDID("已经有相同的身份证号码开户，请更换身份证号码", 300003, 300004),
    MERCH_ALREADY_SAME_CODE("已经有相同的企业注册号，请更换企业注册号", 300004, 300004),
    /**
     * 40000开头订单异常
     */
    ORDER_BORROW_PACKAGE_IS_FOUND("团标记录不存在",400001,300004),

    /**
     * 50000开头券异常
     */
    COUPON_TYPE_NOT_MATCH("找不到对应的优惠券类型", 50001, 300004),



    ROCKETMQ_PRODUCE_FAIL("操作失败,请重试。原因:RcoketMQ生产消息失败", 6001, 600001),

    REPLACE_BANKCARD("请更换银行卡完成充值！", 7008, 300004),

    /**
     * 投标
     */
    BORROW_NOT_FOUND("标不存在", 8001, 300004),
    BORROW_STATUS_ERROR("标状态异常", 8002, 300004),
    BORROW_ALREADY_FULL("标已投满", 8003, 300004),
    BORROW_TENDER_OUT_LIMIT("实际投标金额不能大于单笔最多投标金额", 8004, 300004),
    BORROW_TENDER_TOO_LOW("实际投标金额不能小于单笔最少投标金额", 8005, 300004),
    BORROW_TENDER_REMAIN_ALL("剩余额度小于最小单笔投标金额，须全额投资", 8006, 300004),
    BORROW_TENDER_MONEY_ZERO("投标金额必须大于0", 8007, 300004),
    BORROW_RED_OUT_LIMIT("红包使用额度超出限额", 8008, 300004),
    BORROW_TENDER_BEYOND_REMAIN("投资金额超出剩余金额", 8009, 300004),
    BORROW_TENDER_UPDATE_ACCOUNT_ERROR("更新账户资金错误", 8010, 300004),
    BORROW_REAL_TENDER_MONEY_ZERO("实际支付金额需大于0元", 8011, 300004),
    BORROW_TENDER_TRY_LATER("投标失败，请稍后重试", 8012, 300004),
    BORROW_STATUS_REPAYMENT_START("标处在放款状态", 8013, 300004),
    BORROW_SALE_IS_OVER("该团标投资已结束", 8014, 300004),
    BORROW_SALE_NOT_BEGIN("该团标尚未开售，请稍等", 8015, 300004),
    BORROW_RED_ENVELOPE_ENABLE("无可使用红包!", 8016, 300004),
    BORROW_RED_ENVELOPE_ILLEAGLE("投资抵扣金额大于红包可用金额！", 8017, 300004),
    BORROW_RED_TENDER_LESS_START("投资金额小于红包使用的起购金额！", 8018, 300004),
    BORROW_RED_AMOUNT_BEYOND_LIMIT("抵扣金额超出红包上限金额！", 8019, 300004),
    BORROW_RED_ENVELOPE_EXPIRED("该红包已过期！", 8020, 300004),
    BORROW_TENDER_FOR_FIRST("非新用户不可投此标！", 8021, 300004),
    BORROW_NOT_USE_RED("该团标不能使用红包", 8022, 300004),
    BORROW_TENDER_SAME_USE("该团标同时使用多个卡卷", 8023, 300004),
    BORROW_UP_APR_ENABLE("无可使用加息卷!", 8024, 300004),
    BORROW_UP_APR_INEFFECTIVE("加息卷未生效!", 8025, 300004),
    BORROW_UP_APR_EXPIRED("加息卷已过期!", 8026, 300004),
    BORROW_UP_APR_NOT_RANGE("该金额不在加息卷使用范围内!", 8027, 300004),
    BORROW_EXPERIENCE_ENABLE("无可使用体验金!", 8028, 300004),
    BORROW_EXPERIENCE_INEFFECTIVE("体验金未生效!", 8029, 300004),
    BORROW_EXPERIENCE_EXPIRED("体验金已过期!", 8030, 300004),
    BORROW_EXPERIENCE_NOT_RANGE("该金额不在体验金使用范围内!", 8031, 300004),
    BORROW_EXPERIENCE_TENDER_CONFIG_ERROR("体验标配置异常", 8032, 300004),
    BORROW_REPAYMENT_IS_NULL("还款计划不存在", 8032, 300004),
    BORROW_REPAYMENT_IS_REAPY("标已还款或还款处理中", 8033, 300004),
    BORROW_REPAYMENT_GTE_REPAY_MONEY("贷款还款金额必须大于等于还款金额", 8034, 300004),
    BORROW_CONTRACTAMOUNT_GTE_LOANAMOUNT_MONEY("合同金额必须大于放款金额", 8037, 300004),
    BORROW_REPAYMENT_REPAY_CLEAR_PERIOD_ERROR("还款失败，期数有误", 8035, 300004),
    BORROW_NOT_USE_UPAPR("该团标不能使用加息券", 8022, 300004),
    BORROW_NOT_USE_FINANCIAL("该团标不能使用理财金", 8022, 300004),
    BORROW_USER_NOT_TENDER_RISk("应对该用户进行风险提示", 8040, 300004),
    BORROW_UP_APR_TIMELIMIT("加息券适用范围不符，请选择其他加息券哦~", 8041, 300004),
    BORROW_RED_ENVELOPE_TIMELIMIT("红包适用范围不符，请选择其他红包哦~", 8042, 300004),

    PROXY_COUNT_MAX("该用户单日代扣次数已达上限", 7005, 300006),
    PROXYTIME_MORE_THAN_REPAYMENTTIME("代扣时间不能超过应还时间", 7006, 300004),
    NO_PAYCHANNEL("无可用支付路由", 7007, 300004),

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
