package com.jiaheng.scaffold.common.exception;


public class ParamValidateException extends BaseException {

    public ParamValidateException(BaseResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum);
    }

    public ParamValidateException(Integer code, String message, Integer outCode) {
        super(code, message, outCode);
    }

    public ParamValidateException(String message) {
        super(message);
    }
}
