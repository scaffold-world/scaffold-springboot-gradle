package com.cms.scaffold.sys.sys.domain;

import com.cms.scaffold.common.base.BaseEntity;

public class SysRoleOperate extends BaseEntity {
    private Long operateId;

    private Long roleId;

    /**
     * @return operate_id
     */
    public Long getOperateId() {
        return operateId;
    }

    /**
     * @param operateId
     */
    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    /**
     * @return role_id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
