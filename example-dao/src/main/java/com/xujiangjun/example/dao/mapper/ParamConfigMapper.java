package com.xujiangjun.example.dao.mapper;

import com.github.pagehelper.Page;
import com.xujiangjun.example.dao.model.ParamConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParamConfigMapper {
    int insert(ParamConfig record);

    int insertSelective(ParamConfig record);

    ParamConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParamConfig record);

    int updateByPrimaryKey(ParamConfig record);

    ParamConfig selectByParamNo(String paramNo);

    int updateByParamNo(ParamConfig paramConfig);

    Page<ParamConfig> selectAll();

    Page<ParamConfig> selectAll(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("orderBy") String orderBy);
}