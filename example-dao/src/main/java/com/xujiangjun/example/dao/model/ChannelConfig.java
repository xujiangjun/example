package com.xujiangjun.example.dao.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author xujiangjun
 * @since 2018.07.05
 */
@Data
@ToString(callSuper = true)
public class ChannelConfig extends BaseEntity {

    /** 渠道码(需设计成唯一) */
    private String channelCode;

    /** 渠道名称 */
    private String channelName;

    /** 是否开启 */
    private Integer isOpen;

    /** 渠道其余信息, 如：允许的服务器ip */
    private String serverIp;

}
