package com.xujiangjun.example.dao.mapper;

import com.xujiangjun.example.dao.model.ParamConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamConfigMapper {
    int insert(ParamConfig record);

    int insertSelective(ParamConfig record);

    ParamConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParamConfig record);

    int updateByPrimaryKey(ParamConfig record);

    ParamConfig selectByParamNo(String paramNo);

    int updateByParamNo(ParamConfig paramConfig);
}