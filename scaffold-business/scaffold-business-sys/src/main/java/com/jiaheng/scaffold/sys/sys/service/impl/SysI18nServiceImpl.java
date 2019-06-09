package com.jiaheng.scaffold.sys.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.jiaheng.scaffold.common.asserts.Assert;
import com.jiaheng.scaffold.common.base.Builder;
import com.jiaheng.scaffold.common.base.ResponseListModel;
import com.jiaheng.scaffold.core.baseService.BaseServiceImpl;
import com.jiaheng.scaffold.core.util.ResponseListModelUtils;
import com.jiaheng.scaffold.sys.sys.ao.SysI18nAO;
import com.jiaheng.scaffold.sys.sys.bo.SysI18nBO;
import com.jiaheng.scaffold.sys.sys.dao.SysI18nMapper;
import com.jiaheng.scaffold.sys.sys.domain.SysI18n;
import com.jiaheng.scaffold.sys.sys.service.SysI18nService;
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
