package com.jiaheng.scaffold.route.operate.freemarker.impl;

import com.jiaheng.scaffold.core.spring.SpringContextHolder;
import com.jiaheng.scaffold.route.operate.freemarker.factory.ThFormatterInterface;
import com.jiaheng.scaffold.sys.sys.bo.SysDictBO;
import com.jiaheng.scaffold.sys.sys.service.SysDictService;

import java.util.List;

/**
 * 字典表头工厂实现类
 * Created by 张嘉恒 on 2018/7/2.
 */
public class ThFormatterSelect implements ThFormatterInterface {

    @Override
    public String buildFormatterHtml(String nid, String objName) {
        SysDictService sysDictService = SpringContextHolder.getBean(SysDictService.class);
        List<SysDictBO> sysDictModelList = sysDictService.findByPartnerNid(nid);
        StringBuilder dictHtmlPer = new StringBuilder("<select name=\""+objName+"\">\n");
        StringBuilder dictHtmlSuf = new StringBuilder("</select>");
        StringBuilder dictHtml = new StringBuilder();
        dictHtml.append("<option value=''>请选择</option>");
        for(SysDictBO sysDict:sysDictModelList){
            dictHtml.append("<option value=\""+sysDict.getValue()+"\">"+sysDict.getName()+"</option>");
        }
        String html = dictHtmlPer .append( dictHtml) .append( dictHtmlSuf).toString();

        return html;
    }
}
