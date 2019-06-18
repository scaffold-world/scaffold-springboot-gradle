package com.cms.scaffold.common.base;

import com.cms.scaffold.common.constant_manual.AdvanceFilterOpConstantManual;

import java.io.Serializable;

/**
 * 自定义sql
 * Created by 张嘉恒 on 2018/4/24.
 */
public class AdvanceFilter implements Serializable{

    /**
     * 方式
     */
    private String join;

    /**
     * 左括号
     */
    private String lb;

    /**
     * 字段
     */
    private String field;

    /**
     * 条件
     */
    private String op;

    /**
     * 数值
     */
    private String value;

    /**
     * 右括号
     */
    private String rb;

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRb() {
        return rb;
    }

    public void setRb(String rb) {
        this.rb = rb;
    }

    public AdvanceFilter(String field, String op, String value) {
        this.join = AdvanceFilterOpConstantManual.ADVANCE_FILTER_JOIN_AND;
        this.field = field;
        this.op = op;
        this.value = value;
    }

    public AdvanceFilter(String join, String lb, String field, String op, String value, String rb) {
        this.join = join;
        this.lb = lb;
        this.field = field;
        this.op = op;
        this.value = value;
        this.rb = rb;
    }
}
