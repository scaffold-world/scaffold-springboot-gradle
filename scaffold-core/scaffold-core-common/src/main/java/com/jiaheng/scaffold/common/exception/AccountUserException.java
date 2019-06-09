package com.jiaheng.scaffold.common.exception;


/**
 * @author liugq
 * 用户、账户相关异常
 */
public class AccountUserException extends BusinessException {

    public AccountUserException(BaseResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum);
    }

    public AccountUserException(Integer code, String message, Integer outCode) {
        super(code, message, outCode);
    }

    public AccountUserException(String message) {
        super(message);
    }
}
