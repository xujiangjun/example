package com.xujiangjun.example.common.exception;

import com.xujiangjun.example.common.enums.ErrorEnum;
import lombok.Getter;

/**
 * 校验失败异常
 *
 * @author xujiangjun
 * @date 2017-05-03 13:47
 */
public class CheckFailException extends RuntimeException {
    @Getter
    private String code;

    public CheckFailException(ErrorEnum errorEnum){
        super(errorEnum.getMessage());
        this.code = errorEnum.getCode();
    }

    public CheckFailException(final String code, final String message) {
        super(message);
        this.code = code;
    }

    public CheckFailException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }
}
