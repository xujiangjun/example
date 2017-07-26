package com.xujiangjun.example.web.controller.base;

import com.xujiangjun.example.common.domain.Result;
import com.xujiangjun.example.service.base.ParamConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujiangjun
 * @date 2017-07-01 02:09
 */
@RestController
@RequestMapping("param")
public class ParamConfigController {

    @Autowired
    private ParamConfigService paramConfigService;

    @RequestMapping("")
    public Result<String> config(){
        return Result.wrapSuccessfulResult("Hello");
    }

}
