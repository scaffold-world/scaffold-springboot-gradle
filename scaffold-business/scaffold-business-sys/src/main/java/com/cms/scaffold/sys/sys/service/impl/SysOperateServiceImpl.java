package com.cms.scaffold.sys.sys.service.impl;


import com.cms.scaffold.sys.BaseServiceImpl;
import com.cms.scaffold.sys.sys.dao.SysOperateMapper;
import com.cms.scaffold.sys.sys.domain.SysOperate;
import com.cms.scaffold.sys.sys.domain.SysRoleOperate;
import com.cms.scaffold.sys.sys.service.SysOperateService;
import com.cms.scaffold.sys.sys.service.SysRoleOperateService;
import com.cms.scaffold.sys.sys.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cms.scaffold.common.asserts.Assert;
import com.cms.scaffold.common.base.Builder;
import com.cms.scaffold.common.base.ResponseListModel;
import com.cms.scaffold.common.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysOperateServiceImpl extends BaseServiceImpl<SysOperateMapper, SysOperate> implements SysOperateService {

    @Resource
    SysRoleService sysRoleService;

    @Resource
    SysRoleOperateService sysRoleOperateService;
    @Override
    public SysOperate findByUserName(String username) {
        return dao.findByUserName(username);
    }

    @Override
    public ResponseListModel<SysOperate> queryAllOperate(SysOperate sysOperateModel) {
        PageHelper.startPage(sysOperateModel.getPage(), sysOperateModel.getRows());
        List<SysOperate> list = dao.queryAllOperate(sysOperateModel);
        for (SysOperate sysOperate : list) {
            SysRoleOperate sysRoleOperate = sysRoleOperateService.selectByOperateId(sysOperate.getId());
            sysOperate.setRoleName(sysRoleService.findById(sysRoleOperate.getRoleId()).getName());
        }
        PageInfo<SysOperate> pageInfo = new PageInfo<>(list);
        ResponseListModel<SysOperate> sysRoleResponseListModel = new ResponseListModel<SysOperate>(list, pageInfo.getTotal());
        return sysRoleResponseListModel;
    }

    @Override
    public void saveOperate(SysOperate sysOperate) {
        dao.insert(Builder.build(sysOperate, SysOperate.class));
    }

    @Override
    public SysOperate findById(Long id) {
        return Builder.build(dao.selectById(id), SysOperate.class);
    }

    @Override
    public void updateSysOperate(SysOperate sysOperate) {
        dao.update(Builder.build(sysOperate, SysOperate.class));
    }

    @Override
    public void updateSysOperateAndSysRoleOperate(SysOperate sysOperate, SysRoleOperate sysRoleOperate) {
        dao.update(Builder.build(sysOperate, SysOperate.class));
        sysRoleOperateService.update(Builder.build(sysRoleOperate, SysRoleOperate.class));
    }

    @Override
    public SysOperate findByOpenid(String openid) {
        if(StringUtil.isBlank(openid)){
            return null;
        }
        SysOperate sysOperate = new SysOperate();
        sysOperate.setOpenid(openid);

        return dao.selectOne(sysOperate);
    }

    @Override
    public void updateOpenidById(Long id, String openid) {
        Assert.notNull(id,"id");
        Assert.notNull(openid,"openid");

        dao.updateOpenidById(id,openid);
    }

    @Override
    public SysOperate findByMobilePhone(String mobilePhone) {
        Assert.notNull(mobilePhone,"mobilePhone");
        SysOperate sysOperate = new SysOperate();
        sysOperate.setMobilePhone(mobilePhone);

        return dao.selectOne(sysOperate);
    }

    @Override
    public SysOperate findByRealName(String realName) {
        Assert.notNull(realName,"realName");
        SysOperate sysOperate = new SysOperate();
        sysOperate.setRealName(realName);

        return dao.selectOne(sysOperate);
    }

    @Override
    public SysOperate getById(Long aduitPersonId) {
        return dao.findById(aduitPersonId);
    }

    @Override
    public List<SysOperate> findList(SysOperate sysOperate) {
        return Builder.buildList(dao.findList(Builder.build(sysOperate, SysOperate.class)), SysOperate.class);
    }

    @Override
    public Map<String, String> findByIds(List<Long> addOperates) {
        List<SysOperate> list =dao.findByIds(addOperates);
        Map<String, String> map = new HashMap<>();
        if (list == null || list.isEmpty()) {
            return map;
        } else {
            for (SysOperate sysOperate : list) {
                if (sysOperate.getId()!=null){
                    map.put(sysOperate.getId().toString(),
                            sysOperate.getUserName());
                }
            }
            return map;
        }
    }

}
