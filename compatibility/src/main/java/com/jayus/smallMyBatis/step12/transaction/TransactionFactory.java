package com.jayus.smallMyBatis.step12.transaction;

import com.jayus.smallMyBatis.step12.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @ClassName TransactionFactory
 * @Description: 事务接口
 * @date: 2024/9/19 02:02
 */
public interface TransactionFactory {

    /*
    根据 Connection 创建 Transaction
     */
    Transaction newTransaction(Connection conn);

    /*
    根据数据源和事务隔离级别创建 Transaction
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level,
                               boolean autoCommit);

}
