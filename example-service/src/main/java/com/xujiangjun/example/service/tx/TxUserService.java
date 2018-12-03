package com.xujiangjun.example.service.tx;

/**
 * @author xujiangjun
 * @since 2018.12.03
 */
public interface TxUserService {

    void register(String nickname, String username, String password);

    void registerWithException(String nickname, String username, String password);

    void login(String username, String password);

    void loginCallInSameClass(String username, String password);

    void loginWithRollBack(String username, String password);
}
