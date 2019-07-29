package com.cms.scaffold.code.util;


import com.cms.scaffold.code.dict.ExpireTime;
import com.cms.scaffold.code.spring.SpringContextHolder;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;


/**
 * RedisTemplate操作工具类
 * 
 * @author lh
 * @version 3.0
 * @since 2016-8-29
 *
 */
final class RedisTemplateUtils {

	private static RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) SpringContextHolder.getBean(RedisTemplate.class);

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 */
	static void set(final String key, final Object value, final ExpireTime expire) {
		redisTemplate.opsForValue().set(key, value, expire.getTime(), TimeUnit.SECONDS);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	static <T> T get(final String key, Class<T> clazz) {
		return (T) redisTemplate.boundValueOps(key).get();
	}
	
	/**
	 * 读取缓存
	 * @param key
	 * @return
	 */
	static Object getObj(final String key){
		return redisTemplate.boundValueOps(key).get();
	}

	/**
	 * 删除，根据key精确匹配
	 * 
	 * @param key
	 */
	static void del(final String... key) {
		redisTemplate.delete(Arrays.asList(key));
	}

	/**
	 * 批量删除，根据key模糊匹配
	 * 
	 * @param pattern
	 */
	static void delpn(final String... pattern) {
		for (String kp : pattern) {
			redisTemplate.delete(redisTemplate.keys(kp + "*"));
		}
	}

	/**
	 * key是否存在
	 * 
	 * @param key
	 */
	static boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

}
