package com.cms.scaffold.common.exception;


/**
 * Created by linrufeng on 2017/12/18.
 */
public class BaseException extends RuntimeException {

    protected Integer code;

    protected Integer outCode;


    protected BaseResultCodeEnum resultCodeEnum;


    public BaseException(BaseResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.outCode = resultCodeEnum.getOutCode();
        this.resultCodeEnum = resultCodeEnum;
    }

    public BaseException(BaseResultCodeEnum resultCodeEnum,String param){
        super(param + "->" + resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.outCode = resultCodeEnum.getOutCode();
        this.resultCodeEnum = resultCodeEnum;
    }

    public BaseException(Integer code, String message, Integer outCode) {
        super(message);
        this.code = code;
        this.outCode = outCode;
    }

    public BaseException(String message) {
        super(message);
        this.code = 99999;
        this.outCode = 300004;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getOutCode() {
        return outCode;
    }

    public void setOutCode(Integer outCode) {
        this.outCode = outCode;
    }

    public BaseResultCodeEnum getResultCodeEnum() {
        return resultCodeEnum;
    }

    public void setResultCodeEnum(BaseResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
    }
}
