package com.xujiangjun.example.service.tx;

import com.xujiangjun.example.common.exception.BusinessException;
import com.xujiangjun.example.common.exception.CheckFailException;
import com.xujiangjun.example.service.base.BaseTest;
import com.xujiangjun.example.service.tx.impl.TxUserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author xujiangjun
 * @since 2018.12.03
 */
@Rollback
public class TxUserServiceTest extends BaseTest {

    @Autowired
    private TxUserService txUserService;

    @Test
    public void testRegister() throws Exception {
        txUserService.register("Wel", "test", "123");
    }

    @Test(expected = BusinessException.class)
    public void testRegisterWithException() throws Exception {
        txUserService.registerWithException("Wel", "testException", "1234");
    }

    @Test(expected = BusinessException.class)
    public void testLogin() throws Exception {
        txUserService.login("test", "123");
    }

    @Test(expected = BusinessException.class)
    public void testLoginException() throws Exception {
        txUserService.loginCallInSameClass("testException", "1234");
    }

    @Test
    public void testLoginWithRollBack() throws Exception {
        txUserService.loginWithRollBack("testException", "1234");
    }

}