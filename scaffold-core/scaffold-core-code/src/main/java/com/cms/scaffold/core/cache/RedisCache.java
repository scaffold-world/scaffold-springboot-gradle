package com.cms.scaffold.core.cache;

import com.cms.scaffold.common.util.Reflections;
import com.cms.scaffold.common.util.StringUtil;
import com.cms.scaffold.core.dict.ExpireTime;
import com.cms.scaffold.core.jedis.JedisUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

import java.math.BigDecimal;
import java.util.*;

/**
 * 
 * 
 * @author zjh
 * @date 2017-6-28
 */
public class RedisCache implements CacheFactory {
	
	@Override
	public Object get(String key) {
		return JedisUtils.getObject(key);
	}

	@Override
	public void set(String key, Object value, int cacheSec) {
		if(value instanceof String || value instanceof Number || value instanceof Boolean){
			JedisUtils.set(key, value.toString(), cacheSec);
		}else{
			JedisUtils.setObject(key, value, cacheSec);
		}
	}

	@Override
	public void del(String... key) {
		JedisUtils.del(key);
	}
	@Override
	public void batchDel(String... pattern){
		JedisUtils.batchDel(pattern);
	}

	@Override
	public double decr(String key, double by) {
		return JedisUtils.decr(key, by);
	}

	@Override
	public double incr(String key, double by) {
		return JedisUtils.incr(key, by);
	}
	
	@Override
	public long decr(String key, long by) {
		return JedisUtils.decr(key, by);
	}

	@Override
	public long incr(String key, long by) {
		return JedisUtils.incr(key, by);
	}
	

	@Override
	public double getDouble(String key) {
		String value = JedisUtils.get(key);
		if(StringUtil.isNotBlank(value)){
			return Double.valueOf(value);
		}
		return BigDecimal.ZERO.doubleValue();
	}

	@Override
	public void setDouble(String key, double value, int cacheSec) {
		JedisUtils.set(key, String.valueOf(value), cacheSec);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void setMap(String key, Map<String, T> map, int cacheSec) {
		if(map == null || map.isEmpty()){
			return;
		}
		Iterator<String> it = map.keySet().iterator();
		Object obj = map.get(it.next());	
		if(obj instanceof String){
			JedisUtils.setMap(key, (Map<String, String>)map, cacheSec);
		}else{
			JedisUtils.setObjectMap(key, (Map<String, Object>)map, cacheSec);
		}		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> void addMap(String key, Map<String, T> map) {
		if(map == null || map.isEmpty()){
			return;
		}
		Iterator<String> it = map.keySet().iterator();
		Object obj = map.get(it.next());	
		if(obj instanceof String){
			JedisUtils.mapPut(key, (Map<String, String>)map);
		}else{
			JedisUtils.mapObjectPut(key, (Map<String, Object>)map);
		}	
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public <T> Map<String, T> getMap(String key, Class<T> clazz) {
		if(clazz.equals(String.class)){
			return (Map<String, T>) JedisUtils.getMap(key);
		}
		return (Map<String, T>) JedisUtils.getObjectMap(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getMapField(String key, String field, Class<T> clazz) {
		if(clazz.equals(String.class)){
			return (T) JedisUtils.getMapField(key, field);
        }		
		return (T) JedisUtils.getObjectMapField(key, field);
	}

	@Override
	public void delMapField(String key, String... field) {
		JedisUtils.mapRemove(key, field);
	}

 
	@Override
	public boolean setnx(String key, String value, ExpireTime cacheTime) {
		return JedisUtils.setnx(key, value, cacheTime.getTime()) > 0;
	}
	
	@Override
	public void expire(String key, int seconds) {
		JedisUtils.expire(key, seconds);
	}

	@Override
	public void sadd(String key, String... value) {
		JedisUtils.sadd(key, value);
	}

	@Override
	public void srem(String key, String... value) {
		JedisUtils.srem(key, value);
	}
	
	@Override
	public Set<String> getSet(String key) {
		return JedisUtils.getSet(key);
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key, Class<T> clazz) {

		if(clazz.equals(String.class)){
			return (T) JedisUtils.get(key);
		}else if(clazz.equals(Integer.class)){
			String value = JedisUtils.get(key);
			return (T) Integer.valueOf(StringUtil.isBlank(value)? CacheFactoryConstant.DEFAULT_NUMBER_VALUE:value);
		}else if(clazz.equals(Double.class)){
			String value = JedisUtils.get(key);
			return (T) Double.valueOf(StringUtil.isBlank(value)? CacheFactoryConstant.DEFAULT_NUMBER_VALUE:value);
		}else if(clazz.equals(Float.class)){
			String value = JedisUtils.get(key);
			return (T) Float.valueOf(StringUtil.isBlank(value)? CacheFactoryConstant.DEFAULT_NUMBER_VALUE:value);
		}else if(clazz.equals(Short.class)){
			String value = JedisUtils.get(key);
			return (T) Short.valueOf(StringUtil.isBlank(value)? CacheFactoryConstant.DEFAULT_NUMBER_VALUE:value);
		}else if(clazz.equals(Long.class)){
			String value = JedisUtils.get(key);
			return (T) Long.valueOf(StringUtil.isBlank(value)? CacheFactoryConstant.DEFAULT_NUMBER_VALUE:value);
		}else if(clazz.equals(Boolean.class)){
			String value = JedisUtils.get(key);
			return (T) Boolean.valueOf(StringUtil.isBlank(value)? CacheFactoryConstant.DEFAULT_NUMBER_VALUE:value);
		}
		return (T) JedisUtils.getObject(key);
	}

	@Override
	public List<String> list(Map<String, String> params) {
		List<String> list = new ArrayList<>();
		Jedis jedis = null;
		try{
			jedis = JedisUtils.getResource();
			SortingParams sortingParams = new SortingParams();
			//最终得到的对象
			sortingParams.get(params.get(CacheFactoryConstant.SORT_PARAMS_KEY_GET));
			//排序
			String by = params.get(CacheFactoryConstant.SORT_PARAMS_KEY_BY);
			String order = params.get(CacheFactoryConstant.SORT_PARAMS_KEY_ORDER);
			if(StringUtil.isNotBlank(by)){
				sortingParams.alpha().by(by);				
				if(StringUtil.isNotBlank(order) && CacheFactoryConstant.SORT_PARAMS_KEY_ORDER_ASC.equals(order)){
					sortingParams.asc();
				}else{
					sortingParams.desc();
				}
			}			
			//取得所有对象
			sortingParams.limit(0, -1);
			//取得分组
			list = jedis.sort(params.get(CacheFactoryConstant.SORT_PARAMS_KEY_GROUP).toString(), sortingParams);
		}finally{
			JedisUtils.close(jedis);
		}
		return list;
	}


	
	
	@Override
	public void rename(String oldkey, String newkey) {	
		JedisUtils.rename(oldkey, newkey);
	}
	
	@Override
	public void hmset(String key, Map<String, String> map, int cacheSec) {
		JedisUtils.hmset(key, map, cacheSec);
	}

	@Override
	public void hset(String key, String field, String value) {
		JedisUtils.hset(key, field, value);
	}
	
	@Override
	public boolean exists(String key) {
		return JedisUtils.exists(key);
	}
	
	
	/***************************************************私有方法***************************************************/

	/**
	 * 判断是否为数值类型
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	private <T> boolean checkNumber(Class<T> clazz, String fieldName) {
		boolean numberFlag = false;
		if (CacheFactoryConstant.DEFAULT_SORT_PARAM_NAME.equals(fieldName)
				|| CacheFactoryConstant.DEFAULT_SORT_REALIZE_PARAM_NAME.equals(fieldName)) {
			return true;
		}
		String typeName = Reflections.getFieldTypeName(clazz, fieldName);
		if(StringUtil.isBlank(typeName)) {
			return numberFlag;
		}
		String [] numberTypes = CacheFactoryConstant.getNumberTypes();
		for (String numberType : numberTypes) {
			if(numberType.equals(typeName)){
				numberFlag = true;
				break;
			}
		}
		return numberFlag;
	}
	/**
	 * 
	 * @param key
	 * @return   
	 * @author zjh
	 * @date 2017-9-26
	 */
	@Override
	public Long ttl(String key) {
		return JedisUtils.ttl(key);
	}

	@Override
	public void ladd(String key ,String... param){
		JedisUtils.ladd(key,param);
	}

	@Override
	public void ltrim(String key ,long start,long end){
		JedisUtils.ltrim(key,start,end);
	}

	@Override
	public List getladd(String key) {
		return JedisUtils.getladd(key);
	}

	@Override
	public void pulishMessage(String channel,String message) {
		JedisUtils.publish(channel,message);
	}

	@Override
	public Long zadd(String key, String value, double score, int cacheSeconds) {
		return JedisUtils.zadd(key,value,score,cacheSeconds);
	}
}

