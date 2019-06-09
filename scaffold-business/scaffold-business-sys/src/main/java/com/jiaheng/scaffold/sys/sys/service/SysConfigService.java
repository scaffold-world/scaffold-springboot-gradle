package com.jiaheng.scaffold.sys.sys.service;

import com.jiaheng.scaffold.sys.sys.ao.SysConfigAO;
import com.jiaheng.scaffold.sys.sys.bo.SysConfigBO;
import com.jiaheng.scaffold.common.base.BaseServiceInterface;

/**
 * Created with IDEA
 *
 * @author:JHX Date:2019/3/1
 * Time:14:38
 */
public interface SysConfigService extends BaseServiceInterface<SysConfigAO, SysConfigBO> {

    void loadConfigIntoRedis();
}
