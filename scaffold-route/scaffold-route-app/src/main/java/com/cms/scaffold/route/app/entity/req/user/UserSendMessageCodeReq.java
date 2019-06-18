package com.cms.scaffold.route.app.entity.req.user;

import lombok.Getter;
import lombok.Setter;

/**
 * @author JHX
 * @date 2018/11/22 14:39
 */
@Getter
@Setter
public class UserSendMessageCodeReq {
    private String mobilePhone;
    /**
     * 1 登录验证码 2 更改密码验证码
     */
    private String type;
}
