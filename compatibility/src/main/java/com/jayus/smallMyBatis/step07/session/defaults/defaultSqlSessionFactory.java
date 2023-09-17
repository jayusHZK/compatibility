package com.jayus.smallMyBatis.step07.session.defaults;

import com.jayus.smallMyBatis.step07.executor.Executor;
import com.jayus.smallMyBatis.step07.mapping.Environment;
import com.jayus.smallMyBatis.step07.mapping.transaction.Transaction;
import com.jayus.smallMyBatis.step07.mapping.transaction.TransactionFactory;
import com.jayus.smallMyBatis.step07.session.Configuration;
import com.jayus.smallMyBatis.step07.session.SqlSession;
import com.jayus.smallMyBatis.step07.session.SqlSessionFactory;
import com.jayus.smallMyBatis.step07.session.TransactionIsolationLevel;

import java.sql.SQLException;

public class defaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public defaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction tx = null;

        try {
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx = transactionFactory.newTransaction(environment.getDataSource(), TransactionIsolationLevel.READ_COMMITTED,false);
            Executor executor = configuration.newExecutor(tx);
            return new DefaultSqlSession(configuration,executor);
        } catch (Exception e) {
            try{
                assert tx != null;
                tx.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }
}
