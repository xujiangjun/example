package com.xujiangjun.example.service.tx.impl;
import java.util.Date;

import com.xujiangjun.example.common.enums.ErrorEnum;
import com.xujiangjun.example.common.exception.BusinessException;
import com.xujiangjun.example.common.exception.CheckFailException;
import com.xujiangjun.example.dao.mapper.TxLoginLogMapper;
import com.xujiangjun.example.dao.mapper.TxUserMapper;
import com.xujiangjun.example.dao.model.TxLoginLog;
import com.xujiangjun.example.dao.model.TxUser;
import com.xujiangjun.example.service.tx.TxLoginLogService;
import com.xujiangjun.example.service.tx.TxUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xujiangjun
 * @since 2018.12.03
 */
@Slf4j
@Service
public class TxUserServiceImpl implements TxUserService {

    @Autowired
    private TxUserMapper txUserMapper;

    @Autowired
    private TxLoginLogService txLoginLogService;

    @Override
    public void register(String nickname, String username, String password) {
        addTxUser(nickname, username, password);
    }

    @Override
    public void registerWithException(String nickname, String username, String password) {
        addTxUserWithException(nickname, username, password);
    }

    private void addTxUser(String nickname, String username, String password){
        TxUser txUser = new TxUser();
        txUser.setGmtCreate(new Date());
        txUser.setNickname(nickname);
        txUser.setUsername(username);
        txUser.setPassword(DigestUtils.md5Hex(password));
        txUserMapper.insertSelective(txUser);
    }

    @Transactional(rollbackFor = Exception.class)
    private void addTxUserWithException(String nickname, String username, String password){
        addTxUser(nickname, username, password);
        throw new BusinessException(ErrorEnum.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void login(String username, String password) {
        TxUser txUser = txUserMapper.getByUsername(username);
        if(txUser == null || !txUser.getPassword().equals(DigestUtils.md5Hex(password))){
            throw new CheckFailException("", "用户名或密码错误");
        }
        updateGmtModifiedById(txUser.getId());
        txLoginLogService.addLoginLog(username, "192.168.1.162");
    }

    @Override
    public void loginCallInSameClass(String username, String password) {
        TxUser txUser = txUserMapper.getByUsername(username);
        if(txUser == null || !txUser.getPassword().equals(DigestUtils.md5Hex(password))){
            throw new CheckFailException("", "用户名或密码错误");
        }
        updateGmtModifiedById(txUser.getId());
        addLoginLog(username, "192.168.1.162");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void loginWithRollBack(String username, String password) {
        TxUser txUser = txUserMapper.getByUsername(username);
        if(txUser == null || !txUser.getPassword().equals(DigestUtils.md5Hex(password))){
            throw new CheckFailException("", "用户名或密码错误");
        }
        updateGmtModifiedById(txUser.getId());
        try {
            txLoginLogService.addLoginLog(username, "192.168.1.162");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateGmtModifiedById(Long id){
        TxUser updateTxUser = new TxUser();
        updateTxUser.setId(id);
        updateTxUser.setGmtModified(new Date());
        int updateCount = txUserMapper.updateByPrimaryKeySelective(updateTxUser);
        if(updateCount == 0){
            throw new BusinessException(ErrorEnum.UPDATE_FAIL);
        }
    }

    @Autowired
    private TxLoginLogMapper txLoginLogMapper;

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
