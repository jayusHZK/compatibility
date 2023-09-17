package com.jayus.smallMyBatis.step05.transaction.jdbc;

import com.jayus.smallMyBatis.step05.session.TransactionIsolationLevel;
import com.jayus.smallMyBatis.step05.transaction.Transaction;
import com.jayus.smallMyBatis.step05.transaction.TransactionFactory;

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
