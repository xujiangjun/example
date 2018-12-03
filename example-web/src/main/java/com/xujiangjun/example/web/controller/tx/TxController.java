package com.xujiangjun.example.web.controller.tx;

import com.xujiangjun.example.common.domain.Result;
import com.xujiangjun.example.service.tx.TxUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujiangjun
 * @since 2018.12.05
 */
@Slf4j
@RestController
@RequestMapping("tx")
public class TxController {

    @Autowired
    private TxUserService txUserService;

    @RequestMapping("register")
    public Result<String> register(){
        txUserService.register("Wel", "test", "123");
        return Result.wrapSuccessfulResult("Success");
    }

    @RequestMapping("registerWithException")
    public Result<String> registerWithException(){
        txUserService.registerWithException("Wel", "testException", "1234");
        return Result.wrapSuccessfulResult("Success");
    }

    @RequestMapping("login")
    public Result<String> login(){
        txUserService.login("test", "123");
        return Result.wrapSuccessfulResult("Success");
    }

    @RequestMapping("loginCallInSameClass")
    public Result<String> loginCallInSameClass(){
        txUserService.loginCallInSameClass("testException", "1234");
        return Result.wrapSuccessfulResult("Success");
    }

    @RequestMapping("loginWithRollBack")
    public Result<String> loginWithRollBack(){
        txUserService.loginWithRollBack("testException", "1234");
        return Result.wrapSuccessfulResult("Success");
    }
}
