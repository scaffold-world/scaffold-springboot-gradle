package com.cms.scaffold.sys.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.cms.scaffold.common.asserts.Assert;
import com.cms.scaffold.common.base.Builder;
import com.cms.scaffold.common.base.ResponseListModel;
import com.cms.scaffold.core.baseService.BaseServiceImpl;
import com.cms.scaffold.core.util.ResponseListModelUtils;
import com.cms.scaffold.sys.sys.ao.SysI18nAO;
import com.cms.scaffold.sys.sys.bo.SysI18nBO;
import com.cms.scaffold.sys.sys.dao.SysI18nMapper;
import com.cms.scaffold.sys.sys.domain.SysI18n;
import com.cms.scaffold.sys.sys.service.SysI18nService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: zhangjiahengpoping@gmail.com
 * @date: 2019-03-12 19:05
 **/
@Service
public class SysI18nServiceImpl extends BaseServiceImpl<SysI18nMapper,SysI18nAO,SysI18nBO,SysI18n> implements SysI18nService {


    @Override
    public ResponseListModel<SysI18nBO> findPageList(SysI18nAO sysI18nAO) {
        PageHelper.startPage(sysI18nAO.getPage(),sysI18nAO.getLimit());
        List<SysI18n> list = dao.findList(Builder.build(sysI18nAO,SysI18n.class));

        return ResponseListModelUtils.transform(list,SysI18nBO.class);
    }

    @Override
    public void save(SysI18nAO sysI18nAO) {
        Assert.notNull(sysI18nAO);
        SysI18n sysI18n = Builder.build(sysI18nAO,SysI18n.class);
        if(sysI18n.getId() == null){
            dao.insert(sysI18n);
        }else {
            dao.update(sysI18n);
        }

    }
}
