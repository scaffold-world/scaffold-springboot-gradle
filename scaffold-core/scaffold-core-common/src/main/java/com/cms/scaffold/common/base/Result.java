package com.cms.scaffold.common.base;

import java.io.Serializable;

/**
 * @author 林如风
 * @since 2018年1月31日
 */
public interface Result<T> extends Serializable {

    String SUCCESS_CODE = "0";

    String SUCCESS_MSG = "成功";

    /**
     * 获取 code
     */
    String getCode();
    /**
     * 设置 code
     */
    void setCode(String code);
    /**
     * 获取 msg
     */
    String getMsg();
    /**
     * 设置 msg
     */
    void setMsg(String msg);
    /**
     * 获取 sign
     */
    String getSign();
    /**
     * 设置 sign
     */
    void setSign(String sign);
    /**
     * 获取 data
     */
    String getData();
    /**
     * 设置 data
     */
    void setData(String data);
}
