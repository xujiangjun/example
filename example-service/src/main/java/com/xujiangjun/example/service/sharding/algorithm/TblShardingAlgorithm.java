package com.xujiangjun.example.service.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author xujiangjun
 * @since 2019.06.06
 */
@Component("tblShardingAlgorithm")
public class TblShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        for (String availableTargetName : availableTargetNames) {
            if(availableTargetName.endsWith(shardingValue.getValue() % 4 + "")){
                return availableTargetName;
            }
        }
        return null;
    }
}
