package com.cms.scaffold.common.base;

/**
 * 排序过滤器
 * Created by 张嘉恒 on 2018/6/12.
 */
public class OrderFilter {

    public enum OrderType {
        DESC, ASC
    }

    /**
     * 字段名
     */
    public String name;

    /**
     * 排序类型
     */
    public OrderType order;


    public OrderFilter(OrderType order, String name) {
        super();
        this.name = name;
        this.order = order;
    }

    public OrderFilter(String name) {
        super();
        this.name = name;
        this.order = OrderType.ASC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderType getOrder() {
        return order;
    }

    public void setOrder(OrderType order) {
        this.order = order;
    }
}
