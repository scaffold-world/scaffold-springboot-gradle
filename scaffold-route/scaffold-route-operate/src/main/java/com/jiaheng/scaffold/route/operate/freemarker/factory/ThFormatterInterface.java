package com.jiaheng.scaffold.route.operate.freemarker.factory;

/**
 * 表头格式接口类
 * Created by 张嘉恒 on 2018/7/2.
 */
public interface ThFormatterInterface {


    /**
     * 构造生成枚举html
     * @param nid
     * @return
     */
    String buildFormatterHtml(String nid, String fieldName);
}
