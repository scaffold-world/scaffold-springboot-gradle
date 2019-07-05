package com.cms.scaffold.route.operate.controller;


import com.cms.scaffold.common.base.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * Created by zjh on 2018/3/16.
 */
@Controller
public class BaseController {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String BUSINESS_STRING = "业务异常:%s";
    private static final String PARAM_STRING = "参数异常:%s";

    protected ResponseModel doneStatus(int statusCode, String message) {
        return this.doneStatus(statusCode, message, "操作提示");
    }

    protected ResponseModel doneStatus(int statusCode, String message, String title) {
        ResponseModel m = new ResponseModel();
        m.setStatusCode(statusCode);
        m.setTitle(title);
        m.setMessage(message);
        return m;
    }

    protected ResponseModel doneDataSuccess(String data) {
        ResponseModel m = new ResponseModel();
        m.setStatusCode(200);
        m.setTitle("操作提示");
        m.setMessage("操作成功！");
        m.setData(data);
        return m;
    }



    protected ResponseModel doneSuccess() {
        return doneSuccess("操作成功！");
    }

    protected ResponseModel doneSuccess(String message) {
        return doneStatus(200, message);
    }


    protected ResponseModel doneDeleteSuccess(String message) {
        return this.doneStatus(200, message);
    }

    protected ResponseModel doneError() {
        return doneStatus(300, "操作失败！");
    }

    protected ResponseModel doneError(String message) {
        return doneStatus(300, message);
    }

}
