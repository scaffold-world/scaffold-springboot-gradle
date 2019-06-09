package com.jiaheng.scaffold.sys.sys.service;

import com.jiaheng.scaffold.common.base.BaseServiceInterface;
import com.jiaheng.scaffold.common.base.ResponseListModel;
import com.jiaheng.scaffold.sys.sys.ao.SysI18nAO;
import com.jiaheng.scaffold.sys.sys.bo.SysI18nBO;

/**
 * @description:
 * @author: yangdeke@jianbing.com
 * @date: 2019-03-12 19:05
 **/
public interface SysI18nService extends BaseServiceInterface<SysI18nAO, SysI18nBO>{

    /**
     * 分页获取国际化数据
     * @param sysI18nAO
     * @return
     */
    ResponseListModel<SysI18nBO> findPageList(SysI18nAO sysI18nAO);

    /**
     * 保存数据
     * @param sysI18nAO
     */
    void save(SysI18nAO sysI18nAO);
}
