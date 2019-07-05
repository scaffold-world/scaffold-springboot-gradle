package com.cms.scaffold.common.base;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页查询返回结果类
 * @Author: chenweilin
 * @Date: 2018/3/21 20:08
 **/
public class ResponseListModel<T> implements Serializable {


    /**
     * 展示的数据列表
     */
    private List<T> rows;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 展示的数据列表
     */
    private List<T> footer;

    public ResponseListModel(List<T> data, Long total) {
        this.rows = data;
        this.total = total;
    }
    public ResponseListModel(List<T> data, List<T> footer,Long total) {
        this.rows = data;
        this.total = total;
        this.footer = footer;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getFooter() {
        return footer;
    }

    public void setFooter(List<T> footer) {
        this.footer = footer;
    }



}
