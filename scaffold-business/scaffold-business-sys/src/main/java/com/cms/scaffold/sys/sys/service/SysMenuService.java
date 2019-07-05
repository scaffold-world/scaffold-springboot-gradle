package com.cms.scaffold.sys.sys.service;

import com.cms.scaffold.sys.sys.ao.SysMenuAO;
import com.cms.scaffold.sys.sys.bo.SysMenuBO;
import com.cms.scaffold.sys.sys.domain.SysMenu;
import com.cms.scaffold.common.base.BaseServiceInterface;

import java.util.List;

public interface SysMenuService extends BaseServiceInterface<SysMenuAO, SysMenuBO> {

    /**
     * 保存菜单记录
     * @param sysMenuAO
     */
    void save(SysMenuAO sysMenuAO);

    /**
     * 得到该角色可以访问的url
     */
    List<SysMenu> findByOpId(Long operateId);

    /**
     * 得到所以menu表记录
     */
    List<SysMenu> findAll();

    /**
     * 根据分配的角色拿到菜单
     */
    List<SysMenuBO> findByPidAndId(Long pid, Long partnerId);

    /**
     * 根据pid查询菜单记录
     * @param pid
     * @return
     */
    List<SysMenu> findListByPid(Long pid);

    /**
     * 根据id查询所有父类id
     * @param id
     * @return
     */
    String findFatherIds(Long id);

    /**
     *得到全部
     */
    List<SysMenuBO>  findByPid(Long pid);
}
