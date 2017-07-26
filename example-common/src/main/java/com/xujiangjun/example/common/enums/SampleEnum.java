package com.xujiangjun.example.common.enums;

import com.xujiangjun.example.common.domain.Pair;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 枚举类示例代码
 * Note：不能直接返回前端枚举对象，前端无法取值，需使用一个对象进行封装
 *
 * @author xujiangjun
 * @date 2017-07-26 11:20
 */
@Getter
public enum SampleEnum {
    ;

    private int key;

    private String value;

    SampleEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 通过枚举类的key获取对应的枚举，从而获得对应的枚举描述
     *
     * @param key 枚举类的key
     * @return    对应的枚举类
     */
    public static SampleEnum getEnum(int key) {
        for (SampleEnum item : values()) {
            if (item.key == key) {
                return item;
            }
        }
        return null;
    }

    /**
     * 用于返回给前端所有的枚举集合
     *
     * @return 枚举集合
     */
    public static List<Pair> getPairList(){
        List<Pair> pairList = new ArrayList<>();
        for(SampleEnum item : values()){
            Pair pair = new Pair();
            pair.setKey(item.key);
            pair.setValue(item.value);
            pairList.add(pair);
        }
        return pairList;
    }
}
