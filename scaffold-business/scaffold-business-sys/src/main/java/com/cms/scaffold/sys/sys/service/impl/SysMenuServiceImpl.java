package com.cms.scaffold.sys.sys.service.impl;

import com.cms.scaffold.common.base.Builder;
import com.cms.scaffold.common.exception.BaseResultCodeEnum;
import com.cms.scaffold.common.exception.BusinessException;
import com.cms.scaffold.common.util.StringUtil;
import com.cms.scaffold.core.baseService.BaseServiceImpl;
import com.cms.scaffold.core.util.BeanUtils;
import com.cms.scaffold.sys.sys.ao.SysMenuAO;
import com.cms.scaffold.sys.sys.bo.SysMenuBO;
import com.cms.scaffold.sys.sys.domain.SysMenu;
import com.cms.scaffold.sys.sys.service.SysMenuService;
import com.cms.scaffold.sys.sys.dao.SysMenuMapper;
import com.cms.scaffold.common.asserts.Assert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenuAO, SysMenuBO, SysMenu> implements SysMenuService {
    /**
     * 根据pid查询菜单记录
     * @param pid
     * @return
     */
    @Override
    public List<SysMenu> findListByPid(Long pid) {
        Assert.notNull(pid,"pid");

        SysMenu sysMenu = new SysMenu();
        sysMenu.setPid(pid);

        return dao.findList(sysMenu);
    }

    @Override
    public String findFatherIds(Long id) {
        StringBuilder ids = new StringBuilder();

        recursionFindPid(ids,id);
        String strIds =  ids.toString().replaceFirst(",","");

        return strIds;
    }

    private void recursionFindPid(StringBuilder ids,Long id){
        if(id == 0){
            return;
        }
        Long pid = dao.findPid(id);
        ids.append(",").append(pid);
        recursionFindPid(ids,pid);
    }

    @Override
    public List<SysMenuBO> findByPidAndId(Long pid, Long id) {
        return Builder.buildList(dao.findByPidAndId(pid,id),SysMenuBO.class);
    }

    @Override
    public List<SysMenuBO> findByPid(Long pid) {
        return Builder.buildList(dao.findByPid(pid),SysMenuBO.class);
    }

    @Override
    public List<SysMenuBO> findAllMenus(String name) {
        SysMenu menu = new SysMenu();
        menu.setName(name);
        return Builder.buildList(dao.findList(menu),SysMenuBO.class);
    }

    @Override
    public Integer deleteMenusById(Long id) {
        List<SysMenu> list = dao.findByPid(id);
        List<Long> listIds = list.stream().map(SysMenu::getId).collect(Collectors.toList());
        for (Long cId : listIds) {
            deleteMenusById(cId);
        }
        return dao.deleteById(id) + listIds.size();
    }


    @Override
    public void save(SysMenuAO sysMenuAO) {
        Assert.notNull(sysMenuAO);
        Assert.notNull(sysMenuAO.getPid(), "pid");
        SysMenu sysMenu = Builder.build(sysMenuAO, SysMenu.class);
        SysMenu parentSysMenu = dao.selectById(sysMenu.getPid());
        sysMenuAO.setLevelId(parentSysMenu.getLevelId() + 1);
        //判断设置地址链接
        if (StringUtil.isNotBlank(sysMenu.getUrl())) {
            System.out.println(sysMenuAO.getUrl().indexOf("/"));
            if (sysMenu.getUrl().indexOf("/") == 0) {
                sysMenu.setCode(sysMenu.getUrl().
                        replaceFirst("/", "").
                        replaceAll("/", ":"));
            } else {
                sysMenu.setCode(sysMenu.getUrl().
                        replaceAll("/", ":"));
            }
        }
        //保存
        if (sysMenu.getId() == null) {
            dao.insert(sysMenu);
        } else {
            SysMenu tempSysMenu = dao.selectById(sysMenu.getId());
            if (!tempSysMenu.getPid().equals( sysMenu.getPid())) {
                throw new BusinessException(BaseResultCodeEnum.FAIL);
            }

            BeanUtils.copyPropertiesByList(sysMenu, tempSysMenu, new String[]{
                    "name", "pid", "url", "iconCls", "status", "sort", "code", "state", "resourceType"});
            dao.update(tempSysMenu);
        }

    }

    @Override
    public List<SysMenu> findByOpId(Long operateId) {
        return dao.findByOpId(operateId);
    }

    @Override
    public List<SysMenu> findAll() {
        return dao.findAll();
    }
}
