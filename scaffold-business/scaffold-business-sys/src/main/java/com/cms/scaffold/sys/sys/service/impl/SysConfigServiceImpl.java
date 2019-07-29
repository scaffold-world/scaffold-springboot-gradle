package com.cms.scaffold.sys.sys.service.impl;

import com.cms.scaffold.sys.sys.ao.SysConfigAO;
import com.cms.scaffold.sys.sys.bo.SysConfigBO;
import com.cms.scaffold.sys.sys.dao.SysConfigMapper;
import com.cms.scaffold.sys.sys.domain.SysConfig;
import com.cms.scaffold.sys.sys.service.SysConfigService;
import com.cms.scaffold.common.constant_manual.RedisConstant;
import com.cms.scaffold.code.baseService.BaseServiceImpl;
import com.cms.scaffold.code.dict.ExpireTime;
import com.cms.scaffold.code.util.CacheUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IDEA
 *
 *  Date:2019/3/1
 * Time:14:39
 */
@Service
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfigMapper, SysConfigAO, SysConfigBO, SysConfig> implements SysConfigService {
    @Override
    public void loadConfigIntoRedis() {
        //查询状态为1 的配置信息
        List<SysConfig> list = dao.findListByStatus();
        for (SysConfig config : list) {
            CacheUtils.set(RedisConstant.KEY_PREFIX_CONFIG_CODE + config.getNid(), config.getValue(), ExpireTime.NONE);
        }
    }
}
