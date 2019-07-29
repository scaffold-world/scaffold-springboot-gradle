package com.cms.scaffold.sys.sys.dao;

import com.cms.scaffold.code.baseService.BaseMapper;
import com.cms.scaffold.sys.sys.domain.SysI18n;
import com.cms.scaffold.sys.sys.dto.SysI18nDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysI18nMapper extends BaseMapper<SysI18n> {
    /**
     * 查询所有
     */
    @Select("select id,model,name,text,IFNULL(en_US, '') enUs,IFNULL(zh_CN, '') zhCn,IFNULL(in_ID, '') inId from sys_i18n")
    List<SysI18nDTO> findAll();
}