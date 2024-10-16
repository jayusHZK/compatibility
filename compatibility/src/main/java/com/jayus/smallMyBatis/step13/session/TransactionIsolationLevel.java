package com.jayus.smallMyBatis.step13.session;

import java.sql.Connection;

/**
 * @ClassName TransactionIsolationLevel
 * @Description: 事务的隔离级别
 * @date: 2024/10/12 15:57
 */
public enum TransactionIsolationLevel {

    NONE(Connection.TRANSACTION_NONE),
    READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),
    READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),
    REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),
    SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE);

    private final int level;

    TransactionIsolationLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

}
