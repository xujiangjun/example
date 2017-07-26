package com.xujiangjun.example.service;

import com.xujiangjun.example.common.constant.ConfigConsts;
import com.xujiangjun.example.service.base.BaseTest;
import com.xujiangjun.example.service.base.ParamConfigService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xujiangjun
 * @date 2017-07-17 19:19
 */
public class ParamConfigServiceTest extends BaseTest {

    @Autowired
    private ParamConfigService paramConfigService;

    @Test
    public void getByParamNo() throws Exception {
        System.out.println(paramConfigService.getByParamNo(ConfigConsts.IP_WHITE_LIST));
    }

    @Test
    public void updateByParamNo() throws Exception {
        paramConfigService.updateByParamNo(ConfigConsts.IP_WHITE_LIST, "183.129.150.18");
    }

    @Test
    public void appendTo() throws Exception {
        paramConfigService.appendTo(ConfigConsts.IP_WHITE_LIST, "180.153.28.46");
    }

    @Test
    public void removeSpecifiedContent() throws Exception {
        paramConfigService.removeSpecifiedContent(ConfigConsts.IP_WHITE_LIST, "183.129.150.18");
    }

    @Test
    public void flushCache() throws Exception {

    }

}