package com.cms.scaffold.core.cache;


import com.cms.scaffold.core.dict.ExpireTime;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 通用缓存工厂类
 */
public interface CacheFactory {

	/**
	 * 获得缓存对象
	 * @param key	
	 * @return
	 */
   Object get(String key);

	/**
	 * 获得缓存对象
	 * @param key
	 * @param clazz
	 * @param <T>
	 * @return
	 */
   <T> T get(String key, Class<T> clazz);
	
	
	/**
	 * 设置缓存
	 * @param key
	 * @param value
	 * @param cacheSec 缓存时间
	 */
	void set(String key, Object value, int cacheSec);
	
	
	/**
	 * 删除缓存
	 * @param key
	 */
	void del(String... key);
	
	/**
	 * 批量删除
	 * @param pattern
	 */
	void batchDel(String... pattern);
	
	/**
	 * 获得数值
	 * @param key
	 * @return
	 */
	double getDouble(String key);
	
	/**
	 * 设置数值
	 * @param key
	 * @param value
	 * @param cacheSec
	 */
	void setDouble(String key, double value, int cacheSec);
	
	/**
	 * 递减操作
	 * @param key
	 * @param by
	 * @return
	 */
	double decr(String key, double by);
	
	/**
	 * 递增操作
	 * @param key
	 * @param by
	 * @return
	 */
	double incr(String key, double by);
	
	/**
	 * 递减操作
	 * @param key
	 * @param by
	 * @return
	 */
	long decr(String key, long by);
	
	/**
	 * 递增操作
	 * @param key
	 * @param by
	 * @return
	 */
	long incr(String key, long by);
	
	/*********************************** map操作***********************************/
	/**
	 * 设置map缓存
	 * @param key
	 * @param map
	 */
	<T> void setMap(String key, Map<String, T> map, int cacheSec);
	/**
	 * 向map中缓存记录
	 * @param key
	 * @param map
	 */
	<T> void addMap(String key, Map<String, T> map);

	/**
	 * 取得map缓存
	 * @param key
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	<T> Map<String, T> getMap(String key, Class<T> clazz);

	/**
	 * 取得map缓存中的某个对象
	 * @param key
	 * @param field
	 * @return
	 */
	<T> T getMapField(String key, String field, Class<T> clazz);
	
	/**
	 * 批量设置map
	 * @param key
	 * @param map
	 * @param cacheSec 缓存时间（单位：秒）
	 */
	void hmset(String key, Map<String, String> map, int cacheSec);
	
	/**
	 * 设置map
	 * @param key
	 * @param field
	 * @param value
	 */
	void hset(String key, String field, String value);
	
	/**
	 * 删除map中的某个对象
	 * @author lh
	 * @date 2016年8月10日
	 * @param key
	 * @param field
	 */
	void delMapField(String key, String... field);
	
	/**
	 *  指定缓存的失效时间
	 * @author  FangJun
	 * @date 2016年8月14日
	 * @param key 缓存KEY
	 * @param seconds 失效时间(秒)
	 */
	void expire(String key, int seconds);

	/**
	 * 判断key缓存是否存在
	 * @param key
	 * @return
	 */
	boolean exists(String key);

	/**
	 *
	 * @param key
	 * @param value
	 * @param cacheTime
	 * @return
	 */
	boolean setnx(String key, String value, ExpireTime cacheTime);

	/*********************************** set操作***********************************/

	/**
	 * set中添加对象
	 * @param key
	 * @param value
	 */
	void sadd(String key, String... value);

	/**
	 * set中移出对象
	 * @param key
	 * @param value
	 */
	void srem(String key, String... value);
	/**
	 * 根据key查询set
	 * @param key
	 */
	Set<String>  getSet(String key);

	/*********************************** 列表/分页查询***********************************/
	/**
	 * 列表查询
	 * @param params
	 * @return
	 */
	List<String> list(Map<String, String> params);

	/**
	 * KEY重命名
	 * @param oldKey
	 * @param newKey
	 */
	void rename(String oldKey, String newKey);
	/**
	 *
	 * 返回给定key的有效时间，以秒为单位
	 * 	如果是-1则表示永远有效
	 * @param key
	 * @return
	 * @author zjh
	 * @date 2017-9-26
	 */
	Long ttl(String key);

	/**
	 * 存放数据到redis list
	 * @param key
	 * @param param
	 */
	public void ladd(String key, String... param);

	/**
	 * 裁剪list
	 * @param key
	 * @param start
	 * @param end
	 */
	public void ltrim(String key, long start, long end);

	/**
	 * 获取list
	 * @param key
	 * @return
	 */
	List getladd(String key);

	void pulishMessage(String channel, String message);

	/**
	 * 存放数据到redis sorted set
	 * @param key
	 * @param value
	 * @param score
	 * @param cacheSeconds
	 */
	public Long zadd(String key, String value, double score, int cacheSeconds);
}
