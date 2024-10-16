package com.jayus.smallMyBatis.step13.transaction;

import com.jayus.smallMyBatis.step13.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @ClassName TransactionFactory
 * @Description: 事务工厂
 * @date: 2024/10/12 15:56
 */
public interface TransactionFactory {

    Transaction newTransaction(Connection conn);

    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level,boolean autoCommit);

}
