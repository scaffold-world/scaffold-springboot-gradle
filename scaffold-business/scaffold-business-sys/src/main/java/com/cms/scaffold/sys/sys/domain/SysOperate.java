package com.cms.scaffold.sys.sys.domain;

import com.cms.scaffold.common.base.BaseEntity;

import java.util.Date;

public class SysOperate extends BaseEntity {
    /** 用户名**/
    private String userName;

    private String realName;

    /** 密码**/
    private String pwd;

    /** 手机号**/
    private String mobilePhone;

    /** 最近登录ip**/
    private String loginIp;

    /** 最近登录时间**/
    private Date loginTime;

    /** 状态**/
    private Long status;

    /** 钉钉系统用户id**/
    private String openid;

    private Long roleId;
    private String roleName;

    private String partnerName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return real_name
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取密码
     *
     * @return pwd - 密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置密码
     *
     * @param pwd 密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 获取手机号
     *
     * @return mobile_phone - 手机号
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置手机号
     *
     * @param mobilePhone 手机号
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 获取最近登录ip
     *
     * @return login_ip - 最近登录ip
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置最近登录ip
     *
     * @param loginIp 最近登录ip
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取最近登录时间
     *
     * @return login_time - 最近登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置最近登录时间
     *
     * @param loginTime 最近登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Long getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取钉钉系统用户id
     *
     * @return openid - 钉钉系统用户id
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置钉钉系统用户id
     *
     * @param openid 钉钉系统用户id
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public SysOperate() {
    }

    public SysOperate(String userName, String pwd, Long status, Long partnerId, String mobilePhone, String realName) {
        this.userName = userName;
        this.pwd = pwd;
        this.status = status;
        this.mobilePhone = mobilePhone;
        this.realName = realName;
        setPartnerId(partnerId);
    }
}
