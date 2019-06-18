package com.cms.scaffold.common.exception;


/**
 * @author liugq
 * 投标相关异常
 */
public class TenderException extends BusinessException {

    public TenderException(BaseResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum);
    }

    public TenderException(Integer code, String message, Integer outCode) {
        super(code, message, outCode);
    }

    public TenderException(String message) {
        super(message);
    }
}
