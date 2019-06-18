package com.cms.scaffold.common.base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页查询返回结果类
 * @Author: zhangjiahengpoping@gmail.com
 * @Date: 2018/3/21 20:08
 **/
@Data
public class ResponseListModel<T> implements Serializable {

    /**
     * 返回状态 0=成功
     */
    private Integer code;

    /**
     * 返回提示信息
     */
    private String msg;
    /**
     * 展示的数据列表
     */
    private List<T> data;

    /**
     * 总记录数
     */
    private Long count;

    public ResponseListModel(List<T> data, Long total) {
        this.code = ResponseModel.CODE_SUCCESS;
        this.data = data;
        this.count = total;
        this.msg = ResponseModel.MSG_SUCCESS;
    }



}
