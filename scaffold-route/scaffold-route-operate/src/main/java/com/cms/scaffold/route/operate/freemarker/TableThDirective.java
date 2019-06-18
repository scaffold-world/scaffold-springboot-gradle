package com.cms.scaffold.route.operate.freemarker;

import com.cms.scaffold.route.operate.freemarker.factory.ThFormatterFactory;
import com.cms.scaffold.route.operate.freemarker.factory.ThFormatterInterface;
import com.cms.scaffold.route.operate.tag.TableThTag;
import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Created by 张嘉恒 on 2018/4/16.
 */

@Component
@org.springframework.context.annotation.Configuration
public class TableThDirective implements TemplateDirectiveModel {

    Logger logger = LoggerFactory.getLogger(getClass().getName());

    /**
     * FreeMarker自定义指令
     */
    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels,
                        TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        TableThTag tableThTag = new TableThTag();
        //校验参数
        try {
            //  用来将一些 key-value 的值（例如 hashmap）映射到 bean 中的属性
            BeanUtils.populate(tableThTag, map);
            if (StringUtils.isEmpty(tableThTag.getNid()) || StringUtils.isEmpty(tableThTag.getType())) {
                throw new IllegalArgumentException("nid,type不能为空");
            }
        } catch (Exception e) {
            logger.error("数据转化异常", e);
        }
        StringBuilder html = new StringBuilder();
        // 根据类型创建不同的HTML生成器
        ThFormatterInterface thFormatterInterface = ThFormatterFactory.createThFormatter(tableThTag.getType());
        if (thFormatterInterface != null) {
            String dictHtml = thFormatterInterface.buildFormatterHtml(tableThTag.getNid(), tableThTag.getFieldName());
            html.append(dictHtml);
        }
        // 执行真正指令的执行部分:
        Writer out = environment.getOut();
        out.write(html.toString());
        if (templateDirectiveBody != null) {
            templateDirectiveBody.render(environment.getOut());
        }

    }

    public static BeansWrapper getBeansWrapper() {
        BeansWrapper beansWrapper =
                new BeansWrapperBuilder(Configuration.VERSION_2_3_21).build();
        return beansWrapper;
    }

}
