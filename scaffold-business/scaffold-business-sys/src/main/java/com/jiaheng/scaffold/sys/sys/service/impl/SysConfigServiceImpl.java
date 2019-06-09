package com.jiaheng.scaffold.sys.sys.service.impl;

import com.jiaheng.scaffold.sys.sys.ao.SysConfigAO;
import com.jiaheng.scaffold.sys.sys.bo.SysConfigBO;
import com.jiaheng.scaffold.sys.sys.dao.SysConfigMapper;
import com.jiaheng.scaffold.sys.sys.domain.SysConfig;
import com.jiaheng.scaffold.sys.sys.service.SysConfigService;
import com.jiaheng.scaffold.common.constant_manual.RedisConstant;
import com.jiaheng.scaffold.core.baseService.BaseServiceImpl;
import com.jiaheng.scaffold.core.dict.ExpireTime;
import com.jiaheng.scaffold.core.util.CacheUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IDEA
 *
 * @author:JHX Date:2019/3/1
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
