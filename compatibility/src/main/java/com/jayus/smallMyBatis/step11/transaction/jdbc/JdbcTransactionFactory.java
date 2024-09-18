package com.jayus.smallMyBatis.step11.transaction.jdbc;

import com.jayus.smallMyBatis.step11.session.TransactionIsolationLevel;
import com.jayus.smallMyBatis.step11.transaction.Transaction;
import com.jayus.smallMyBatis.step11.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @ClassName JdbcTransactionFactory
 * @Description: JdbcTransaction 工厂
 * @date: 2024/5/13 15:31
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
