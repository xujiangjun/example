package com.xujiangjun.example.service.sharding;

import com.xujiangjun.example.dao.model.ShardingTbl;

/**
 * @author xujiangjun
 * @since 2019.06.06
 */
public interface ShardingTblService {

    void insert(ShardingTbl shardingTbl);

    ShardingTbl getById(Long id);
}
