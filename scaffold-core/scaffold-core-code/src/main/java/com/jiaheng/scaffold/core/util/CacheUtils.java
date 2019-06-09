/**  
 * @Title: CacheUtils.java
 * @Package com.jiaheng.scaffold.p2p.core.core.util
 * 
 * @author yangdeke@jianbing.com
 * @date 2017-6-29
 */
package com.jiaheng.scaffold.core.util;

import com.alibaba.fastjson.JSONObject;
import com.jiaheng.scaffold.core.cache.CacheFactory;
import com.jiaheng.scaffold.core.cache.RedisCache;
import com.jiaheng.scaffold.core.dict.ExpireTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 通用缓存工具类
 * 
 * @author yangdeke@jianbing.com
 * @date 2017-6-29
 */
public class CacheUtils {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CacheUtils.class);

	private static CacheFactory cache = null;

	/**
	 * 目前系统单缓存
	 */
	static {
		cache = new RedisCache();
	}

	/**
	 * 删除缓存<br>
	 * 根据key精确匹配删除
	 * 
	 * @param key
	 */
	public static void del(String... key) {
		if (cache != null) {
			cache.del(key);
		}
	}

	/**
	 * 批量删除<br>
	 * （该操作会执行模糊查询，请尽量不要使用，以免影响性能或误删）
	 * 
	 * @param pattern
	 */
	public static void batchDel(String... pattern) {
		RedisTemplateUtils.delpn(pattern);
	}

	/**
	 * 取得缓存（int型）
	 * 
	 * @param key
	 * @return
	 */
	public static Integer getInt(String key) {
		if (cache != null) {
			return cache.get(key, Integer.class);
		}
		return null;
	}

	/**
	 * 取得缓存（字符串类型）
	 * 
	 * @param key
	 * @return
	 */
	public static String getStr(String key) {
		if (cache != null) {
			return cache.get(key, String.class);
		}
		return null;
	}

	/**
	 * 取得缓存（字符串类型）
	 * 
	 * @param key
	 * @return
	 */
	public static String getStr(String key, boolean retain) {
		if (cache != null) {
			String value = cache.get(key, String.class);
			if (!retain && value != null) {
				cache.del(key);
			}
			return value;
		}
		return null;
	}

	/**
	 * 获取缓存<br>
	 * 注：基本数据类型(Character除外)，请直接使用get(String key, Class<T> clazz)取值
	 * 
	 * @param key
	 * @return
	 */
	public static Object getObj(String key) {
		Object obj = null;
		if (cache != null) {
			obj = cache.get(key);
		}
		return obj;
	}

	/**
	 * 获取缓存<br>
	 * 注：java 8种基本类型的数据请直接使用get(String key, Class<T> clazz)取值
	 * 
	 * @param key
	 * @param retain
	 *            是否保留
	 * @return
	 */
	public static Object getObj(String key, boolean retain) {
		Object obj = null;
		if (cache != null) {
			obj = cache.get(key);
			if (!retain && obj != null) {
				cache.del(key);
			}
		}
		return obj;
	}

	/**
	 * 获取缓存<br>
	 * 注：该方法暂不支持Character数据类型
	 * 
	 * @param key
	 *            key
	 * @param clazz
	 *            类型
	 * @return
	 */
	public static <T> T get(String key, Class<T> clazz) {
		if (cache != null) {
			return cache.get(key, clazz);
		}
		return null;
	}

	/**
	 * 获取缓存json对象<br>
	 * 
	 * @param key
	 *            key
	 * @param clazz
	 *            类型
	 * @return
	 */
	public static <T> T getJson(String key, Class<T> clazz) {
		if (cache != null) {
			return JSONObject.parseObject((cache.get(key, String.class)),
					clazz);
		}
		return null;
	}

	/**
	 * 将value对象写入缓存
	 * 
	 * @param key
	 * @param value
	 * @param time
	 *            失效时间(秒)
	 */
	public static void set(String key, Object value, ExpireTime time) {
		if (cache != null) {
			cache.set(key, value, time.getTime());
		}
	}

	/**
	 * 将value对象写入缓存
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 *            失效时间(秒)
	 */
	public static void set(String key, Object value, int seconds) {
		if (cache != null) {
			cache.set(key, value, seconds);
		}
	}

	/**
	 * 将value对象以JSON格式写入缓存
	 * 
	 * @param key
	 * @param value
	 * @param time
	 *            失效时间(秒)
	 */
	public static void setJson(String key, Object value, ExpireTime time) {
		if (cache != null) {
			cache.set(key, JSONObject.toJSONString(value), time.getTime());
		}
	}

	/**
	 * 更新key对象field的值
	 * 
	 * @param key
	 *            缓存key
	 * @param field
	 *            缓存对象field
	 * @param value
	 *            缓存对象field值
	 */
	public static void setJsonField(String key, String field, String value) {
		if (cache != null) {
			JSONObject obj = JSONObject.parseObject(cache.get(key, String.class));
			if (obj != null) {
				obj.put(field, value);
				cache.set(key, obj.toJSONString(), 0);
			}
		}
	}

	/**
	 * 递减操作
	 * 
	 * @param key
	 * @param by
	 * @return
	 */
	public static double decr(String key, double by) {
		if (cache != null) {
			return cache.decr(key, by);
		}
		return 0d;
	}

	/**
	 * 递增操作
	 * 
	 * @param key
	 * @param by
	 * @return
	 */
	public static double

	incr(String key, double by) {
		if (cache != null) {
			return cache.incr(key, by);
		}
		return 0d;
	}

	/**
	 * 递减操作
	 * 
	 * @param key
	 * @param by
	 * @return
	 */
	public static long decr(String key, long by) {
		if (cache != null) {
			return cache.decr(key, by);
		}
		return 0l;
	}

	/**
	 * 递增操作
	 * 
	 * @param key
	 * @param by
	 * @return
	 */
	public static long incr(String key, long by) {
		if (cache != null) {
			return cache.incr(key, by);
		}
		return 0l;
	}

	/**
	 * 获取double类型值
	 * 
	 * @param key
	 * @return
	 */
	public static double getDouble(String key) {
		if (cache != null) {
			return cache.getDouble(key);
		}
		return 0d;
	}

	/**
	 * 设置double类型值
	 * 
	 * @param key
	 * @param value
	 * @param time
	 *            失效时间(秒)
	 */
	public static void setDouble(String key, double value, ExpireTime time) {
		if (cache != null) {
			cache.setDouble(key, value, time.getTime());
		}
	}

	/**
	 * 将map写入缓存
	 * 
	 * @param key
	 * @param map
	 * @param time
	 *            失效时间(秒)
	 */
	public static <T> void setMap(String key, Map<String, T> map,
			ExpireTime time) {
		if (cache != null) {
			cache.setMap(key, map, time.getTime());
		}
	}

	/**
	 * 将map写入缓存
	 * 
	 * @param key
	 * @param
	 * @param time
	 *            失效时间(秒)
	 */
	@SuppressWarnings("unchecked")
	public static <T> void setMap(String key, T obj, ExpireTime time) {
		if (cache != null) {
			Map<String, String> map = (Map<String, String>) JSONObject.parseObject(JSONObject.toJSONString(obj), Map.class);
			cache.hmset(key, map, time.getTime());
		}
	}

	/**
	 * 向key对应的map中添加缓存对象
	 * 
	 * @param key
	 * @param map
	 */
	public static <T> void addMap(String key, Map<String, T> map) {
		if (cache != null) {
			cache.addMap(key, map);
		}
	}

	/**
	 * 向key对应的map中添加缓存对象
	 * 
	 * @param key
	 *            cache对象key
	 * @param field
	 *            map对应的key
	 * @param value
	 *            值
	 */
	public static void addMap(String key, String field, String value) {
		if (cache != null) {
			cache.hset(key, field, value);
		}
	}

	/**
	 * 向key对应的map中添加缓存对象
	 * 
	 * @param key
	 *            cache对象key
	 * @param field
	 *            map对应的key
	 * @param obj
	 *            对象
	 */
	public static <T> void addMap(String key, String field, T obj) {
		if (cache != null) {
			if (obj instanceof String || obj instanceof Number) {
				cache.hset(key, field, obj.toString());
			} else {
				Map<String, T> map = new HashMap<String, T>();
				map.put(field, obj);
				cache.addMap(key, map);
			}
		}
	}

	/**
	 * 获取map缓存
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static <T> Map<String, T> mget(String key, Class<T> clazz) {
		if (cache != null) {
			return cache.getMap(key, clazz);
		}
		return null;
	}

	/**
	 * 获取map缓存，并转为<T>类型对象
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static <T> T getMap(String key, Class<T> clazz) {
		if (cache != null) {
			Map<String, String> map = cache.getMap(key, String.class);
			return JSONObject.parseObject(JSONObject.toJSONString(map), clazz);
		}
		return null;
	}

	/**
	 * 获取map缓存中的某个对象
	 * 
	 * @param key
	 * @param field
	 * @param clazz
	 * @return
	 */
	public static <T> T getMapField(String key, String field, Class<T> clazz) {
		if (cache != null) {
			return cache.getMapField(key, field, clazz);
		}
		return null;
	}

	/**
	 * 删除map中的某个对象
	 * 
	 * @author lh
	 * @date 2016年8月10日
	 * @param key
	 *            map对应的key
	 * @param field
	 *            map中该对象的key
	 */
	public void delMapField(String key, String... field) {
		if (cache != null) {
			cache.delMapField(key, field);
		}
	}

	public static boolean setnx(String key, String value, ExpireTime cacheTime) {
		if (cache != null) {
			return cache.setnx(key, value, cacheTime);
		}
		return false;
	}

	/**
	 * 指定缓存的失效时间
	 * 
	 * @author FangJun
	 * @date 2016年8月14日
	 * @param key
	 *            缓存KEY
	 * @param time
	 *            失效时间(秒)
	 */
	public static void expire(String key, ExpireTime time) {
		if (cache != null) {
			cache.expire(key, time.getTime());
		}
	}

	/**
	 * 指定缓存的失效时间
	 * 
	 * @author FangJun
	 * @date 2016年8月14日
	 * @param key
	 *            缓存KEY
	 * @param seconds
	 *            失效时间(秒)
	 */
	public static void expire(String key, int seconds) {
		if (cache != null) {
			cache.expire(key, seconds);
		}
	}

	/**
	 * 添加set
	 * 
	 * @param key
	 * @param value
	 */
	public static void sadd(String key, String... value) {
		if (cache != null) {
			cache.sadd(key, value);
		}
	}

	/**
	 * 删除set集合中的对象
	 * 
	 * @param key
	 * @param value
	 */
	public static void srem(String key, String... value) {
		if (cache != null) {
			cache.srem(key, value);
		}
	}
	
	/**
	 * 根据key获取set
	 * 
	 * @param key
	 * @param
	 */
	public static Set<String>  getSet(String key) {
		if (cache != null) {
			return cache.getSet(key);
		}
		return null;
	}

	/**
	 * 取得列表
	 * 
	 * @param params
	 * @return
	 */
	public static List<String> list(Map<String, String> params) {
		if (cache != null) {
			return cache.list(params);
		}
		return null;
	}

	/**
	 * 短信缓存
	 * 
	 * @param key
	 * @param value
	 * @param time
	 */
	public static void setIntForPhone(String key, Object value, int time) {
		if (cache != null) {
			cache.set(key, JSONObject.toJSONString(value), time);
		}
	}

	/**
	 * 判断key对应的缓存是否存在
	 * 
	 * @param key
	 * @return
	 */
	public static boolean exists(String key) {
		if (cache != null) {
			return cache.exists(key);
		}
		return false;
	}
	/**
	 * 
	 * 返回给定key的有效时间，以秒为单位
	 * 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。 否则，以秒为单位，返回 key 的剩余生存时间。
	 * @param key
	 * @return   
	 * @author yangdeke@jianbing.com
	 * @date 2017-9-26
	 */
	public static Long ttl(String key){
		if (cache != null) {
			return cache.ttl(key);
		}
		return (long)0;
	}

	public static void ladd(String key ,String... param){
		if (cache != null) {
			cache.ladd(key,param);
		}
	}

	public static void ltrim(String key ,long start,long end){
		if (cache != null) {
			cache.ltrim(key,start,end);
		}
	}


	public static List<String> getladd(String key){
		if (cache != null) {
			return cache.getladd(key);
		}
		return null;
	}

	public static void pulishMessage(String channel,String message){
		if (cache != null) {
			cache.pulishMessage(channel,message);
		}
	}


}
