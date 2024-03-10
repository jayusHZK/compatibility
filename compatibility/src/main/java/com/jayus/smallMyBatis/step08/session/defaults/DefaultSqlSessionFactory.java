package com.jayus.smallMyBatis.step08.session.defaults;

import com.jayus.smallMyBatis.step08.executor.Executor;
import com.jayus.smallMyBatis.step08.mapping.Environment;
import com.jayus.smallMyBatis.step08.session.Configuration;
import com.jayus.smallMyBatis.step08.session.SqlSession;
import com.jayus.smallMyBatis.step08.session.SqlSessionFactory;
import com.jayus.smallMyBatis.step08.session.TransactionIsolationLevel;
import com.jayus.smallMyBatis.step08.transaction.Transaction;
import com.jayus.smallMyBatis.step08.transaction.TransactionFactory;

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
            // 创建执行器
            final Executor executor = configuration.newExecutor(tx);
            // 创建 DefaultSqlSession
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
