package com.xujiangjun.example.dao.params.base;

import lombok.Data;

import java.util.List;

/**
 * 基础查询参数（分页、排序）
 * @author xujiangjun
 * @date 2017-05-09 13:53
 */
@Data
public class BaseQueryParam {

    /** 查询起始位置 **/
    private int offset;

    /** 最大查询条数 **/
    private int limit;

    /** 自定义排序 **/
    private List<String> sorts;
}
