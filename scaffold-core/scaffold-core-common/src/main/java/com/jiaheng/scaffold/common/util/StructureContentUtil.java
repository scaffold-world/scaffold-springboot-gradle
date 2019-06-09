package com.jiaheng.scaffold.common.util;

import java.util.Map;

/**
 * Created with IDEA
 *
 * @author:JHX Date:2019/2/27
 * Time:10:30
 */
public class StructureContentUtil {

    public static  String structureContent(String template, Map<String, Object> map) {
        if (StringUtil.isBlank(template)) {
            return template;
        }

        if (map.isEmpty()) {
            return template;
        }
        template = StringUtil.replaceTemplate(template, map);
        return template;
    }
}
