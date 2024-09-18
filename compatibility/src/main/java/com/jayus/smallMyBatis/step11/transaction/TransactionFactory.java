package com.jayus.smallMyBatis.step11.transaction;

import com.jayus.smallMyBatis.step11.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @ClassName TransactionFactory
 * @Description: 事务工厂
 * @date: 2024/5/13 15:00
 */
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
     * @param level
     * @param autoCommit
     * @return
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level,boolean autoCommit);

}
