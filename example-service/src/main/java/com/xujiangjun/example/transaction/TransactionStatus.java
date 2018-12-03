package com.xujiangjun.example.transaction;

/**
 * @author xujiangjun
 * @since 2018.11.29
 */
public interface TransactionStatus {

    /**
     * 是否是新事务
     */
    boolean isNewTransaction();

    /**
     * 是否有savePoint
     */
    boolean hasSavepoint();

    /**
     * 设置为只回滚
     */
    void setRollbackOnly();

    /**
     * 是否为只回滚
     */
    boolean isRollbackOnly();

    /**
     * 是否已完成
     */
    boolean isCompleted();

}
