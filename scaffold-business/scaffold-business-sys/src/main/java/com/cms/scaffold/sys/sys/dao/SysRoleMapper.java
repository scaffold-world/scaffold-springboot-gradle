package com.cms.scaffold.sys.sys.dao;

import com.cms.scaffold.sys.BaseMapper;
import com.cms.scaffold.sys.sys.domain.SysRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> listAll(SysRole sysRoleModel);

    @Select("select * from sys_role where id = #{roleId}")
    SysRole queryByRoleId(Long roleId);

    @Select("select * from sys_role")
    List<SysRole> queryAll();
}
