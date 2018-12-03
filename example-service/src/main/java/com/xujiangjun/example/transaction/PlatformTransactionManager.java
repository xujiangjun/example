package com.xujiangjun.example.transaction;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

/**
 * @author xujiangjun
 * @since 2018.11.28
 */
public interface PlatformTransactionManager {

    /**
     * Return a currently active transaction or create a new one, according to
     * the specified propagation behavior.(根据指定的传播行为，返回当前活动的事务或创建一个新事务。)
     * @param definition TransactionDefinition instance (can be {@code null} for defaults),
     * describing propagation behavior, isolation level, timeout etc.
     * @return transaction status object representing the new or current transaction
     * @throws TransactionException in case of lookup, creation, or system errors
     */
    TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;

    /**
     * Commit the given transaction, with regard to its status.(使用事务目前的状态提交事务)
     *
     * @param status object returned by the {@code getTransaction} method
     * @throws TransactionException in case of a transaction failure
     * that the transaction coordinator initiated
     */
    void commit(TransactionStatus status) throws TransactionException;

    /**
     * Perform a rollback of the given transaction.(对执行的事务进行回滚)
     * @param status object returned by the {@code getTransaction} method
     * @throws TransactionException
     */
    void rollback(TransactionStatus status) throws TransactionException;
}
