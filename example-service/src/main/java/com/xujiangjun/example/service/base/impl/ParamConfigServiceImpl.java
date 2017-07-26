package com.xujiangjun.example.service.base.impl;

import com.xujiangjun.example.common.constant.ConfigConsts;
import com.xujiangjun.example.common.domain.Result;
import com.xujiangjun.example.common.enums.ErrorEnum;
import com.xujiangjun.example.common.util.StringUtils;
import com.xujiangjun.example.dao.mapper.ParamConfigMapper;
import com.xujiangjun.example.dao.model.ParamConfig;
import com.xujiangjun.example.service.base.ParamConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 参数配置服务
 *
 * @author xujiangjun
 * @date 2017-07-01 02:11
 */
@Slf4j
@Service
public class ParamConfigServiceImpl implements ParamConfigService {

    @Autowired
    private ParamConfigMapper paramConfigMapper;

    /**
     * 根据paramNo获取paramValue(可考虑使用缓存)
     *
     * 返回值解析：如果是作为dubbo接口，即使数据项不存在也不应该返回success为false，
     * 提供方不做调用方的判断逻辑，只做数据查询工作
     *
     * @param paramNo 参数编码
     * @return 查询成功 - true | 查询失败 - false
     */
    @Override
    public Result<String> getByParamNo(String paramNo) {
        if (StringUtils.isEmpty(paramNo)) {
            return Result.wrapFailureResult(ErrorEnum.PARAM_IS_NULL);
        }
        ParamConfig paramConfig = paramConfigMapper.selectByParamNo(paramNo);
        if (paramConfig == null) {
            return Result.wrapFailureResult(ErrorEnum.NOT_EXISTS);
        }
        return Result.wrapSuccessfulResult(paramConfig.getParamValue());
    }


    /**
     * 根据paramNo更新paramValue
     *
     * @param paramNo    参数编码
     * @param paramValue 参数值
     * @return 处理成功 - true | 处理失败 - false
     */
    @Override
    public Result<String> updateByParamNo(String paramNo, String paramValue) {
        Result<String> result = getByParamNo(paramNo);
        if (!result.isSuccess()) {
            return Result.wrapFailureResult(result);
        }
        return doUpdateByParamNo(paramNo, paramValue, result.getData());
    }


    /**
     * 在原值基础上追加paramValue
     *
     * @param paramNo    参数编码
     * @param paramValue 参数值
     * @return 追加成功 - true | 追加失败 - false
     */
    @Override
    public Result<String> appendTo(String paramNo, String paramValue) {
        Result<String> result = getByParamNo(paramNo);
        if (!result.isSuccess()) {
            return Result.wrapFailureResult(result);
        }
        String originParamValue = result.getData();
        String newParamValue;
        if (StringUtils.isEmpty(originParamValue)) {
            newParamValue = paramValue;
        } else {
            newParamValue = originParamValue + "," + paramValue;
        }
        return doUpdateByParamNo(paramNo, newParamValue, originParamValue);
    }


    /**
     * 从原值中删除指定的paramValue
     *
     * @param paramNo    参数编码
     * @param paramValue 参数值
     * @return 删除成功 - true | 删除失败 - false
     */
    @Override
    public Result<String> removeSpecifiedContent(String paramNo, String paramValue) {
        Result<String> result = getByParamNo(paramNo);
        if (!result.isSuccess()) {
            return Result.wrapFailureResult(result);
        }
        String originParamValue = result.getData();
        if (StringUtils.isEmpty(originParamValue)) {
            return Result.wrapFailureResult("", "配置项中的值为空");
        }
        if (!StringUtils.contains(paramValue, originParamValue)) {
            return Result.wrapFailureResult("", "配置项中不包含指定删除的值");
        }
        String newParamValue = StringUtils.removeContains(paramValue, originParamValue);
        return doUpdateByParamNo(paramNo, newParamValue, originParamValue);
    }


    /**
     * 如果采用了缓存，可以调用该接口刷新缓存
     *
     * @param paramNo 参数编码
     * @return 刷新成功 - true | 刷新失败- false
     */
    @Override
    public Result<String> flushCache(String paramNo) {
        return null;
    }


    /**
     * 实际真正根据paramNo更新paramValue
     *
     * @param paramNo          参数编码
     * @param paramValue       新参数值
     * @param originParamValue 原参数值
     * @return 处理成功 - true | 处理失败 - false
     */
    private Result<String> doUpdateByParamNo(String paramNo, String paramValue, String originParamValue) {
        ParamConfig paramConfig = new ParamConfig();
        paramConfig.setParamNo(paramNo);
        paramConfig.setParamValue(paramValue);
        paramConfig.setGmtModified(new Date());
        paramConfig.setModifier(ConfigConsts.SYSTEM);
        int updateCount = paramConfigMapper.updateByParamNo(paramConfig);
        if (updateCount < 1) {
            log.error("更新配置项:{}, 原paramValue:{}, 新paramValue:{}", paramNo, originParamValue, paramValue);
            return Result.wrapFailureResult(ErrorEnum.UPDATE_FAIL);
        }
        return Result.wrapSuccessfulResult("更新配置项" + paramNo + "成功");
    }
}
