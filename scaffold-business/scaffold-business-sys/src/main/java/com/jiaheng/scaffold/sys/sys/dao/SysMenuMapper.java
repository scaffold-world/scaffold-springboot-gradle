package com.jiaheng.scaffold.sys.sys.dao;

import com.jiaheng.scaffold.core.baseService.BaseMapper;
import com.jiaheng.scaffold.sys.sys.domain.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {

    @Select("SELECT pid FROM gjj_sys_menu WHERE id = #{id}")
    Long findPid(@Param("id") Long id);


    @Select("select * from gjj_sys_menu where id in(select menu_id  from gjj_sys_role_menu  where role_id = (select  role_id from  gjj_sys_role_operate  where operate_id =#{id})) and pid =#{pid} and status=1 order by sort ")
    List<SysMenu> findByPidAndId(@Param("pid") Long pid, @Param("id") Long id);

    @Select("select * from gjj_sys_menu where pid=#{pid}")
    List<SysMenu> findByPid(Long pid);

    @Select("select * from gjj_sys_menu where id in(select menu_id  from gjj_sys_role_menu  where role_id = (select  role_id from  gjj_sys_role_operate  where operate_id =#{id}))")
    List<SysMenu>  findByOpId(Long operateId);

    @Select("select * from gjj_sys_menu")
    List<SysMenu> findAll();
}
