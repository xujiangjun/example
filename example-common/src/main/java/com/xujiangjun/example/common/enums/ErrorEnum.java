package com.xujiangjun.example.common.enums;

import lombok.Getter;

/**
 * 错误枚举类
 *
 * 错误码的定义是为了便于在多系统情况下出现问题时能够快速定位到某个系统的某个模块,在定位问题时更加方便地追根溯源.
 * 目前错误码总共分为8位,第1位为平台码,第2-3位系统码,第4位为异常等级码,第5-8位为具体异常定义码.
 * 第1位：0-代表公共异常; 1-电商系统; 2-ERP系统; 3-CRM系统
 * 第2-3位：00-代表legend项目; 01-代表engine项目
 * 第4位：1-校验（校验错误）; 2-警告（会出现潜在的情形）; 3-错误（错误事件发生，但仍然不影响系统继续运行）; 4-严重（错误事件将会导致应用程序退出）;
 *
 * @author xujiangjun
 * @date 2017-04-01 15:47
 */
@Getter
public enum ErrorEnum {
    PARAM_IS_NULL("00010001", "参数不允许为空"),
    PARAM_CHECK_FAIL("00010002", "参数不规范"),
    CHECK_FAIL("00010003", "校验不通过"),
    NOT_EXISTS("00010004", "数据不存在"),
    INTERNAL_SERVER_ERROR("00030001", "服务器内部错误"),
    UPDATE_FAIL("00030002", "更新数据项失败");

    private String code;

    private String message;

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
