package com.cms.scaffold.sys.sys.service.impl;

import com.cms.scaffold.sys.BaseServiceImpl;
import com.cms.scaffold.sys.sys.dao.SysRoleOperateMapper;
import com.cms.scaffold.sys.sys.domain.SysOperate;
import com.cms.scaffold.sys.sys.domain.SysRoleOperate;
import com.cms.scaffold.sys.sys.service.SysOperateService;
import com.cms.scaffold.sys.sys.service.SysRoleOperateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleOperateServiceImpl extends BaseServiceImpl<SysRoleOperateMapper, SysRoleOperate> implements SysRoleOperateService {

    @Resource
    SysOperateService sysOperateService;
    @Override
    public SysRoleOperate selectByOperateId(Long operateId) {
        return dao.selectByOperateId(operateId);
    }

    @Override
    public void saveOperateAndRole(SysRoleOperate sysRoleOperate, SysOperate sysOperate) {
        sysOperateService.insert(sysOperate);

        sysRoleOperate.setOperateId(sysOperate.getId());

        dao.insert(sysRoleOperate);
    }

    @Override
    public List<SysRoleOperate> findListByRole(Long id) {
        SysRoleOperate sysRoleOperate = new SysRoleOperate();
        sysRoleOperate.setRoleId(id);
        return findList(sysRoleOperate);

    }

}
