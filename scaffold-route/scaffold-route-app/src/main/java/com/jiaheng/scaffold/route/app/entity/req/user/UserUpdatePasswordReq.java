package com.jiaheng.scaffold.route.app.entity.req.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author JHX
 * @date 2018/11/26 11:46
 * @descipt 更改密码接收类
 */
@Getter
@Setter
public class UserUpdatePasswordReq implements Serializable {

    private String mobilePhone;

    private String password;

    private String code;
}
