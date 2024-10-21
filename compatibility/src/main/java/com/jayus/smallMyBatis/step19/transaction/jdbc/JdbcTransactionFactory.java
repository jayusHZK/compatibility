package com.jayus.smallMyBatis.step19.transaction.jdbc;

import com.jayus.smallMyBatis.step19.session.TransactionIsolationLevel;
import com.jayus.smallMyBatis.step19.transaction.Transaction;
import com.jayus.smallMyBatis.step19.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @ClassName JdbcTransactionFactory
 * @Description: JdbcTransaction 工厂
 * @date: 2024/10/16 21:21
 */
public class JdbcTransactionFactory implements TransactionFactory {

    @Override
    public Transaction newTransaction(Connection connection) {
        return new JdbcTransaction(connection);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource,level,autoCommit);
    }

}
