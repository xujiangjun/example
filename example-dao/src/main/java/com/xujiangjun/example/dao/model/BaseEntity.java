package com.xujiangjun.example.dao.model;

import lombok.Data;

import java.util.Date;

/**
 * 数据库6个基础字段
 *
 * @author xujiangjun
 * @date 2017-07-26 11:02
 */
@Data
public class BaseEntity {
    /** 自增主键 **/
    private Integer id;

    /** 是否删除：0-未删除; 1-已删除 **/
    private Integer isDeleted;

    /** 创建人 **/
    private String creator;

    /** 创建时间 **/
    private Date gmtCreate;

    /** 修改人 **/
    private String modifier;

    /** 修改时间 **/
    private Date gmtModified;
}
