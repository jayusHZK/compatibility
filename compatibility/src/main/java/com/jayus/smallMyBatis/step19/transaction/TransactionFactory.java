package com.jayus.smallMyBatis.step19.transaction;

import com.jayus.smallMyBatis.step19.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @ClassName TransactionFactory
 * @Description: 事务工厂
 * @date: 2024/10/16 21:17
 */
public interface TransactionFactory {

    /*
    根据 Connection 创建 Transaction
     */
    Transaction newTransaction(Connection connection);

    /*
    根据数据源和事务隔离级别创建 Transaction
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level,boolean autoCommit);

}
