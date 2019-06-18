package com.cms.scaffold.sys;

import com.cms.scaffold.common.asserts.Assert;
import com.cms.scaffold.common.base.BaseEntity;
import com.cms.scaffold.common.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;


public class BaseServiceImpl<D extends BaseMapper<T>, T extends BaseEntity> implements BaseService<T> {

    @Autowired
    protected D dao;

    protected Logger logger = LoggerFactory.getLogger(getClass().getName());

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    @Override
    //@Cacheable(key = CacheConstant.DATABASE_TABLE_POJO_ID,expire = ExpireTime.ONE_DAY)
    public T selectById(Serializable id) {
        return dao.selectById(id);
    }

    @Override
    public T selectCacheById(Serializable id) {
        return null;
    }

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    @Override
    public T selectLockById(Serializable id) {
        return dao.selectLockById(id);
    }

    /**
     * 查询单条记录
     *
     * @param record
     * @return
     */
    @Override
    public T selectOne(T record) {
        return dao.selectOne(record);
    }

    /**
     * 查询记录集合
     *
     * @param record
     * @return
     */
    @Override
    public List<T> findList(T record) {
        return dao.findList(record);
    }

    /**
     * 插入记录
     * @param record
     * @return
     */
    @Override
    public int insert(T record) {
        return dao.insert(record);
    }

    /**
     * 批量插入记录
     * @param list
     * @return
     */
    @Override
    public int batchInsert(List<?> list) {
        return dao.batchInsert(list);
    }

    /**
     * 更新不为空记录字段
     * @param record
     * @return
     */
    @Override
    //@CacheEvict(keys = CacheConstant.DATABASE_TABLE_POJO_RECORD_ID)
    public int update(T record) {
        Assert.notNull(record.getId(), "更新操作ID不能为空！");
        return dao.update(record);
    }

    /**
     * 批量更新
     * @param list
     * @return
     */
    @Override
    public int batchUpdate(List<?> list) {
        return dao.batchUpdate(list);
    }

    /**
     * 更新所有字段记录
     * @param record
     * @return
     */
    @Override
    public int updateAll(T record) {
        return dao.updateAll(record);
    }

    /**
     * 删除记录
     * @param list
     * @return
     */
    @Override
    public int delList(List<?> list) {
        return dao.delList(list);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public int delArray(Long[] ids) {
        return dao.delArray(ids);
    }

    /**
     * 查询个数
     * @param record
     * @return
     */
    @Override
    public int count(T record) {
        return dao.count(record);
    }
}
