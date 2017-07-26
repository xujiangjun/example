package com.xujiangjun.example.common.exception;

import com.xujiangjun.example.common.enums.ErrorEnum;
import lombok.Getter;

/**
 * 自定义业务异常类
 *
 * @author xujiangjun
 * @date 2017-04-01 17:12
 */
public class BusinessException extends RuntimeException {

    @Getter
    private String code;

    public BusinessException(ErrorEnum errorEnum){
        super(errorEnum.getMessage());
        this.code = errorEnum.getCode();
    }

    public BusinessException(final String code, final String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }
}
