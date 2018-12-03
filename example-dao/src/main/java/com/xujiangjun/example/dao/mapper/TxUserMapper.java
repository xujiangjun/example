package com.xujiangjun.example.dao.mapper;

import com.xujiangjun.example.dao.model.TxUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TxUserMapper {
    int insert(TxUser record);

    int insertSelective(TxUser record);

    TxUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TxUser record);

    int updateByPrimaryKey(TxUser record);

    TxUser getByUsername(String username);
}