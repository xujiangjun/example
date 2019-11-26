package com.xujiangjun.example.service.sharding.algorithm;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author xujiangjun
 * @since 2019.06.19
 */
@Component("tblRangeShardingAlgorithm")
public class TblRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        List<String> collection = new ArrayList<>();
        Range<Long> valueRange = shardingValue.getValueRange();
        for (long i = valueRange.lowerEndpoint(); i <= valueRange.upperEndpoint(); i++){
            for (String availableTargetName : availableTargetNames) {
                if(availableTargetName.endsWith(i % 4 + "")){
                    collection.add(availableTargetName);
                }
            }
        }
        return collection;
    }
}
