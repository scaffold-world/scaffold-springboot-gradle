package com.cms.scaffold.sys.sys.service;

import com.cms.scaffold.sys.sys.ao.SysI18nAO;
import com.cms.scaffold.sys.sys.bo.SysI18nBO;
import com.cms.scaffold.common.base.BaseServiceInterface;
import com.cms.scaffold.common.base.ResponseListModel;

/**
 * @description:
 * @author: zjh
 * @date: 2019-03-12 19:05
 **/
public interface SysI18nService extends BaseServiceInterface<SysI18nAO, SysI18nBO>{

    /**
     * 分页获取国际化数据
     * @param sysI18nAO
     * @return
     */
    ResponseListModel findPageList(SysI18nAO sysI18nAO);

    /**
     * 保存数据
     * @param sysI18nAO
     */
    void save(SysI18nAO sysI18nAO);
}
