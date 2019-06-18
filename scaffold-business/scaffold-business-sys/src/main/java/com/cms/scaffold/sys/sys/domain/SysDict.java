package com.cms.scaffold.sys.sys.domain;

import com.cms.scaffold.common.base.BaseEntity;

public class SysDict extends BaseEntity {
    /** 名称**/
    private String name;

    /** 唯一标识**/
    private String nid;

    /** 父级id**/
    private Long pid;

    /** 值**/
    private String value;

    /** 类型  (详情见dict表basics_dict_type) **/
    private Integer type;

    /** 代码**/
    private String code;

    /** java类型 (详情见dict表basics_java_type)**/
    private String javaType;

    /** 排序**/
    private Integer sort;

    /** 状态，(详情见dict表basics_use_status) **/
    private Integer status;

    /** 备注**/
    private String remark;

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取唯一标识
     *
     * @return nid - 唯一标识
     */
    public String getNid() {
        return nid;
    }

    /**
     * 设置唯一标识
     *
     * @param nid 唯一标识
     */
    public void setNid(String nid) {
        this.nid = nid;
    }

    /**
     * 获取父级id
     *
     * @return pid - 父级id
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置父级id
     *
     * @param pid 父级id
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取值
     *
     * @return value - 值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置值
     *
     * @param value 值
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取类型  (详情见dict表basics_dict_type)
     *
     * @return type - 类型  (详情见dict表basics_dict_type)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型  (详情见dict表basics_dict_type)
     *
     * @param type 类型  (详情见dict表basics_dict_type)
     */
    public void setType(Integer type) {
        this.type = type;
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
     * 获取java类型 (详情见dict表basics_java_type)
     *
     * @return java_type - java类型 (详情见dict表basics_java_type)
     */
    public String getJavaType() {
        return javaType;
    }

    /**
     * 设置java类型 (详情见dict表basics_java_type)
     *
     * @param javaType java类型 (详情见dict表basics_java_type)
     */
    public void setJavaType(String javaType) {
        this.javaType = javaType;
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
     * 获取状态，(详情见dict表basics_use_status)
     *
     * @return status - 状态，(详情见dict表basics_use_status)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态，(详情见dict表basics_use_status)
     *
     * @param status 状态，(详情见dict表basics_use_status)
     */
    public void setStatus(Integer status) {
        this.status = status;
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
