package com.cms.scaffold.sys.sys.service.impl;

import com.cms.scaffold.sys.BaseServiceImpl;
import com.cms.scaffold.sys.sys.bo.SysRoleMenuBO;
import com.cms.scaffold.sys.sys.dao.SysRoleMenuMapper;
import com.cms.scaffold.sys.sys.domain.SysRoleMenu;
import com.cms.scaffold.sys.sys.service.SysRoleMenuService;
import com.cms.scaffold.common.base.Builder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangjiaheng@gmail.com
 * @Description
 **/
@Service
public class SysRoleMenuServiceImpl  extends BaseServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Override
    public List<SysRoleMenu> findByRoleId(Long roleId) {
        return dao.findByRoleId(roleId);
    }

    @Override
    public SysRoleMenuBO selectByRoleIdAndMenuId(Long ruleId, Long menuId) {
        return Builder.build(dao.queryByRoleIdAndMenuId(ruleId, menuId),SysRoleMenuBO.class);
    }

    @Override
    public int saveRoleMenu(List<SysRoleMenu> list) {
        return dao.batchInsertT(list);
    }

    @Override
    public void deleteMenu(String uuid, Long roleId) {
        dao.deleteMenu(uuid,roleId);
    }
}
