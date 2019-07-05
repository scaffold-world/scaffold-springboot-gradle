package com.cms.scaffold.sys.sys.service;

import com.cms.scaffold.sys.sys.domain.SysOperate;
import com.cms.scaffold.sys.sys.domain.SysRoleOperate;
import com.cms.scaffold.common.base.BaseService;
import com.cms.scaffold.common.base.ResponseListModel;

import java.util.List;
import java.util.Map;

public interface SysOperateService  extends BaseService<SysOperate> {

    SysOperate findByUserName(String username);

    /**
     * 查询所有用户
     */
    ResponseListModel<SysOperate> queryAllOperate(SysOperate sysOperate);


    /**
     * 保存用户
     */
    void saveOperate(SysOperate sysOperate);

    /**
     * 根据id查找
     */
    SysOperate findById(Long id);

    /**
     * 更新
     */
    void updateSysOperate(SysOperate sysOperate);


    /**
     * 更新角色跟role
     * @param sysOperate
     * @param sysRoleOperate
     */
    public void updateSysOperateAndSysRoleOperate(SysOperate sysOperate, SysRoleOperate sysRoleOperate);

    /**
     * 根据openid获取操作员记录
     * @param openid
     * @return
     */
    SysOperate findByOpenid(String openid);

    /**
     * 更新绑定关系
     * @param id
     * @param openid
     */
    void updateOpenidById(Long id, String openid);

    /**
     * 根据手机号获取操作员记录
     * @param mobilePhone
     * @return
     */
    SysOperate findByMobilePhone(String mobilePhone);

    /**
     * 根据真实姓名获取操作员记录
     * @param realName
     * @return
     */
    SysOperate findByRealName(String realName);

    /**
     * 根据id获取操作员记录
     * @param aduitPersonId
     * @return
     */
    SysOperate getById(Long aduitPersonId);

    /**
     * 根据条件查询操作员列表
     * @param sysOperate
     * @return
     */
    List<SysOperate> findList(SysOperate sysOperate);

    /**
     * 根据addOperatesID查询
     * @param addOperates
     * @return
     */
    Map<String,String> findByIds(List<Long> addOperates);
}
