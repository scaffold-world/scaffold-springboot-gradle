package com.cms.scaffold.common.base;


public class ResponseModel implements Result {

    private int statusCode = 200;
    private String message = "";
    private String title ="";
    private String data;

    public int getStatusCode()
    {
        return statusCode;
    }
    public void setStatusCode(int statusCode)
    {
        this.statusCode = statusCode;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public void setCode(String code) {

    }

    @Override
    public String getMsg() {
        return null;
    }

    @Override
    public void setMsg(String msg) {

    }

    @Override
    public String getSign() {
        return null;
    }

    @Override
    public void setSign(String sign) {

    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }
}
