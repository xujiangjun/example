package com.xujiangjun.example.service.tx;

/**
 * @author xujiangjun
 * @since 2018.12.03
 */
public interface TxLoginLogService {

    void addLoginLog(String username, String lastIp);
}
