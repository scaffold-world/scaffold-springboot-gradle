package com.cms.scaffold.sys.sys.domain;

import com.cms.scaffold.common.base.BaseEntity;

public class SysMenu extends BaseEntity {
    /** 菜单名称**/
    private String name;

    /** 父级ID**/
    private Long pid;

    /** 等级**/
    private Integer levelId;

    /** 链接地址**/
    private String url;

    /** 图标**/
    private String iconCls;

    /** 状态**/
    private Integer status;

    /** 排序**/
    private Integer sort;

    /** 代码**/
    private String code;

    /** 是否可展开**/
    private String state;

    /** 资源类型**/
    private String resourceType;

    /** 添加用户**/
    private String addUser;

    /** 修改用户**/
    private String updateUser;

    /** 备注**/
    private String remark;


    /**
     * 获取菜单名称
     *
     * @return name - 菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置菜单名称
     *
     * @param name 菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取父级ID
     *
     * @return pid - 父级ID
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置父级ID
     *
     * @param pid 父级ID
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取等级
     *
     * @return level_id - 等级
     */
    public Integer getLevelId() {
        return levelId;
    }

    /**
     * 设置等级
     *
     * @param levelId 等级
     */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /**
     * 获取链接地址
     *
     * @return url - 链接地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置链接地址
     *
     * @param url 链接地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取图标
     *
     * @return icon_cls - 图标
     */
    public String getIconCls() {
        return iconCls;
    }

    /**
     * 设置图标
     *
     * @param iconCls 图标
     */
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取代码
     *
     * @return code - 代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置代码
     *
     * @param code 代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取是否可展开
     *
     * @return state - 是否可展开
     */
    public String getState() {
        return state;
    }

    /**
     * 设置是否可展开
     *
     * @param state 是否可展开
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取资源类型
     *
     * @return resource_type - 资源类型
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * 设置资源类型
     *
     * @param resourceType 资源类型
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * 获取添加用户
     *
     * @return add_user - 添加用户
     */
    public String getAddUser() {
        return addUser;
    }

    /**
     * 设置添加用户
     *
     * @param addUser 添加用户
     */
    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    /**
     * 获取修改用户
     *
     * @return update_user - 修改用户
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置修改用户
     *
     * @param updateUser 修改用户
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
