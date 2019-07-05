package com.cms.scaffold.sys.sys.service.impl;

import com.cms.scaffold.sys.BaseServiceImpl;
import com.cms.scaffold.sys.sys.dao.SysRoleMapper;
import com.cms.scaffold.sys.sys.domain.SysRole;
import com.cms.scaffold.sys.sys.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cms.scaffold.common.base.ResponseListModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Override
    public List<SysRole> findList(SysRole sysRoleModel) {
       return dao.listAll(sysRoleModel);
    }

    @Override
    public void saveRole(SysRole sysRole) {
        dao.insert(sysRole);
    }

    @Override
    public SysRole findById(Long roleId) {
        return dao.queryByRoleId(roleId);
    }

    @Override
    public List<SysRole> queryAll() {
        return dao.queryAll();
    }

    @Override
    public ResponseListModel<SysRole> findAll(SysRole sysRole) {
        PageHelper.startPage(sysRole.getPage(), sysRole.getRows());
        List list = dao.listAll(sysRole);
        PageInfo<SysRole> pageInfo = new PageInfo<>(list);
        ResponseListModel<SysRole> sysRoleResponseListModel = new ResponseListModel<SysRole>(list, pageInfo.getTotal());

        return sysRoleResponseListModel;
    }
}
