package com.xujiangjun.example.common.enums;

import lombok.Getter;

/**
 * 是否枚举类
 *
 * @author xujiangjun
 * @date 2017-07-26 11:15
 */
@Getter
public enum WhetherEnum {
    NO(0, "否"),
    YES(1, "是");

    private int key;

    private String value;

    WhetherEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
