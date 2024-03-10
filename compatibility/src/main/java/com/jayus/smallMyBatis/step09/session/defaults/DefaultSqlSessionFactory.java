package com.jayus.smallMyBatis.step09.session.defaults;

import com.jayus.smallMyBatis.step09.executor.Executor;
import com.jayus.smallMyBatis.step09.mapping.Environment;
import com.jayus.smallMyBatis.step09.session.Configuration;
import com.jayus.smallMyBatis.step09.session.SqlSession;
import com.jayus.smallMyBatis.step09.session.SqlSessionFactory;
import com.jayus.smallMyBatis.step09.session.TransactionIsolationLevel;
import com.jayus.smallMyBatis.step09.transaction.Transaction;
import com.jayus.smallMyBatis.step09.transaction.TransactionFactory;

import java.sql.SQLException;

/**
 * 默认的 SqlSessionFactory
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED,false);
            final Executor executor = configuration.newExecutor(tx);
            return new DefaultSqlSession(configuration,executor);
        } catch (Exception e) {
            try {
                assert tx != null;
                tx.close();
            } catch (SQLException ignore) {
            }
            throw new RuntimeException("Error opening session.  Cause: " + e);
        }
    }
}
