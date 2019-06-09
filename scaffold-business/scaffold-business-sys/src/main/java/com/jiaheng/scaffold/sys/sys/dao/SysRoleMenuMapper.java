package com.jiaheng.scaffold.sys.sys.dao;

import com.jiaheng.scaffold.sys.BaseMapper;
import com.jiaheng.scaffold.sys.sys.domain.SysRoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    @Select("select * from sys_role_menu where role_id =#{roleId}")
    List<SysRoleMenu> findByRoleId(Long ruleId);

    @Select("select * from sys_role_menu where role_id=#{roleId} and menu_id=#{menuId}")
    SysRoleMenu queryByRoleIdAndMenuId(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    int batchInsertT(List<SysRoleMenu> list);

    @Delete("DELETE from sys_role_menu where  menu_id in(${uuid}) and role_id=#{roleId};")
    void deleteMenu(@Param("uuid") String uuid, @Param("roleId") Long roleId);

    @Select("select * from sys_role_menu  where operate_id =#{operateId}")
    SysRoleMenu queryByOperateId(Long operateId);
}
