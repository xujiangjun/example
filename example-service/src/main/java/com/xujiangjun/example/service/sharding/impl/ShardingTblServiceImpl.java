package com.xujiangjun.example.service.sharding.impl;

import com.xujiangjun.example.dao.mapper.ShardingTblMapper;
import com.xujiangjun.example.dao.model.ShardingTbl;
import com.xujiangjun.example.service.sharding.ShardingTblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xujiangjun
 * @since 2019.06.06
 */
@Service
public class ShardingTblServiceImpl implements ShardingTblService {

    @Autowired
    private ShardingTblMapper shardingTblMapper;

    @Override
    public void insert(ShardingTbl shardingTbl) {
        shardingTblMapper.insertSelective(shardingTbl);
    }

    @Override
    public ShardingTbl getById(Long id) {
        return shardingTblMapper.selectByPrimaryKey(id);
    }
}
