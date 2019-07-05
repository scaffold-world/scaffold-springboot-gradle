package com.cms.scaffold.sys.sys.service;


import com.cms.scaffold.sys.sys.ao.SysDictAO;
import com.cms.scaffold.sys.sys.bo.SysDictBO;
import com.cms.scaffold.sys.sys.domain.SysDict;
import com.cms.scaffold.common.base.BaseServiceInterface;
import com.cms.scaffold.common.base.BaseService;

import java.util.List;

public interface SysDictService extends BaseServiceInterface<SysDictAO, SysDictBO> {

    /**
     * 根据上级pid查询字典记录
     * @param pid
     * @return
     */
    List<SysDictBO> findListByPid(Long pid);

    /**
     * 根据id查询所有父类id
     * @param id
     * @return
     */
    String findFatherIds(Long id);


    /**
     * 根据nid查找字典资源
     * @param nid
     * @return
     */
    List<SysDictBO> findByPartnerNid(String nid);

    /**
     * 加载字典信息到redis
     * @return
     */
    void loadDictIntoRedis();


    /**
     * 根据nid查找字典资源
     * @param nid
     * @return
     */
    List<SysDict> findByNid(String nid);
    /**
     * 根据nid和值查询字典记录
     * @param nid
     * @param value
     * @return
     */
    SysDict selectByNidAndValue(String nid, String value);

    /**
     * 查询资产字典
     * @return
     */
    List<SysDict> findOrderPropertyList();

    void save(SysDictAO dict);
}
