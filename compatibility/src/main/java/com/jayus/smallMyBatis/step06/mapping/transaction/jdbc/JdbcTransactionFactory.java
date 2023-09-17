package com.jayus.smallMyBatis.step06.mapping.transaction.jdbc;

import com.jayus.smallMyBatis.step06.session.TransactionIsolationLevel;
import com.jayus.smallMyBatis.step06.mapping.transaction.Transaction;
import com.jayus.smallMyBatis.step06.mapping.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

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
