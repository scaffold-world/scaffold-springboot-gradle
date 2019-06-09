/**  
 * @Title: BaseService.java
 * @Package com.jiaheng.scaffold.common.service
 * TODO:TODO
 * @author yangdk yangdk@erongdu.com
 * @date 2017-6-24
 */
package com.jiaheng.scaffold.common.base;

import java.io.Serializable;
import java.util.List;


/**
 * TODO:Service支持类实现
 * @author yangdk yangdk@erongdu.com
 * @date 2017-6-24
 */
public interface BaseService<T extends BaseEntity> {

	/**
	 * 通过ID查询
	 *
	 * @param id
	 * @return
	 */
	T selectById(Serializable id);

	/**
	 * 通过ID查询（缓存）
	 * @param id
	 * @return
	 */
	T selectCacheById(Serializable id);

	/**
	 * 通过ID查询
	 *
	 * @param id
	 * @return
	 */
	T selectLockById(Serializable id);

	/**
	 * 查询单条记录
	 *
	 * @param record
	 * @return
	 */
	T selectOne(T record);

	/**
	 * 查询记录集合
	 *
	 * @param record
	 * @return
	 */
	List<T> findList(T record);

	int insert(T record);

	/**
	 * 批量保存
	 *
	 * @param list
	 */
	int batchInsert(List<?> list);

	/**
	 * 通用的修改方法
	 *
	 * @param record
	 */
	int update(T record);

	/**
	 * 批量更新
	 *
	 * @param list
	 * @return
	 */
	int batchUpdate(List<?> list);

	/**
	 * 通用的全部修改方法
	 *
	 * @param record
	 */
	int updateAll(T record);


	/**
	 * 批量删除
	 *
	 * @param list
	 * @return
	 */
	int delList(List<?> list);

	/**
	 * 批量删除方法
	 *
	 * @param ids
	 */
	int delArray(Long[] ids);

	/**
	 * 统计查询
	 *
	 * @param record
	 *            查询参数
	 * @return 总记录条数
	 */
	int count(T record);

	
}
