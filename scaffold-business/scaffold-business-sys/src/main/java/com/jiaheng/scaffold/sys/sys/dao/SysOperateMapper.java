package com.jiaheng.scaffold.sys.sys.dao;

import com.jiaheng.scaffold.sys.BaseMapper;
import com.jiaheng.scaffold.sys.sys.domain.SysOperate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface SysOperateMapper  extends BaseMapper<SysOperate> {

    SysOperate findByUserName(@Param("userName") String userName);

    List<SysOperate> queryAllOperate(SysOperate sysOperateModel);

    void saveOperate(SysOperate sysOperate);

    @Select("select * from gjj_sys_operate where id=#{id}")
    SysOperate findById(Long id);

    void updateSysOperate(SysOperate sysOperate);

    /**
     * 更新openid
     * @param id
     * @param openid
     */
    @Update("update gjj_sys_operate set openid = #{openid} where id = #{id}")
    void updateOpenidById(@Param("id") Long id, @Param("openid") String openid);

    List<SysOperate> findByIds(@Param("ids")List<Long> ids);
}
