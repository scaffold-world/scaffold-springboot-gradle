package com.jiaheng.scaffold.common.exception;

public class BusinessException extends BaseException {
    public BusinessException(BaseResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum);
    }

    public BusinessException(Integer code, String message, Integer outCode) {
        super(code, message, outCode);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(BaseResultCodeEnum resultCodeEnum,String param){
        super(resultCodeEnum,param);
    }
}
