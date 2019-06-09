package com.jiaheng.scaffold.common.exception;

public enum BaseResultCodeEnum {

    FAIL("操作失败", 0, 300004),
    SUCCESS("操作成功", 1, 300004),
    FILED_NOT_FOUND("参数不正确", 3, 300002),

    PARAM_ERROR("参数异常", 1000, 300001),
    PARAM_NULL_ERROR("参数不允许为null", 1001, 300001),
    PARAM_EMPTY_ERROR("参数不允许为空", 1002, 300001),
    RECORD_ALREADY_EXISTS("记录已存在", 1003, 300004);

    /**
     * other
     */
    private String message;
    private Integer code;
    private Integer outCode;

    public Integer getOutCode() {
        return outCode;
    }

    BaseResultCodeEnum(String message, Integer code, Integer outCode) {
        this.message = message;
        this.code = code;
        this.outCode = outCode;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public static BaseResultCodeEnum getResultCodeEnum(Integer code) {
        for (BaseResultCodeEnum resultCodeEnum : BaseResultCodeEnum.values()) {
            if (code.equals(resultCodeEnum.getCode())) {
                return resultCodeEnum;
            }
        }
        return BaseResultCodeEnum.FAIL;
    }
}
