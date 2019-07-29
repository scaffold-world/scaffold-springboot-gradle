package com.cms.scaffold.route.operate.freemarker.impl;

import com.cms.scaffold.route.operate.freemarker.factory.ThFormatterInterface;
import com.cms.scaffold.sys.sys.bo.SysDictBO;
import com.cms.scaffold.sys.sys.service.SysDictService;
import com.cms.scaffold.code.spring.SpringContextHolder;

import java.util.List;

/**
 * 字典表头工厂实现类
 * Created by zjh on 2018/7/2.
 */
public class ThFormatterDict implements ThFormatterInterface {

    @Override
    public String buildFormatterHtml(String nid) {
        SysDictService sysDictService = SpringContextHolder.getBean(SysDictService.class);
        List<SysDictBO> sysDictModelList = sysDictService.findByPartnerNid(nid);
        StringBuilder dictHtml = new StringBuilder();
        dictHtml.append("formatter: function(value,row,index){ ");
        for(SysDictBO sysDict:sysDictModelList){
            dictHtml.append("if(value == '"+sysDict.getValue()+"'){ return '"+sysDict.getName()+"';}");
        }
        dictHtml.append("}");

        return dictHtml.toString();
    }
}
