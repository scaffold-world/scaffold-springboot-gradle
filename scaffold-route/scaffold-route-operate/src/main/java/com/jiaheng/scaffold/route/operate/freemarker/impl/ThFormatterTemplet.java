package com.jiaheng.scaffold.route.operate.freemarker.impl;

import com.jiaheng.scaffold.common.asserts.Assert;
import com.jiaheng.scaffold.core.spring.SpringContextHolder;
import com.jiaheng.scaffold.route.operate.freemarker.factory.ThFormatterInterface;
import com.jiaheng.scaffold.sys.sys.bo.SysDictBO;
import com.jiaheng.scaffold.sys.sys.service.SysDictService;

import java.util.List;

/**
 * 字典表头工厂实现类
 * Created by 张嘉恒 on 2018/7/2.
 */
public class ThFormatterTemplet implements ThFormatterInterface {

    @Override
    public String buildFormatterHtml(String nid, String fieldName) {
        Assert.notEMPTY(nid, "nid不能为空");
        Assert.notEMPTY(fieldName, "objName不能为空");
        SysDictService sysDictService = SpringContextHolder.getBean(SysDictService.class);
        // 通过nid查询字典类 这里不需要进行照抄 每个人都会有自己的实现方法
        List<SysDictBO> sysDictModelList = sysDictService.findByPartnerNid(nid);
        StringBuilder dictHtml = new StringBuilder();
        // 反正目的就是根据字典类生成对应的html就行了 需要生成的格式对照template原本应该有的写法就行了
        dictHtml.append("templet: function(d){ ");
        for (SysDictBO sysDict : sysDictModelList) {
            dictHtml.append("if(d." + fieldName + " == '" + sysDict.getValue() + "'){ return '" + sysDict.getName() + "';}");
        }
        dictHtml.append("}");

        return dictHtml.toString();
    }
}
