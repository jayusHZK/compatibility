package com.jayus.smallMyBatis.step06.session.defaults;

import com.jayus.smallMyBatis.step06.executor.Executor;
import com.jayus.smallMyBatis.step06.mapping.Environment;
import com.jayus.smallMyBatis.step06.mapping.transaction.Transaction;
import com.jayus.smallMyBatis.step06.mapping.transaction.TransactionFactory;
import com.jayus.smallMyBatis.step06.session.Configuration;
import com.jayus.smallMyBatis.step06.session.SqlSession;
import com.jayus.smallMyBatis.step06.session.SqlSessionFactory;
import com.jayus.smallMyBatis.step06.session.TransactionIsolationLevel;

import java.sql.SQLException;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction tx = null;
        try {
            // 通过事务工厂创建事务
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
