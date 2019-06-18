package com.cms.scaffold.common.exception;

public class IllegalParamException extends BaseException {
    public IllegalParamException(BaseResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum);
    }

    public IllegalParamException(Integer code, String message, Integer outCode) {
        super(code, message, outCode);
    }

    public IllegalParamException(String message) {
        super(message);
    }

    public IllegalParamException(BaseResultCodeEnum resultCodeEnum, String param) {
        super(resultCodeEnum,param);
    }
}
