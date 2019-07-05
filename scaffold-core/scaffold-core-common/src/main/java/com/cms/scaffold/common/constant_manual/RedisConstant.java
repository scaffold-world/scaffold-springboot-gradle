/**
 * @Title: CacheConstant.java
 * @Package com.gjj.p2p.common.constant
 * @author zjh
 * @date 2017-6-28
 */
package com.cms.scaffold.common.constant_manual;

/**
 * 缓存常量类
 *
 * @author zjh
 * @date 2017-6-28
 */
public class RedisConstant {

    /**
     * 参数配置
     */
    public static final String KEY_PREFIX_CONFIG_CODE = "sys:config:code:";

    /**
     * 字典类型
     */
    public static final String SYS_DICT_NID = "sys:dict:nid";

    public static final String SYS_DICT_NID_NAME = "sys:dict:nid:%s";
    /**
     * 参数配置
     */
    public static final String USER_LOGIN_TOKEN = "user:login:token:%s";

    /** 国际化信息根据model.name缓存 **/
    public static final String SYS_I18N_MODEL_NAME_MAP = "sys:i18n:model:name:%s";


}
