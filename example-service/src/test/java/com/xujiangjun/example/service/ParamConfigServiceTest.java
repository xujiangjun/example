package com.xujiangjun.example.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xujiangjun.example.common.constant.ConfigConsts;
import com.xujiangjun.example.dao.mapper.ParamConfigMapper;
import com.xujiangjun.example.dao.model.ParamConfig;
import com.xujiangjun.example.service.base.BaseTest;
import com.xujiangjun.example.service.base.ParamConfigService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xujiangjun
 * @date 2017-07-17 19:19
 */
public class ParamConfigServiceTest extends BaseTest {

    @Autowired
    private ParamConfigService paramConfigService;

    @Autowired
    private ParamConfigMapper paramConfigMapper;

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
    public void testSelectAll() throws Exception {
        PageHelper.startPage(3, 2, "id desc");
        Page<ParamConfig> paramConfigList = paramConfigMapper.selectAll();
        System.out.println(paramConfigList);
    }

    @Test
    public void testSelectAll2() throws Exception {
        Page<ParamConfig> paramConfigList = paramConfigMapper.selectAll(2, 2, "id desc");
        System.out.println(paramConfigList);
    }

    @Test
    public void flushCache() throws Exception {

    }

}