package com.jayus.smallMyBatis.step04.transaction;

import org.apache.ibatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

public interface TransactionFactory {

    /**
     * 根据 Connection 创建 Transaction
     * @param conn
     * @return
     */
    Transaction newTransaction(Connection conn);

    /**
     * 根据数据源和事务隔离级别创建 Transaction
     * @param dataSource
     * @param level 事务隔离级别
     * @param autoCommit 是否自动提交
     * @return
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level,boolean autoCommit);


}
