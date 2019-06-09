package com.jiaheng.scaffold.common.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseModel implements Serializable {
    public static final String MSG_SUCCESS = "操作成功";
    public static final Integer CODE_SUCCESS = 0;

    private int code = 0;
    private String msg = "";
    private Object data;

    private ResponseModel(Integer code, String message, Object data){
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public static ResponseModel doneSuccess(){
        return new ResponseModel(CODE_SUCCESS, MSG_SUCCESS, null);
    }

    public static ResponseModel doneSuccessMsg(String message){
        return new ResponseModel(CODE_SUCCESS, message, null);
    }

    public static ResponseModel doneSuccessDataMag(String message, Object data){
        return new ResponseModel(CODE_SUCCESS, message, data);
    }
    public static ResponseModel doneSuccessData(Object data){
        return new ResponseModel(CODE_SUCCESS, MSG_SUCCESS, data);
    }

    public static ResponseModel doneStatus(Integer code, String message, Object data){
        return new ResponseModel(code, message, data);
    }
}
