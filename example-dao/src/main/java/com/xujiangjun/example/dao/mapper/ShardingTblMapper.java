package com.xujiangjun.example.dao.mapper;

import com.xujiangjun.example.dao.model.ShardingTbl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShardingTblMapper {
    int insert(ShardingTbl record);

    int insertSelective(ShardingTbl record);

    ShardingTbl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShardingTbl record);

    int updateByPrimaryKey(ShardingTbl record);

    List<ShardingTbl> listByParams(Integer isDeleted);
}