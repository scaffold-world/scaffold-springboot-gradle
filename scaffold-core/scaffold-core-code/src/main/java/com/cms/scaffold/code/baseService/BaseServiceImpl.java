package com.cms.scaffold.code.baseService;

import com.cms.scaffold.common.base.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public class BaseServiceImpl<D extends BaseMapper<T>, AO extends BaseAO,BO extends BaseBO,T extends BaseEntity> implements BaseServiceInterface<AO,BO> {

    @Autowired
    protected D dao;


    protected Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Override
    public BO selectById(Serializable id) {
        //获取超类下的Type对象数组
        Class<BO> boClass = (Class <BO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
        T t = dao.selectById(id);


        return Builder.build(t,boClass);
    }

    @Override
    public BO selectCacheById(Serializable id) {
        Class<BO> boClass = (Class <BO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
        T t = dao.selectById(id);

        return Builder.build(t, boClass);
    }

    @Override
    public BO selectLockById(Serializable id) {
        Class<BO> boClass = (Class <BO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
        T t = dao.selectLockById(id);

        return Builder.build(t, boClass);

    }

    @Override
    public BO selectOne(AO record) {
        Class<BO> boClass = (Class <BO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
        Class<T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3];
        T t = Builder.build(record,entityClass);

        return Builder.build(dao.selectOne(t),boClass);
    }

    @Override
    public List<BO> findList(AO record) {
        Class<BO> boClass = (Class <BO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
        Class<T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3];

        T t = Builder.build(record,entityClass);
        List<T> list = dao.findList(t);

        return Builder.buildList(list,boClass);
    }

    @Override
    public int insert(AO record) {
        Class<T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3];
        T t = Builder.build(record,entityClass);
        t.setTableName(null);
        Integer i =dao.insert(t);
        record.setId(t.getId());
        return i;
    }

    @Override
    public int batchInsert(List<AO> list) {
        Class<T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3];
        List<T> tList = Builder.buildList(list,entityClass);

        return dao.batchInsert(tList);
    }

    @Override
    public int update(AO record) {
        Class<T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3];
        T t = Builder.build(record,entityClass);

        return dao.update(t);
    }

    @Override
    public int batchUpdate(List<AO> list) {
        Class<T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3];
        List<T> tList = Builder.buildList(list,entityClass);

        return dao.batchUpdate(tList);
    }

    @Override
    public int updateAll(AO record) {
        Class<T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3];
        T t = Builder.build(record,entityClass);

        return dao.updateAll(t);
    }

    @Override
    public int delList(List<AO> list) {
        Class<T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3];
        List<T> tList = Builder.buildList(list,entityClass);

        return dao.delList(tList);
    }

    @Override
    public int delArray(Long[] ids) {
        return dao.delArray(ids);
    }

    @Override
    public int count(AO record) {
        Class<T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3];
        T t = Builder.build(record,entityClass);

        return dao.count(t);
    }

    @Override
    public int update(AO record, AO oldRecord) {
        Class<T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3];
        T t = Builder.build(record,entityClass);
        T oldT = Builder.build(oldRecord,entityClass);
        t.setTableName(null);
        return dao.update(t,oldT);
    }

    @Override
    public int batchUpdate(List<AO> list,List<AO> listOld) {
        Class<T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3];
        List<T> tList = Builder.buildList(list,entityClass);
        for (T t : tList){
            t.setTableName(null);
        }
        List<T> tOldList = Builder.buildList(listOld,entityClass);
        return dao.batchUpdate(tList, tOldList);
    }
}
