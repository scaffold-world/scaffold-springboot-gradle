package com.jiaheng.scaffold.core.jedis;


import com.jiaheng.scaffold.core.spring.SpringContextHolder;
import com.jiaheng.scaffold.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import java.io.Serializable;
import java.util.*;

/**
 * Jedis Cache 工具类
 * 
 */
public class JedisUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(JedisUtils.class);

	private static JedisPool jedisPool = null;

	public static final String KEY_PREFIX = "gjj";

	private JedisUtils() {
	}


	/**
	 * 获取缓存
	 * @param key 键
	 * @return 值
	 */
	public static String get(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				value = jedis.get(key);
				value = StringUtil.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
			}
		} catch (Exception e) {
			LOGGER.warn("get {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return value;
	}
	
	/**
	 * 获取缓存
	 * @param key 键
	 * @return 值
	 */
	public static Object getObject(String key) {
		Object value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(getBytesKey(key))) {
				value = toObject(jedis.get(getBytesKey(key)));
			}
		} catch (Exception e) {
			LOGGER.warn("getObject {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return value;
	}
	
	/**
	 * 设置缓存
	 * @param key 键
	 * @param value 值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static String set(String key, String value, int cacheSeconds) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			result = jedis.set(key, value);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
		} catch (Exception e) {
			LOGGER.warn("set {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 设置缓存
	 * @param key 键
	 * @param value 值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static String setObject(String key, Object value, int cacheSeconds) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			result = jedis.set(getBytesKey(key), toBytes(value));
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
		} catch (Exception e) {
			LOGGER.warn("setObject {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	/**
	 * 获取List缓存
	 * @param key 键
	 * @return 值
	 */
	public static List<String> getList(String key) {
		List<String> value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				value = jedis.lrange(key, 0, -1);
				LOGGER.debug("getList {} = {}", key, value);					
			}
		} catch (Exception e) {
			LOGGER.warn("getList {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return value;
	}
	
	/**
	 * 获取List缓存
	 * @param key 键
	 * @return 值
	 */
	public static List<Object> getObjectList(String key) {
		List<Object> value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(getBytesKey(key))) {
				List<byte[]> list = jedis.lrange(getBytesKey(key), 0, -1);
				value = new ArrayList<Object>();
				for (byte[] bs : list){
					value.add(toObject(bs));
				}
			}
		} catch (Exception e) {
			LOGGER.warn("getObjectList {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return value;
	}
	
	/**
	 * 设置List缓存
	 * @param key 键
	 * @param value 值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static long setList(String key, List<String> value, int cacheSeconds) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			for (String v : value) {
				result += jedis.rpush(key,v);
			}
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
		} catch (Exception e) {
			LOGGER.warn("setList {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 设置List缓存
	 * @param <T>
	 * @param key 键
	 * @param value 值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return 
	 * @return
	 */
	public static <T> long setObjectList(String key, List<T> value, int cacheSeconds) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(getBytesKey(key))) {
				jedis.del(key);
			}
			for (Object o : value){
				result += jedis.rpush(getBytesKey(key),toBytes(o));
			}
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
		} catch (Exception e) {
			LOGGER.warn("setObjectList {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 向List缓存中添加值
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public static long listAdd(String key, String... value) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.rpush(key, value);
		} catch (Exception e) {
			LOGGER.warn("listAdd {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 向List缓存中添加值
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public static long listObjectAdd(String key, Object... value) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			for (Object o : value){
				result += jedis.rpush(getBytesKey(key), toBytes(o));
			}
		} catch (Exception e) {
			LOGGER.warn("listObjectAdd {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	

	/**
	 * 获取缓存
	 * @param key 键
	 * @return 值
	 */
	public static Set<String> getSet(String key) {
		Set<String> value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				value = jedis.smembers(key);
			}
		} catch (Exception e) {
			LOGGER.warn("getSet {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return value;
	}
	
	/**
	 * 获取缓存
	 * @param key 键
	 * @return 值
	 */
	public static Set<Object> getObjectSet(String key) {
		Set<Object> value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(getBytesKey(key))) {
				value = new HashSet<Object>();
				Set<byte[]> set = jedis.smembers(getBytesKey(key));
				for (byte[] bs : set){
					value.add(toObject(bs));
				}
			}
		} catch (Exception e) {
			LOGGER.warn("getObjectSet {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return value;
	}
	
	/**
	 * 设置Set缓存
	 * @param key 键
	 * @param value 值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static long setSet(String key, Set<String> value, int cacheSeconds) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			result = jedis.sadd(key, value.toArray(new String[value.size()]));
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
		} catch (Exception e) {
			LOGGER.warn("setSet {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 设置Set缓存
	 * @param key 键
	 * @param value 值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static long setObjectSet(String key, Set<Object> value, int cacheSeconds) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(getBytesKey(key))) {
				jedis.del(key);
			}
			for (Object o : value){
				result += jedis.sadd(getBytesKey(key),toBytes(o));
			}
			
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
		} catch (Exception e) {
			LOGGER.warn("setObjectSet {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 向Set缓存中添加值
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public static long sadd(String key, String... value) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.sadd(key, value);
		} catch (Exception e) {
			LOGGER.warn("setSetAdd {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}

	/**
	 * 向Set缓存中添加值
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public static long saddObj(String key, Object... value) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			for (Object o : value){
				result += jedis.sadd(getBytesKey(key), toBytes(o));
			}
		} catch (Exception e) {
			LOGGER.warn("setSetObjectAdd {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	/**
	 * Set缓存中删除值
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public static long srem(String key,String... value) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.srem(key, value);
		} catch (Exception e) {
			LOGGER.warn("setSetAdd {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 获取Map缓存
	 * @param key 键
	 * @return 值
	 */
	public static Map<String, String> getMap(String key) {
		Map<String, String> value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				value = jedis.hgetAll(key);
			}
		} catch (Exception e) {
			LOGGER.warn("getMap {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return value;
	}
	
	/**
	 * 获取Map缓存的某项值
	 * @param key
	 * @param field
	 * @return	
	 */
	public static String getMapField(String key,String field) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				value = jedis.hget(key, field);
			}
		} catch (Exception e) {
			LOGGER.warn("getMap {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return value;
	}
	
	/**
	 * 获取Map缓存
	 * @param key 键
	 * @return 值
	 */
	public static Map<String, Object> getObjectMap(String key) {
		Map<String, Object> value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(getBytesKey(key))) {
				value = new HashMap<String,Object>();
				Map<byte[], byte[]> map = jedis.hgetAll(getBytesKey(key));
				for (Map.Entry<byte[], byte[]> e : map.entrySet()){
					value.put(StringUtil.toString(e.getKey()), toObject(e.getValue()));
				}
			}
		} catch (Exception e) {
			LOGGER.warn("getObjectMap {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return value;
	}
	
	/**
	 * 获取Map缓存的某个对象
	 * @param key
	 * @param field
	 * @return
	 */
	public static Object getObjectMapField(String key, String field) {
		Object value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(getBytesKey(key))) {
				value =toObject(jedis.hget(getBytesKey(key), getBytesKey(field)));
			}
		} catch (Exception e) {
			LOGGER.warn("getObjectMap {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return value;
	}
	
	/**
	 * 设置Map缓存
	 * @param key 键
	 * @param value 值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static String setMap(String key, Map<String, String> value, int cacheSeconds) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			result = jedis.hmset(key, value);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
		} catch (Exception e) {
			LOGGER.warn("setMap {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 设置Map缓存
	 * @param key 键
	 * @param value 值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static String setObjectMap(String key, Map<String, Object> value, int cacheSeconds) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(getBytesKey(key))) {
				jedis.del(key);
			}
			Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
			for (Map.Entry<String, Object> e : value.entrySet()){
				map.put(getBytesKey(e.getKey()), toBytes(e.getValue()));
			}
			result = jedis.hmset(getBytesKey(key), (Map<byte[], byte[]>)map);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
		} catch (Exception e) {
			LOGGER.warn("setObjectMap {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 向Map缓存中添加值
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public static String mapPut(String key, Map<String, String> value) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.hmset(key, value);
		} catch (Exception e) {
			LOGGER.warn("mapPut {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 向Map缓存中添加值
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public static String mapObjectPut(String key, Map<String, Object> value) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
			for (Map.Entry<String, Object> e : value.entrySet()){
				map.put(getBytesKey(e.getKey()), toBytes(e.getValue()));
			}
			result = jedis.hmset(getBytesKey(key), (Map<byte[], byte[]>)map);
		} catch (Exception e) {
			LOGGER.warn("mapObjectPut {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 移除Map缓存中的值
	 * @param key 键
	 * @param mapKey
	 * @return
	 */
	public static long mapRemove(String key, String... mapKey) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.hdel(key, mapKey);
		} catch (Exception e) {
			LOGGER.warn("mapRemove {}  {}", key, mapKey, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 移除Map缓存中的值
	 * @param key 键
	 * @param mapKey
	 * @return
	 */
	public static long mapObjectRemove(String key, String mapKey) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.hdel(getBytesKey(key), getBytesKey(mapKey));
		} catch (Exception e) {
			LOGGER.warn("mapObjectRemove {}  {}", key, mapKey, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 判断Map缓存中的Key是否存在
	 * @param key 键
	 * @param mapKey
	 * @return
	 */
	public static boolean mapExists(String key, String mapKey) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.hexists(key, mapKey);
		} catch (Exception e) {
			LOGGER.warn("mapExists {}  {}", key, mapKey, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 判断Map缓存中的Key是否存在
	 * @param key 键
	 * @param mapKey
	 * @return
	 */
	public static boolean mapObjectExists(String key, String mapKey) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.hexists(getBytesKey(key), getBytesKey(mapKey));
		} catch (Exception e) {
			LOGGER.warn("mapObjectExists {}  {}", key, mapKey, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 删除缓存
	 * @param key 键
	 * @return
	 */
	public static long del(String... key) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.del(key);
		} catch (Exception e) {
			LOGGER.warn("del {}", key, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	
	/**
	 * 批量删除
	 * @param pattern
	 * @return
	 */
	public static long batchDel(String... pattern){
		long result = 0;
		if(pattern == null || pattern.length == 0){
			return result;
		}
		Jedis jedis = null;
		try {
			jedis = getResource();
			for (String kp : pattern) {
				Set<String> sets = jedis.keys(kp+"*");
				if(!CollectionUtils.isEmpty(sets)){
					String [] keys = sets.toArray(new String[sets.size()]);
					result = jedis.del(keys);					
				}
			}
			
		} catch (Exception e) {
			LOGGER.warn("del pattern: {}", pattern, e);
		} finally {
			close(jedis);
		}
		return result;		
	}
	
	/**
	 * 删除缓存
	 * @param key 键
	 * @return
	 */
	public static long delObject(String key) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(getBytesKey(key))){
				result = jedis.del(getBytesKey(key));
			}else{
				LOGGER.debug("delObject {} not exists", key);
			}
		} catch (Exception e) {
			LOGGER.warn("delObject {}", key, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 缓存是否存在
	 * @param key 键
	 * @return
	 */
	public static boolean exists(String key) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.exists(key);
		} catch (Exception e) {
			LOGGER.warn("exists {}", key, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 缓存是否存在
	 * @param key 键
	 * @return
	 */
	public static boolean existsObject(String key) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.exists(getBytesKey(key));
		} catch (Exception e) {
			LOGGER.warn("existsObject {}", key, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 递增数字
	 * @param key 键
	 * @param by 步长
	 * @return
	 */
	public static long incr(String key,long by) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if(by>0){
				result = jedis.incrBy(key, by);
			}else{
				result = jedis.incr(key);				
			}
			
		} catch (Exception e) {
			LOGGER.warn("incr key={}, by={}", key, by, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 递减数字
	 * @param key 键
	 * @param by 步长
	 * @return
	 */
	public static long decr(String key,long by) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if(by>0){
				result = jedis.decrBy(key, by);
			}else{
				result = jedis.decr(key);				
			}
			
		} catch (Exception e) {
			LOGGER.warn("decr key={}, by={}", key, by, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 递增数字
	 * @param key 键
	 * @param by 步长
	 * @return
	 */
	public static double incr(String key, double by) {
		double result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if(by>0){
				result = jedis.incrByFloat(key, by);
			}else{
				result = jedis.incrByFloat(key, 1d);			
			}
			
		} catch (Exception e) {
			LOGGER.warn("incr key={}, by={}", key, by, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 递减数字
	 * @param key 键
	 * @param by 步长
	 * @return
	 */
	public static double decr(String key, double by) {
		double result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if(by>0){
				result = jedis.incrByFloat(key, -by);
			}else{
				result = jedis.incrByFloat(key, -1d);				
			}
			
		} catch (Exception e) {
			LOGGER.warn("decr key={}, by={}, result={}", key, by,result, e);
		} finally {
			close(jedis);
		}
		return result;
	}
	
	

	/**
	 * 获取资源
	 * @return
	 * @throws JedisException
	 */
	public static Jedis getResource() throws JedisException {
		Jedis jedis = null;
		try {
			jedis = getInstance().getResource();
		} catch (JedisException e) {
			LOGGER.warn("getResource.", e);
			close(jedis);
			throw e;
		}
		return jedis;
	}

	public static JedisPool getInstance(){
		if(jedisPool==null){
			synchronized (JedisUtils.class){
				if(jedisPool==null){
					jedisPool = SpringContextHolder.getBean("jedisPool");
				}
			}
		}
		return jedisPool;
	}
	
	/**
	 * 释放资源
	 * @param jedis
	 * @param
	 */
	public static void close(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}
	
	/**
	 * 发布消息到其他节点，更新缓存时使用
	 * @param channel
	 * @param message
	 */
    public static void publish(String channel, Serializable message) {
        if (StringUtil.isBlank(channel) || message == null) {
            return;
        }
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.publish(channel, (String) message);
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
    }
        
    /**
     * getAndset
     * @param key
     * @param newVal
     * @return
     */
    public static String getAndset(String key, String newVal){
    	String oldVal = null;
    	Jedis jedis = null;
    	try {
    		jedis = getResource();
    		oldVal = jedis.getSet(key, newVal);
    	} catch (Exception e) {
    		LOGGER.error(e.getMessage(), e);
    	} finally {
    		close(jedis);
    	}
    	return oldVal;
    }
    
    /**
     * setnx
     * @param key
     * @param value
     * @return
     */
    public static Long setnx(String key, String value){
    	Long result = 0l;
    	Jedis jedis = null;
    	try {
    		jedis = getResource();
    		result = jedis.setnx(key, value);
    	} catch (Exception e) {
    		LOGGER.error(e.getMessage(), e);
    	} finally {
    		close(jedis);
    	}
    	return result;
    }
    /**
     * setnx
     * @param key
     * @param value
     * @return
     */
    public static Long setnx(String key, String value, int seconds){
    	Long result = 0l;
    	Jedis jedis = null;
    	try {
    		jedis = getResource();
    		result = jedis.setnx(key, value);
    		if(seconds > 0){
    			jedis.expire(key, seconds);
    		}
    	} catch (Exception e) {
    		LOGGER.error(e.getMessage(), e);
    	} finally {
    		close(jedis);
    	}
    	return result;
    }
    
    /**
     * 设置有效期
     * @param key
     * @param seconds
     * @return
     */
    public static Long expire(String key, int seconds){
    	Long result = 0l;
    	Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.expire(key, seconds);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return result;
    }
		
	/**
	 * 获取byte[]类型Key
	 * @param object
	 * @return
	 */
	public static byte[] getBytesKey(Object object){
		if(object instanceof String){
    		return StringUtil.getBytes((String)object);
    	}else{
    		return JsonSerializerUtils.serialize(object);
    	}
	}
	
	/**
	 * 
	 * 返回给定key的有效时间，以秒为单位
	 * 	当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。 否则，以秒为单位，返回 key 的剩余生存时间。
	 * @param key
	 * @return   
	 * @author yangdeke@jianbing.com
	 * @date 2017-9-26
	 */
	public static long ttl(String key){
		Long result = 0l;
    	Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.ttl(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return result;
		
	}
	
	/**
	 * Object转换byte[]类型
	 * @param object
	 * @return
	 */
	public static byte[] toBytes(Object object){
    	return JsonSerializerUtils.serialize(object);
	}

	/**
	 * byte[]型转换Object
	 * @param bytes
	 * @return
	 */
	public static Object toObject(byte[] bytes){
		return JsonSerializerUtils.unserialize(bytes);
	}
	
	/**
	 * 重命名
	 * @param oldkey
	 * @param newkey
	 */
	public static void rename(String oldkey, String newkey) {	
		Jedis jedis = null;
		try {			
			jedis = getResource();			
			jedis.rename(oldkey, newkey);
		}finally{
			close(jedis);
		}
	}
	
	/**
	 * 批量设置map
	 * @param key
	 * @param map
	 * @param cacheSec
	 */
	public static void hmset(String key, Map<String, String> map, int cacheSec) {
		Jedis jedis = null;
		try {			
			jedis = getResource();	
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String field = it.next();
				jedis.hset(key, field, String.valueOf(map.get(field)));
			}
			if(cacheSec > 0){
				jedis.expire(key, cacheSec);
			}
			
		}finally{
			close(jedis);
		}
	}

	/**
	 * 设置map
	 * @param key
	 * @param field
	 * @param value
	 */
	public static void hset(String key, Object field, Object value) {
		Jedis jedis = null;
		try {			
			jedis = getResource();	
			jedis.hset(getBytesKey(key), getBytesKey(field), getBytesKey(value));
		}finally{
			close(jedis);
		}
	}
	
	/**
	 * 取得hash的 len
	 * @param key
	 * @return
	 */
	public static Long hlen(final String key) {
		Jedis jedis = null;
		Long result = 0l;
		try {
			jedis = getResource();
			result = jedis.hlen(getBytesKey(key));
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 取得hash的 keys
	 * @param key
	 * @return
	 */
	public static Set<byte[]> hkeys(final String key) {
		Jedis jedis = null;
		Set<byte[]> result = null;
		try {
			jedis = getResource();
			result = jedis.hkeys(getBytesKey(key));
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 取得hash的values
	 * @param key
	 * @return
	 */
	public static List<byte[]> hvals(final String key) {
		Jedis jedis = null;
		List<byte[]> result = null;
		try {
			jedis = getResource();
			result = jedis.hvals(getBytesKey(key));
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 获取byte[]类型Key
	 * @param key
	 * @return
	 */
	public static Object getObjectKey(byte[] key){
		try{
			return StringUtil.toString(key);
		}catch(UnsupportedOperationException uoe){
			try{
				return JedisUtils.toObject(key);
			}catch(UnsupportedOperationException uoe2){
				uoe2.printStackTrace();
			}
		}
		return null;
	}


	/**
	 * 在头部添加字符串值存储在列表. 假如key不存在，则创建空的列表
	 * 假如key存在，但不是列表对象时，则出现异常
	 * @param key
	 * @param param
	 */
	public static void ladd(String key ,String... param) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.lpush(key,param);
		} catch (Exception e) {
			LOGGER.warn("ladd{}", key, e);
		} finally {
			close(jedis);
		}

	}

	/**
	 * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
	 * @param key
	 * @param start
	 * @param end
	 */
	public static void ltrim(String key,long start,long end){
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.ltrim(key,start,end);
		} catch (Exception e) {
			LOGGER.warn("trim{}", key, e);
		} finally {
			close(jedis);
		}
	}

	/**
	 * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
	 * @param key
	 * @return
	 */
	public static List<String> getladd(String key){
		Jedis jedis = null;
		List<String> list =null;
		try {
			jedis = getResource();
			list = jedis.lrange(key,0,-1);
		} catch (Exception e) {
			LOGGER.warn("lrange{}", key, e);
		} finally {
			close(jedis);
		}
		return  list;
	}


	/**
	 * 设置sorted set缓存
	 * @param key 键
	 * @param value 值
	 * @Param score 值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static Long zadd(String key, String value, double score,int cacheSeconds) {
		Long result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.zadd(key, score,value);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
		} catch (Exception e) {
			LOGGER.warn("zadd {} = {}", key, value, e);
		} finally {
			close(jedis);
		}
		return result;
	}
}
