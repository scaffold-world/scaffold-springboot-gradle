/**  
 * @Title: BaseService.java
 * @Package com.cms.scaffold.common.service
 * TODO:TODO
 * @author zjh
 * @date 2017-6-24
 */
package com.cms.scaffold.common.base;

import java.io.Serializable;
import java.util.List;


/**
 * TODO:Service支持类实现
 * @author zjh
 * @date 2017-6-24
 */
public interface BaseServiceInterface<AO extends BaseAO, BO extends BaseBO> {

	/**
	 * 通过ID查询
	 *
	 * @param id
	 * @return
	 */
	BO selectById(Serializable id);

	/**
	 * 通过ID查询（缓存）
	 * @param id
	 * @return
	 */
	BO selectCacheById(Serializable id);

	/**
	 * 通过ID查询
	 *
	 * @param id
	 * @return
	 */
	BO selectLockById(Serializable id);

	/**
	 * 查询单条记录
	 *
	 * @param record
	 * @return
	 */
	BO selectOne(AO record);

	/**
	 * 查询记录集合
	 *
	 * @param record
	 * @return
	 */
	List<BO> findList(AO record);

	int insert(AO record);

	/**
	 * 批量保存
	 *
	 * @param list
	 */
	int batchInsert(List<AO> list);

	/**
	 * 通用的修改方法
	 *
	 * @param record
	 */
	int update(AO record);

	/**
	 * 通用的修改方法
	 *
	 * @param record
	 */
	int update(AO record, AO oldRecord);

	/**
	 * 批量更新
	 *
	 * @param list
	 * @return
	 */
	int batchUpdate(List<AO> list);

	/**
	 * 通用的全部修改方法
	 *
	 * @param record
	 */
	int updateAll(AO record);


	/**
	 * 批量删除
	 *
	 * @param list
	 * @return
	 */
	int delList(List<AO> list);

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
	int count(AO record);

	int batchUpdate(List<AO> list,List<AO> listOld);


}
