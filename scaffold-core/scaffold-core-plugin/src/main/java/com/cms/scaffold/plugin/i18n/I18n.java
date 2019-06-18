package com.cms.scaffold.plugin.i18n;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description:
 * @author: zhangjiahengpoping@gmail.com
 * @date: 2019-03-12 11:25
 **/
@Setter
@Getter
public class I18n {

    private Long id;

    private String model;

    private String name;

    private String text;

    private String zhCH;

    private String enUS;

    private String idID;

    private Date addTime;

    private Date updateTime;
}
