package com.cms.scaffold.sys.sys.service;

import com.cms.scaffold.sys.sys.domain.SysRole;
import com.cms.scaffold.common.base.BaseService;
import com.cms.scaffold.common.base.ResponseListModel;

import java.util.List;

public interface SysRoleService extends BaseService<SysRole> {
    ResponseListModel<SysRole> findAll(SysRole sysRole);

    void saveRole(SysRole sysRole);

    List<SysRole> queryAll();

    SysRole findById(Long id);

    List<SysRole> findList(SysRole sysRoleModel);
}
