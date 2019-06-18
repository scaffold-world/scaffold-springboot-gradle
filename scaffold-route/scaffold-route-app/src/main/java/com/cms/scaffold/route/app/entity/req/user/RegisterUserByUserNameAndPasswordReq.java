package com.cms.scaffold.route.app.entity.req.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * @author JHX
 * @date 2018/11/22 11:00
 */
@Getter
@Setter
public class RegisterUserByUserNameAndPasswordReq implements Serializable{

    private String mobilePhone;

    private String password;

    private String code;
}
