package com.jiaheng.scaffold.sys.sys.service.impl;

import com.jiaheng.scaffold.common.base.Builder;
import com.jiaheng.scaffold.sys.BaseServiceImpl;
import com.jiaheng.scaffold.sys.sys.bo.SysRoleMenuBO;
import com.jiaheng.scaffold.sys.sys.domain.SysRoleMenu;
import com.jiaheng.scaffold.sys.sys.service.SysRoleMenuService;
import com.jiaheng.scaffold.sys.sys.dao.SysRoleMenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangjiaheng@jianbing.com
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
