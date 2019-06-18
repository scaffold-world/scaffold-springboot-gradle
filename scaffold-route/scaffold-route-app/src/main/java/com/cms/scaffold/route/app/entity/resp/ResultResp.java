package com.cms.scaffold.route.app.entity.resp;

import com.cms.scaffold.common.exception.BaseResultCodeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResp {

    /**
     * 0操作失败,1操作成功,2授权token不唯一,3Token不存在
     */
    private int retCode;

    /**
     * 返回信息
     */
    private String retMsg;

    /**
     * 接口返回数据
     */
    private Object retData;

    private ResultResp(){

    }

    public static ResultResp buildResult(int retCode, String retMsg) {
        ResultResp resultRes = new ResultResp();
        resultRes.setRetCode(retCode);
        resultRes.setRetMsg(retMsg);
        return resultRes;
    }

    public static ResultResp buildResult(BaseResultCodeEnum resultCodeEnum) {
        ResultResp resultRes = new ResultResp();
        resultRes.setRetCode(resultCodeEnum.getCode());
        resultRes.setRetMsg(resultCodeEnum.getMessage());
        return resultRes;
    }

    public static ResultResp buildResult(BaseResultCodeEnum resultCodeEnum, Object resultData) {
        ResultResp resultRes = buildResult(resultCodeEnum);
        resultRes.setRetData(resultData);
        return resultRes;
    }

    public static ResultResp buildSuccessResult(Object resultData) {
        ResultResp resultRes = buildResult(BaseResultCodeEnum.SUCCESS);
        resultRes.setRetData(resultData);
        return resultRes;
    }

    public static ResultResp buildSuccessResult() {
        return buildResult(BaseResultCodeEnum.SUCCESS);
    }

    public static ResultResp buildFailResult(BaseResultCodeEnum resultCodeEnum) {
        ResultResp resultRes = new ResultResp();
        resultRes.setRetCode(resultCodeEnum.getCode());
        resultRes.setRetMsg(resultCodeEnum.getMessage());
        return resultRes;
    }

}
