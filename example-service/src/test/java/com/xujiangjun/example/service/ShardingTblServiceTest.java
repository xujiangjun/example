package com.xujiangjun.example.service;
import java.util.Date;
import java.util.List;

import com.xujiangjun.example.dao.mapper.ShardingTblMapper;
import com.xujiangjun.example.dao.model.ShardingTbl;
import com.xujiangjun.example.service.base.BaseTest;
import com.xujiangjun.example.service.sharding.ShardingTblService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xujiangjun
 * @since 2019.06.06
 */
public class ShardingTblServiceTest extends BaseTest {

    @Autowired
    private ShardingTblService shardingTblService;

    @Autowired
    private ShardingTblMapper shardingTblMapper;

    @Test
    public void testInsert(){
        for (long i = 0; i < 16; i++) {
            ShardingTbl shardingTbl = new ShardingTbl();
            shardingTbl.setId(i);
            shardingTbl.setIsDeleted((byte)0);
            shardingTbl.setCreator("Hello");
            shardingTbl.setGmtCreate(new Date());
            shardingTbl.setModifier("Hello");
            shardingTbl.setGmtModified(new Date());
            shardingTblService.insert(shardingTbl);
        }
    }

    @Test
    public void testGetById(){
        ShardingTbl shardingTbl = shardingTblService.getById(1l);
    }

    @Test
    public void testListByParams(){
        List<ShardingTbl> shardingTbls = shardingTblMapper.listByParams(0);
    }
}
