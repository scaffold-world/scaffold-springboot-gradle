package com.cms.scaffold.sys.sys.service;

import com.cms.scaffold.sys.sys.ao.SysConfigAO;
import com.cms.scaffold.sys.sys.bo.SysConfigBO;
import com.cms.scaffold.common.base.BaseServiceInterface;

/**
 * Created with IDEA
 *
 * @author:JHX Date:2019/3/1
 * Time:14:38
 */
public interface SysConfigService extends BaseServiceInterface<SysConfigAO, SysConfigBO> {

    void loadConfigIntoRedis();
}
