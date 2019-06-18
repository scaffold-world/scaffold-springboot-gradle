package com.cms.scaffold.common.base;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by linrufeng on 2017/11/30.
 */
public abstract class ToString implements Serializable {

    private static final Collection<String> fieldNames = new ArrayList();

    public ToString() {
    }

    @Override
    public String toString() {
        return toString(this);
    }

    public static String toString(Object obj) {
        return fieldNames.isEmpty()
            ? ToStringBuilder.reflectionToString(obj, ToStringStyle.SHORT_PREFIX_STYLE)
            : (new ReflectionToStringBuilder(obj, ToStringStyle.SHORT_PREFIX_STYLE))
                .setExcludeFieldNames((String[]) fieldNames.toArray(new String[fieldNames.size()]))
                .toString();
    }

    public static void addFilterField(String fieldName) {
        fieldNames.add(fieldName);
    }
}
