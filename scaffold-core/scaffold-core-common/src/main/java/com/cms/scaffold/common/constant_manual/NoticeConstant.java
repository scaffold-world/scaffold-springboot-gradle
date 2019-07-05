package com.cms.scaffold.common.constant_manual;

/**
 * 消息模板常量类
 * 
 */
public class NoticeConstant {

    /** 邮箱认证-发送认证链接 **/
    public static final String NOTICE_EMAIL_ACTIVE = "get_email";
    /** 通过邮箱找回密码-发送校验码 **/
    public static final String NOTICE_GET_PWD_EMAIL = "get_pwd_email";
    /** 通过手机找回密码-发送校验码 **/
    public static final String NOTICE_GET_PWD_PHONE = "get_pwd_phone";
    /** 绑定邮箱-发送校验码 **/
    public static final String NOTICE_BIND_EMAIL = "bind_email";
    /** 修改绑定邮箱-发送校验码 **/
    public static final String NOTICE_MODIFY_EMAIL = "modify_email";
    /** 绑定手机-发送校验码 **/
    public static final String NOTICE_BIND_PHONE = "bind_phone";
    /** 修改绑定手机-发送校验码 **/
    public static final String NOTICE_MODIFY_PHONE = "modify_phone";
    /** 找回支付密码 **/
    public static final String NOTICE_GET_PAYPWD = "get_paypwd";
    /** 企业用户初始化密码 **/
    public static final String NOTICE_INIT_PASSWORD = "init_password";
    /** 新借款标发布 **/
    public static final String NEW_BORROW = "new_borrow";
    /** 借款标取消 **/
    public static final String BORROW_CANCEL = "borrow_cancel";
    /** 借款标流标（借款人） **/
    public static final String BORROW_OVERTIME_CANCEL="borrow_overtime_cancel";
    /** 借款标流标（投资人） **/
    public static final String BORROWINVEST_OVERTIME_CANCEL ="borrowinvest_overtime_cancel";
    /** 借款标撤标（投资人） **/
    public static final String BORROWINVEST_CANCEL ="borrowinvest_cancel";
    /** 后台初审通过 **/
    public static final String BORROW_VERIFY_SUCCESS = "borrow_verify_success";
    /** 后台初审不通过 **/
    public static final String BORROW_VERIFY_FAIL = "borrow_verify_fail";
    /** 投标成功 **/
    public static final String INVEST_SUCCESS = "invest_success";
    /** 借款标被投资**/
    public static final String HAVE_INVEST_SUCCESS = "have_invest_success";
    /**截标通知(借款人)**/
    public static final String BORROW_UNFULL="borrow_unfull";
    /**截标通知(担保人)**/
    public static final String BORROW_UNFULL_GUARANTOR="borrow_unfull_guarantor";
    /**截标通知(投资人)**/
    public static final String BORROWINVEST_UNFULL="borrowinvest_unfull";
    /** 投标失败 **/
    public static final String INVEST_FAIL = "invest_fail";
    /** 自动投标成功 **/
    public static final String AUTO_TENDER = "auto_tender";
    /** 满标审核通过 **/
    public static final String BORROW_FULL_SUCCESS = "borrow_full_success";
    /** 满标审核通过(分账借款人) **/
    public static final String BORROWER_FULL_SUCCESS = "borrower_full_success";
    /** 满标审核通过(分账人) **/
    public static final String SPLITER_FULL_SUCCESS = "spliter_full_success";
    /** 满标审核通过给担保人 **/
    public static final String BORROW_FULL_SUCCESS_GUARANTOR = "borrow_full_success_guarantor";
    /** 满标审核通过(投资人) **/
    public static final String BORROWINVEST_FULL_SUCCESS = "borrowinvest_full_success";
    /** 满标审核失败 **/
    public static final String BORROW_FULL_FAIL = "borrow_full_fail";
    /** 满标审核失败 (投资人)**/
    public static final String BORROWINVEST_FULL_FAIL = "borrowinvest_full_fail";
    /** 借款人还款 **/
    public static final String RECEIVE_REPAY = "receive_repay";
    /** 还款成功 **/
    public static final String REPAY_SUCCESS = "repay_success";
    /** 还款成功发送给担保人 **/
    public static final String REPAY_SUCCESS_FOR_GUARANTOR = "repay_success_for_guarantor";
    /** 代偿成功 **/
    public static final String COMPENSATE_SUCCESS = "compensate_success";
    /** 代偿成功发送给担保人 **/
    public static final String COMPENSATE_SUCCESS_FOR_GUARANTOR = "compensate_success_for_guarantor";
    /** 收到投标奖励 **/
    public static final String RECEIVE_TENDER_AWARD = "receive_tender_award";
    /** 支付投标奖励 **/
    public static final String DEDUCT_BORROWER_AWARD = "deduct_borrower_award";
    /** 线上充值成功 **/
    public static final String RECHARGE_success = "recharge_success";
    /** 登录密码修改 **/
    public static final String PASSWORD_UPDATE = "password_update";
    /** 交易密码修改 **/
    public static final String PAYPWD_UPDATE = "paypwd_update";
    /** 还款提醒通知 **/
    public static final String BORROWER_REPAY_NOTICE = "borrower_repay_notice";
    /** 后台扣款通知 **/
    public static final String HOUTAI_DEDUCT_SUCCESS = "houtai_deduct_success";
    /** 认证通过 **/
    public static final String CERTIFY_SUCCESS = "certify_success";
    /** 认证未通过 **/
    public static final String CERTIFY_FAIL = "certify_fail";
    /** 债权转让成功出让人 **/
    public static final String BOND_SELL_SUCCESS = "bond_sell_success";
    /** 债权转让成功受让人 **/
    public static final String BOND_BUY_SUCCESS = "bond_buy_success";
    /** 债权转让成功受让人 **/
    public static final String BOND_NEW_SUCCESS = "bond_new_success";
    /** 债权投资人发送还款成功通知 **/
    public static final String BOND_RECEIVE_SUCCESS = "bond_receive_success";
    /** 投资人发送还款成功通知 **/
    public static final String RECEIVE_SUCCESS = "receive_success";
    /** 债权转让撤回出让人 **/
    public static final String BOND_SELL_STOP = "bond_sell_stop";
    /** 积分兑换审核不通过 **/
    public static final String SCORE_CONVERT_FAIL = "score_convert_fail";
    /** 积分商城发货通知 **/
    public static final String SCORE_DELIVERY_SUCCESS = "score_delivery_success";
    /** 找回密码邮件**/
    public static final String NOTICE_FIND_PASSWORD_MAIL="get_pwd_email";
    /** 找回密码短信**/
    public static final String NOTICE_FIND_PASSWORD_SMS="get_pwd_sms";
    /** 获取邮件**/
    public static final String NOTICE_GET_EMAIL="get_email";
    /** 修改绑定邮箱**/
    public static final String BIND_EMAIL_UPDATE="bind_email_update";
    /** 修改绑定邮箱**/
    public static final String BIND_EMAIL="bind_email";
    /** 修改手机号码**/
    public static final String MODIFY_PHONE="modify_phone";
    /** 线上充值成功**/
    public static final String RECHARGE_SUCC="recharge_succ";
    /** 提现成功**/
    public static final String DO_CASH="do_cash";
    /** 实名认证发送验证短信**/
    public static final String REALNAME_SMS="get_realname_sms";
    /** 信用额度申请审核成功**/
    public static final String CREDITAPPLY_SUCCESS="creditapply_success";
    /** 信用额度申请审核失败**/
    public static final String CREDITAPPLY_FAIL="creditapply_fail";
    /** 资料证明审核结果**/
    public static final String CRETIFICATION_SUCCESS="certification_success";
    /** 资料证明审核失败**/
    public static final String CRETIFICATION_FAIL="certification_fail";
    /** 发送逾期未还提醒**/
    public static final String OVERTIME_MESSAGE="overtime_message";
    /**发送我要借款短信验证码 */
    public static final String GET_BORROWAPPLY_SMS="get_borrowapply_sms";
    /**发送注册短信验证码 */
    public static final String GET_REGISTER_SMS = "get_register_sms";
    /**发送借款处理结果短信 */
    public static final String BORROWAPPLY_RESULT="borrowapply_result";
    /**发送修改年化率短信 */
    public static final String MODIFY_RATE_YEAR="modify_rate_year";
    /**账户金额一定时间内未使用*/
    public static final String ACCOUNT_STATIC_AMOUNT="account_static_amount";
    /** 红包发送成功通知 */
    public static final String SEND_REDPACKET_SUCCESS = "send_redpacket_success";
    /** 体验金发送成功通知 */
    public static final String SEND_EXPERIENCE_SUCCESS = "send_experience_success";
    /** 体验金发送成功通知 */
    public static final String SEND_RATE_SUCCESS = "send_rate_success";
    /** 体验标还款成功通知 */
    public static final String EXPBORROW_REPAY_SUCCESS = "expborrow_repay_success";
    /** 积分发送成功通知 */
    public static final String SEND_COIN_SUCCESS = "send_coin_success";
    /** 经验值发送成功通知 */
    public static final String SEND_EXP_SUCCESS = "send_exp_success";
    
    
    public static final int NOTICE_SEND = 1;
    public static final int NOTICE_NOT_SEND = 0;
    public static final int NOTICE_RECEIVE = 1;
    public static final int NOTICE_NOT_RECEIVE = 0;
    public static final int NOTICE_SMS = 1;
    public static final int NOTICE_EMAIL = 2;
    public static final int NOTICE_MESSAGE = 3;

    /** 短信渠道 **/
    public static final String GONGJIJIN_SMS = "gongJiJinSms";

    /** 邮件附件获取类型 **/
    public static final String ATTACHMENT_SOURCE_TYPE_SFTP = "sftp";

    /** 邮件附件获取类型 **/
    public static final String FILE_UPLOAD_TYPE_ENTERPRISE_REGISTER = "ENTERPRISE_REGISTER";


    /**
     * 验证登录短信
     */
    public static final  String  LOING_MESSAGE="1";
    /**
     * 验证修改短信
     */
    public static final  String  UPDATE_MESSAGE="2";

    /**
     * 注册短信接口
     */
    public static final  String  REGISTER_MESSAGE="3";


    /** 短信类型 */
    public static final String SEND_TYPE_LOGIN = "login";

    /** 短信类型 */
    public static final String SEND_TYPE_UPPWD = "update_pwd";

    /** 短信类型 */
    public static final String SEND_TYPE_REGISTER = "register";



}
