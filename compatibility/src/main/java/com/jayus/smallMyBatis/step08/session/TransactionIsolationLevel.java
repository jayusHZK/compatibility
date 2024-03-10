package com.jayus.smallMyBatis.step08.session;

import java.sql.Connection;


public enum TransactionIsolationLevel {

    //包括JDBC支持的5个级别
    NONE(Connection.TRANSACTION_NONE),
    //读已提交
    READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),
    // 读未提交
    READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),
    // 可重复读
    REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),
    // 串行化
    SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE);

    private final int level;

    TransactionIsolationLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

}
