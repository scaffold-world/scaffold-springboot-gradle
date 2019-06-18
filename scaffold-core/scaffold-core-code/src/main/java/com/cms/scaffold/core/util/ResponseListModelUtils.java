package com.cms.scaffold.core.util;

import com.github.pagehelper.PageInfo;
import com.cms.scaffold.common.base.Builder;
import com.cms.scaffold.common.base.ResponseListModel;

import java.util.List;

/**
 * @description:
 * @author: zhangjiahengpoping@gmail.com
 * @date: 2019-03-12 19:27
 **/
public class ResponseListModelUtils {

    /**
     * 转化分页中的数据对象
     * @param data
     * @param clazz
     * @return
     */
    public static ResponseListModel transform(List data, Class clazz){
        PageInfo  pageInfo = new PageInfo<>(data);
        return new ResponseListModel<>(Builder.buildList(data,clazz), pageInfo.getTotal());
    }


    /**
     * 转化分页中的数据对象
     * @param data
     * @param clazz
     * @return
     */
    public static ResponseListModel transform(ResponseListModel data, Class clazz){
        return new ResponseListModel<>(Builder.buildList(data.getData(),clazz), data.getCount());
    }
}
