package com.jiaheng.scaffold.route.app.entity.req.buryingpoint;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created with IDEA
 *
 * @author:JHX Date:2019/3/1
 * Time:10:29
 */
@Getter
@Setter
public class BuryingPointReq implements Serializable {
    /**
     * 渠道id
     */
    private Long channelId;
    /**
     * 中文名
     */
    private String name;
    /**
     * 事件类型
     */
    private String eventName;
    /**
     * 事件ID
     */
    private String eventType;
    /**
     * 事件的key/value
     */
    private String data;





}
