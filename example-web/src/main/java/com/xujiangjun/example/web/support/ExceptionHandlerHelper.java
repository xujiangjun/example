package com.xujiangjun.example.web.support;

import com.xujiangjun.example.common.domain.Result;
import com.xujiangjun.example.common.enums.ErrorEnum;
import com.xujiangjun.example.common.exception.BusinessException;
import com.xujiangjun.example.common.exception.CheckFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller 异常处理
 * @author xujiangjun
 * @date 2017-04-01 15:32
 */
@Slf4j
@ControllerAdvice(annotations = {Controller.class, RestController.class})
public class ExceptionHandlerHelper {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandler(Exception e, HttpServletRequest request) {
        if (e instanceof CheckFailException) {
            CheckFailException exception = (CheckFailException) e;
            return Result.wrapFailureResult(exception.getCode(), exception.getMessage());
        } else if (e instanceof BusinessException) {
            BusinessException exception = (BusinessException) e;
            return Result.wrapFailureResult(exception.getCode(), exception.getMessage());
        } else {
            log.error("服务器内部发生非自定义异常，msg:", e);
            return Result.wrapFailureResult(ErrorEnum.INTERNAL_SERVER_ERROR);
        }
    }
}
