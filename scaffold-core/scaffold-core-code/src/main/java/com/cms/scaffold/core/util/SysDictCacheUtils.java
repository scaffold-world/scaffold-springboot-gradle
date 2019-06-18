package com.cms.scaffold.core.util;

import com.cms.scaffold.common.constant_manual.RedisConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 字典缓存类
 * Created by 张嘉恒 on 2018/8/1.
 */
public class SysDictCacheUtils {

    private static Logger logger = LoggerFactory.getLogger(SysDictCacheUtils.class);


    /**
     * 根据nid和value获取字典数据
     * @param nid
     * @param value
     * @return
     */
    public static String getSysDictName(String nid,String value){
        Map<String,String> cacheMap = CacheUtils.mget(String.format(RedisConstant.SYS_DICT_NID_NAME,nid),String.class);

        if(cacheMap == null){
            logger.warn("缓存nid:{}数据不存在",nid);
            return "";
        }


        return cacheMap.get(value);
    }

    /**
     * 获取字典集合
     * @param nid
     * @return
     */
    public static Map getSysDictMap(String nid){
        return  CacheUtils.mget(String.format(RedisConstant.SYS_DICT_NID_NAME,nid),String.class);
    }
}
