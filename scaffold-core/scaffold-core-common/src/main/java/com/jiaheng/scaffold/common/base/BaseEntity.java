package com.jiaheng.scaffold.common.base;


import com.alibaba.fastjson.JSONObject;
import com.jiaheng.scaffold.common.annotation.CustomAdvanceFilter;
import com.jiaheng.scaffold.common.annotation.TableName;
import com.jiaheng.scaffold.common.constant_manual.AdvanceFilterOpConstantManual;
import com.jiaheng.scaffold.common.util.CamelCaseUtils;
import com.jiaheng.scaffold.common.util.DateUtil;
import com.jiaheng.scaffold.common.util.Reflections;
import com.jiaheng.scaffold.common.util.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 支持类
 *
 * @author zhangjiahengpoping@gmail.com
 * @date 2017-6-22
 */
public class BaseEntity extends ToString {

    /**
     * id
     */
    protected Long id;

    /**
     * 插入时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private Integer index;

    /**
     * 页数
     */
    private Integer page = 1;

    /**
     * 行数
     */
    private Integer limit = 20;

    /**
     * 商户id
     */
    private Long partnerId;

    /**
     * 查询条件
     */
    private String advanceFilter;

    /**
     * 查询条件数组
     */
    private List<AdvanceFilter> advanceFilterList = new ArrayList<>();

    /**
     * 查询条件字符串
     */
    private String advanceFilterStr;

    /**
     * 判断是否是商户后台的还是运营后台的
     */
    private boolean operatePlatform;

    /**
     * 当前的操作人/登录人
     */
    private Long sysOperateId;

    /** 创建人员**/
    private Long addOperate;

    /** 更新人员**/
    private Long updateOperate;
    /**
     * 物理表名
     */
    private String tableName;
    /**
     * 排序字段
     */
    private String sortStr;
    /**
     * 排序规则
     */
    private String orderStr;

    private List<OrderFilter> orders = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIndex() {
        return index;
    }

    public void preUpdate() {
        this.updateTime = DateUtil.getNow();
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void preInsert() {
        this.addTime = DateUtil.getNow();
        this.updateTime = DateUtil.getNow();
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public String getAdvanceFilter() {
        return advanceFilter;
    }

    public void setAdvanceFilter(String advanceFilter) {
        this.advanceFilter = advanceFilter;
        if (StringUtil.isNotBlank(advanceFilter)) {
            setAdvanceFilterList(JSONObject.parseArray(getAdvanceFilter(), AdvanceFilter.class));
        }
    }

    public List<AdvanceFilter> getAdvanceFilterList() {
        if (this.advanceFilterList != null && !this.advanceFilterList.isEmpty()) {
            return advanceFilterList;
        }

        if (StringUtil.isNotBlank(getAdvanceFilter())) {
            return JSONObject.parseArray(getAdvanceFilter(), AdvanceFilter.class);
        }

        return null;
    }

    public void setAdvanceFilterList(List<AdvanceFilter> advanceFilterList) {
        this.advanceFilterList = advanceFilterList;
    }

    public void addAdvanceFilter(String name,String op,String value){
        advanceFilterList.add(new AdvanceFilter(name,op,value));
    }

    public void addAdvanceFilter(String name,String value){
        advanceFilterList.add(new AdvanceFilter(name,AdvanceFilterOpConstantManual.ADVANCE_FILTER_OP_EQUAL,value));
    }

    public void addAdvanceFilter(AdvanceFilter advanceFilter){
        advanceFilterList.add(advanceFilter);
    }
    /**
     * 拼接查询条件字符串
     *
     * @return
     */
    public String getAdvanceFilterStr() {
        if (this.advanceFilterList != null && !this.advanceFilterList.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            boolean isFirst = true;
            for (AdvanceFilter advanceFilter : advanceFilterList) {
                if (StringUtil.isBlank(advanceFilter.getField())) {
                    continue;
                }


                stringBuilder.append("\t" + advanceFilter.getJoin() + "\t");
                stringBuilder.append(advanceFilter.getLb() + "\t");
                stringBuilder.append(CamelCaseUtils.toUnderlineName(advanceFilter.getField()) + "\t");

                switch (advanceFilter.getOp()) {
                    case AdvanceFilterOpConstantManual.ADVANCE_FILTER_OP_CONTAINS:
                        stringBuilder.append("like " + "'%" + advanceFilter.getValue() + "%'\t");
                        break;
                    case AdvanceFilterOpConstantManual.ADVANCE_FILTER_OP_EQUAL:
                        stringBuilder.append("= '" + advanceFilter.getValue() + "'\t");
                        break;
                    case AdvanceFilterOpConstantManual.ADVANCE_FILTER_OP_NOTEQUAL:
                        stringBuilder.append("<> '" + advanceFilter.getValue() + "'\t");
                        break;
                    case AdvanceFilterOpConstantManual.ADVANCE_FILTER_OP_GREATER:
                        stringBuilder.append("> '" + advanceFilter.getValue() + "'\t");
                        break;
                    case AdvanceFilterOpConstantManual.ADVANCE_FILTER_OP_GREATEROREQUAL:
                        stringBuilder.append(">= '" + advanceFilter.getValue() + "'\t");
                        break;
                    case AdvanceFilterOpConstantManual.ADVANCE_FILTER_OP_LESS:
                        stringBuilder.append("< '" + advanceFilter.getValue() + "'\t");
                        break;
                    case AdvanceFilterOpConstantManual.ADVANCE_FILTER_OP_LESSOREQUAL:
                        stringBuilder.append("<= '" + advanceFilter.getValue() + "'\t");
                        break;
                    case AdvanceFilterOpConstantManual.ADVANCE_FILTER_OP_BEGINWITH:
                        stringBuilder.append("like '" + "'%" + advanceFilter.getValue() + "'\t");
                        break;
                    case AdvanceFilterOpConstantManual.ADVANCE_FILTER_OP_ENDWITH:
                        stringBuilder.append("like '" + advanceFilter.getValue() + "%'\t");
                        break;
                    case AdvanceFilterOpConstantManual.ADVANCE_FILTER_OP_IN:
                        stringBuilder.append("in (" + advanceFilter.getValue() + ")\t");
                        break;
                    default:
                        break;
                }
                stringBuilder.append(advanceFilter.getRb() + "\n");

            }

            return stringBuilder.toString();
        }

        return advanceFilterStr;
    }

    public void setAdvanceFilterStr(String advanceFilterStr) {
        this.advanceFilterStr = advanceFilterStr;
    }

    public List<OrderFilter> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderFilter> orders) {
        this.orders = orders;
    }

    /**
     * 获取排序sql
     *
     * @return
     */
    public String getOrdersSql() {
        StringBuffer ordersSql = new StringBuffer();
        if (getOrders() != null && !getOrders().isEmpty()) {
            ordersSql.append("order by ");
            getOrders().stream().forEach(orderFilter -> {
                ordersSql.append(","+orderFilter.getName() + " " + orderFilter.getOrder().name());
            });

            return ordersSql.toString().replaceFirst(",","");
        }

        return null;
    }

    /**
     * 增加排序
     * @param name
     */
    public void addOrder(String name){
        orders.add(new OrderFilter(name));
    }

    public boolean isOperatePlatform() {
        return operatePlatform;
    }

    public void setOperatePlatform(boolean operatePlatform) {
        this.operatePlatform = operatePlatform;
    }

    public Long getSysOperateId() {
        return sysOperateId;
    }

    public void setSysOperateId(Long sysOperateId) {
        this.sysOperateId = sysOperateId;
    }

    /**
     * 构造自定义sql
     */
    public BaseEntity buildFilter(){
        /**
         * 返回类中所有字段
         */
        Field[] fields  =  this.getClass().getDeclaredFields();
        this.setAdvanceFilterList(new ArrayList<>());
        for(Field field : fields){
            CustomAdvanceFilter customAdvanceFilter = field.getAnnotation(CustomAdvanceFilter.class);
            if(customAdvanceFilter != null){
                Object obj =  Reflections.invokeGetter(this, field.getName());
                if(obj != null && StringUtil.isNotBlank(obj+"")){
                    AdvanceFilter advanceFilter  = new AdvanceFilter(customAdvanceFilter.join(),
                            customAdvanceFilter.lb(),customAdvanceFilter.field(),customAdvanceFilter.op(),
                            String.valueOf(obj),customAdvanceFilter.rb());

                    this.addAdvanceFilter(advanceFilter);
                }
            }
        }

        return  this;
    }

    public Long getAddOperate() {
        return addOperate;
    }

    public void setAddOperate(Long addOperate) {
        this.addOperate = addOperate;
    }

    public Long getUpdateOperate() {
        return updateOperate;
    }

    public void setUpdateOperate(Long updateOperate) {
        this.updateOperate = updateOperate;
    }

    public String getTableName() {
        if(StringUtil.isNotBlank(tableName)){
            return tableName;
        }
        // 设置id
        TableName tableNameClass=this.getClass().getAnnotation(TableName.class);
        if(tableNameClass != null){
            tableName  = tableNameClass.name();
        }else{
            tableName = ClassNameTransTableName();
        }


        return tableName;
    }

    /**
     * 获取泛型类型,根据泛型类型对象获取所对应的表名
     */
    private String ClassNameTransTableName(){
        Type genType = getClass().getGenericSuperclass();
        //只做2层判断是否为泛型类型
        if (!(genType instanceof ParameterizedType)) {
            genType = getClass().getSuperclass().getGenericSuperclass();
            if (!(genType instanceof ParameterizedType)) {
                return Object.class.getName();
            }
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Class clazz = (Class) params[0];

        return StringUtil.toUnderline(clazz.getName()
                .substring((clazz.getName().lastIndexOf(".")+1)));
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSortStr() {
        return sortStr;
    }

    public void setSortStr(String sortStr) {
        this.sortStr = sortStr;
    }

    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    public String getOrderByStr(){
        StringBuilder builder = new StringBuilder();

        if(StringUtil.isBlank(getSortStr()) || StringUtil.isBlank(getOrderStr())){
            return null;
        }

        String[] sortStrs = getSortStr().split(",");
        String[] orderStrs = getOrderStr().split(",");

        if(sortStrs.length != orderStrs.length){
            return null;
        }

        for (int i = 0;i<sortStrs.length;i++){
            builder.append(StringUtil.toUnderline(sortStrs[i]));
            builder.append(" ");
            builder.append(orderStrs[i]);
            if(i != sortStrs.length -1){
                builder.append(",");
            }
        }

        return builder.toString();
    }
}
