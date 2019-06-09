package com.jiaheng.scaffold.sys.sys.dao;

import com.jiaheng.scaffold.core.baseService.BaseMapper;
import com.jiaheng.scaffold.sys.sys.domain.SysDict;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysDictMapper extends BaseMapper<SysDict> {

    @Select("SELECT pid FROM gjj_sys_dict WHERE id = #{id}")
    Long findPid(@Param("id") Long id);


    @Select("select * from gjj_sys_dict where id in(select menu_id  from gjj_sys_role_menu  where role_id = (select  role_id from  gjj_sys_role_operate  where operate_id =#{id})) and pid =#{pid};")
    List<SysDict> findSysDictByPidAndId(@Param("pid") Long pid, @Param("id") Long id);

    @Select("select * from gjj_sys_dict where pid=#{pid} order by sort asc")
    List<SysDict> listByPid(Long pid);

    List<SysDict> findByPartnerNid(@Param("nid") String nid);


    @Select("select * from gjj_sys_dict where nid = #{nid} and type=2 and status = 1 order by sort")
    List<SysDict> findByNid(@Param("nid") String nid);

    @Select("SELECT * FROM gjj_sys_dict where nid like 'order_property%'")
    List<SysDict> findOrderPropertyList();
}
