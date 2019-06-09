package com.jiaheng.scaffold.sys.sys.service;

import com.jiaheng.scaffold.sys.sys.domain.SysRole;
import com.jiaheng.scaffold.common.base.BaseService;
import com.jiaheng.scaffold.common.base.ResponseListModel;

import java.util.List;

public interface SysRoleService extends BaseService<SysRole> {
    ResponseListModel<SysRole> findAll(SysRole sysRole);

    void saveRole(SysRole sysRole);

    List<SysRole> queryAll();

    SysRole findById(Long id);

    List<SysRole> findList(SysRole sysRoleModel);
}
