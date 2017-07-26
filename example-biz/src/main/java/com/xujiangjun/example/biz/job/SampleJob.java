package com.xujiangjun.example.biz.job;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 定时任务示例
 *
 * @author xujiangjun
 * @date 2017-07-26 11:36
 */
@Slf4j
public class SampleJob {

    public void execute(){
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String ipAddress = inetAddress.getHostAddress();
            log.info("不继承QuartzJobBean方式---调度进行中... [ip:" + ipAddress + "]");
        } catch (UnknownHostException e) {
            log.error("获取本机ip异常", e);
        }
    }
}
