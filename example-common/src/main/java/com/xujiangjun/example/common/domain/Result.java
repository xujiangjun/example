package com.xujiangjun.example.common.domain;

import com.xujiangjun.example.common.enums.ErrorEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xujiangjun
 * @date 2017-04-01 15:37
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 6110065893706471853L;

    /** 提示码 **/
    private String code;

    /** 提示信息 **/
    private String message;

    /** 是否成功 **/
    private boolean success;

    /** 返回数据 **/
    private T data;

    public static <T> Result<T> wrapSuccessfulResult(T data){
        Result<T> result = new Result<>();
        result.success = true;
        result.data = data;
        return result;
    }

    public static <T> Result<T> wrapSuccessfulResult(String code, String message, T data){
        Result<T> result = new Result<>();
        result.code = code;
        result.message = message;
        result.success = true;
        result.data = data;
        return result;
    }

    public static <T> Result<T> wrapFailureResult(String code, String message){
        Result<T> result = new Result<>();
        result.code = code;
        result.message = message;
        result.success = false;
        return result;
    }

    public static <S> Result<S> wrapFailureResult(Result<?> otherResult){
        Result<S> result = new Result<>();
        result.code = otherResult.getCode();
        result.message = otherResult.getMessage();
        result.success = false;
        return result;
    }

    public static <T> Result<T> wrapFailureResult(ErrorEnum errorEnum){
        Result<T> result = new Result<>();
        result.code = errorEnum.getCode();
        result.message = errorEnum.getMessage();
        result.success = false;
        return result;
    }
}
