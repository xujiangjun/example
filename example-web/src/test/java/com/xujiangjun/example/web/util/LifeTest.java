package com.xujiangjun.example.web.util;

import com.xujiangjun.example.common.util.DateUtils;
import org.joda.time.DateTime;

/**
 * @author xujiangjun
 * @since 2018.11.09
 */
public class LifeTest {


    public static void main(String[] args) {
        testNextDay();
    }

    public static void testNextDay(){
        DateTime startTime = DateUtils.fullFormatter.parseDateTime("2018-11-09 10:00:00");
        DateTime nextTime;
        for (int i = 0; i < 5; i++) {
            nextTime = startTime.plusDays(25);
            startTime = nextTime;
            System.out.println("最近第" + (i + 1) + "次时间：" + nextTime.toString("yyyy-MM-dd HH:mm:ss"));
        }
    }
}
