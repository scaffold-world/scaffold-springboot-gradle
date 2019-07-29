package com.cms.scaffold.sys.sys.dao;


import com.cms.scaffold.sys.sys.domain.SysConfig;
import com.cms.scaffold.code.baseService.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysConfigMapper extends BaseMapper<SysConfig> {
    /**
     * 查询开启状态下的配置
     * @return
     */
    @Select("select * from sys_config where status =1")
    List<SysConfig> findListByStatus();
}