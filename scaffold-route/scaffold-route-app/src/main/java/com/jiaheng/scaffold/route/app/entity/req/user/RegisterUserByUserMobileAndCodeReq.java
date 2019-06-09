package com.jiaheng.scaffold.route.app.entity.req.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author JHX
 * @date 2018/11/22 14:23
 */
@Getter
@Setter
public class RegisterUserByUserMobileAndCodeReq implements Serializable{
    private String mobilePhone;

    private String code;
}
