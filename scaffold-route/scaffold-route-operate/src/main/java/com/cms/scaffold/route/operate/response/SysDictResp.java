package com.cms.scaffold.route.operate.response;

import com.cms.scaffold.sys.sys.domain.SysDict;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SysDictResp {
    /**
     * 目录类型
     */
    public  final static int TYPE_DICT =1;

    /** 中文对应的国际化标识ID**/
    private String i18nNid;

    /**
     * treegrid  类型  closed，open
     */
    private  String state;

    private Long id;
    /** 名称**/
    private String name;

    /** 唯一标识**/
    private String nid;

    /** 父级id**/
    private Long pid;

    /** 值**/
    private String value;

    /** 代码 **/
    private String code;

    /** java类型 **/
    private String javaType;

    /** 类型，1目录 2值**/
    private Integer type;

    /** 排序**/
    private Integer sort;

    /** 状态，1可用 2不可用**/
    private Integer status;

    /** 备注**/
    private String remark;

    private List<SysDict> list;

    public String getState() {
        if(getType()==null){
            return "open";
        }

        if(TYPE_DICT==getType()){
            state = "closed" ;
        }else{
            state = "open";
        }
        return state;
    }
}
