package com.xujiangjun.example.common.domain;

import lombok.Data;

/**
 * 用于包装枚举类，然后返回
 *
 * @author xujiangjun
 * @date 2017-05-03 13:29
 */
@Data
public class Pair {

    private int key;

    private String value;
}
