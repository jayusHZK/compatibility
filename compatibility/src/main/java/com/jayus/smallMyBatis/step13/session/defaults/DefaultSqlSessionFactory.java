package com.jayus.smallMyBatis.step13.session.defaults;

import com.jayus.smallMyBatis.step13.executor.Executor;
import com.jayus.smallMyBatis.step13.mapping.Environment;
import com.jayus.smallMyBatis.step13.session.Configuration;
import com.jayus.smallMyBatis.step13.session.SqlSession;
import com.jayus.smallMyBatis.step13.session.SqlSessionFactory;
import com.jayus.smallMyBatis.step13.session.TransactionIsolationLevel;
import com.jayus.smallMyBatis.step13.transaction.Transaction;
import com.jayus.smallMyBatis.step13.transaction.TransactionFactory;

import java.sql.SQLException;

/**
 * @ClassName DefaultSqlSessionFactory
 * @Description: 默认的 DefaultSqlSessionFactory
 * @date: 2024/10/16 16:28
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
