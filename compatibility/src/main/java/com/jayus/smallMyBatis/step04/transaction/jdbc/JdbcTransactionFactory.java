package com.jayus.smallMyBatis.step04.transaction.jdbc;

import com.jayus.smallMyBatis.step04.transaction.Transaction;
import com.jayus.smallMyBatis.step04.transaction.TransactionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

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
