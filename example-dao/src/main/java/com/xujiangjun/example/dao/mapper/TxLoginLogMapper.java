package com.xujiangjun.example.dao.mapper;

import com.xujiangjun.example.dao.model.TxLoginLog;
import org.springframework.stereotype.Repository;

@Repository
public interface TxLoginLogMapper {
    int insert(TxLoginLog record);

    int insertSelective(TxLoginLog record);

    TxLoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TxLoginLog record);

    int updateByPrimaryKey(TxLoginLog record);
}