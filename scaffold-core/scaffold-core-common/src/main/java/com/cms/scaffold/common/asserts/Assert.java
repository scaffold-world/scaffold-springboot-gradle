package com.cms.scaffold.common.asserts;

import com.cms.scaffold.common.exception.BaseResultCodeEnum;
import com.cms.scaffold.common.exception.BusinessException;
import com.cms.scaffold.common.exception.IllegalParamException;
import org.apache.commons.lang3.StringUtils;

/**
 * 断言
 */
public class Assert {

    /**
     * 判断传入的参数是否为 null
     * @param object
     */
    public static void notNull(Object object) {
        if (object == null) {
            throw new IllegalParamException(BaseResultCodeEnum.PARAM_NULL_ERROR);
        }
    }

    public static void notNull(Object object,String param){
        if (object == null) {
            throw new IllegalParamException(BaseResultCodeEnum.PARAM_NULL_ERROR,param);
        }
    }

    public static void notEMPTY(Object object,String param){
        if (object == null) {
            throw new IllegalParamException(BaseResultCodeEnum.PARAM_EMPTY_ERROR,param);
        }
    }

    /**
     * 判断传入的字符串是否为空，只含有空格的字符串会被认为不为空
     * @param text
     */
    public static void hasLength(String text) {
        if (!StringUtils.isNotEmpty(text)) {
            throw new IllegalParamException(BaseResultCodeEnum.PARAM_EMPTY_ERROR);
        }
    }

    /**
     * 判断传入的字符串是否为空，只含有空格的字符串会被认为空
     * @param text
     */
    public static void hasText(String text) {
        if (!StringUtils.isNotBlank(text)) {
            throw new IllegalParamException(BaseResultCodeEnum.PARAM_EMPTY_ERROR);
        }
    }

    public static void notNull(Object object,BaseResultCodeEnum baseResultCodeEnum){
        if (object == null) {
            throw new BusinessException(baseResultCodeEnum);
        }
    }


}
