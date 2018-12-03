package com.xujiangjun.example.transaction;

/**
 * @author xujiangjun
 * @since 2018.11.28
 */
public interface TransactionDefinition {

    /**
     * Return the propagation behavior.
     * @return 返回事务传播行为
     */
    int getPropagationBehavior();

    /**
     * Return the isolation level.
     * @return 返回事务隔离级别，事务管理器根据它来控制另外一个事务可以看到本事务内的哪些数据
     */
    int getIsolationLevel();

    /**
     * Return the transaction timeout.
     * @return 返回事务必须在多少秒内完成
     */
    int getTimeout();

    /**
     * Return whether to optimize as a read-only transaction.
     * @return 返回是否优化为只读事务
     */
    boolean isReadOnly();

    /**
     * Return the name of this transaction. Can be {@code null}.
     * @return 事务的名字
     */
    String getName();
}
