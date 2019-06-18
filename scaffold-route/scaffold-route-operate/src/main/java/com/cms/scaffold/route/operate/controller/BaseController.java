package com.cms.scaffold.route.operate.controller;


import com.cms.scaffold.common.base.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * Created by 张嘉恒 on 2018/3/16.
 */
@Controller
public class BaseController {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String BUSINESS_STRING = "业务异常:%s";
    private static final String PARAM_STRING = "参数异常:%s";

    protected ResponseModel doneStatus(int statusCode, String message) {
        return this.doneStatus(statusCode, message, "操作提示");
    }

    protected ResponseModel doneStatus(int statusCode, String message, Object data) {
        return ResponseModel.doneStatus(statusCode, message, data);
    }

    protected ResponseModel doneDataSuccess(Object data) {
        return ResponseModel.doneSuccessData(data);
    }



    protected ResponseModel doneSuccess() {
        return doneSuccess("操作成功！");
    }

    protected ResponseModel doneSuccess(String message) {
        return doneStatus(0, message);
    }


    protected ResponseModel doneDeleteSuccess(String message) {
        return this.doneStatus(0, message);
    }

    protected ResponseModel doneError() {
        return doneStatus(-1, "操作失败！");
    }

    protected ResponseModel doneError(String message) {
        return doneStatus(-1, message);
    }

}
