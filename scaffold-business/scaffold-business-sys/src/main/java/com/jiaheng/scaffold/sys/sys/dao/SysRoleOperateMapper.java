package com.jiaheng.scaffold.sys.sys.dao;

import com.jiaheng.scaffold.sys.BaseMapper;
import com.jiaheng.scaffold.sys.sys.domain.SysRoleOperate;
import org.apache.ibatis.annotations.Select;

public interface SysRoleOperateMapper extends BaseMapper<SysRoleOperate> {
    @Select("select * from sys_role_operate where operate_id =#{operateId}")
    SysRoleOperate selectByOperateId(Long operateId);
}
