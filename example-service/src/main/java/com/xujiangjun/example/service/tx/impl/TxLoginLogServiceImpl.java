package com.xujiangjun.example.service.tx.impl;
import java.util.Date;

import com.xujiangjun.example.common.enums.ErrorEnum;
import com.xujiangjun.example.common.exception.BusinessException;
import com.xujiangjun.example.dao.mapper.TxLoginLogMapper;
import com.xujiangjun.example.dao.model.TxLoginLog;
import com.xujiangjun.example.service.tx.TxLoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xujiangjun
 * @since 2018.12.03
 */
@Slf4j
@Service
public class TxLoginLogServiceImpl implements TxLoginLogService {

    @Autowired
    private TxLoginLogMapper txLoginLogMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addLoginLog(String username, String lastIp) {
        TxLoginLog txLoginLog = new TxLoginLog();
        txLoginLog.setGmtCreate(new Date());
        txLoginLog.setUsername(username);
        txLoginLog.setLastIp(lastIp);
        txLoginLogMapper.insertSelective(txLoginLog);
        throw new BusinessException(ErrorEnum.INTERNAL_SERVER_ERROR);
    }
}
