package com.jayus.smallMyBatis.step13.transaction.jdbc;

import com.jayus.smallMyBatis.step13.session.TransactionIsolationLevel;
import com.jayus.smallMyBatis.step13.transaction.Transaction;
import com.jayus.smallMyBatis.step13.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @ClassName JdbcTransactionFactory
 * @Description: JdbcTransaction工厂
 * @date: 2024/10/12 16:02
 */
public class JdbcTransactionFactory implements TransactionFactory {

    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource,level,autoCommit);
    }
}
