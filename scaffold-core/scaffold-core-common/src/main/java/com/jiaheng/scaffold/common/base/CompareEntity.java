package com.jiaheng.scaffold.common.base;

import lombok.Getter;
import lombok.Setter;

/**
 * @description:比较实体类
 * @author: zhangjiahengpoping@gmail.com
 * @date: 2019-02-28 00:58
 **/
@Getter
@Setter
public class CompareEntity {

    /**
     * 字段名称
     */
    private String field;
    /**
     * 原值
     */
    private String valueOld;

    /**
     * 新值
     */
    private String valueNew;

}
