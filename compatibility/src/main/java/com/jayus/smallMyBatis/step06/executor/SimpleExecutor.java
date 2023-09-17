package com.jayus.smallMyBatis.step06.executor;

import com.jayus.smallMyBatis.step06.executor.statement.StatementHandler;
import com.jayus.smallMyBatis.step06.mapping.BoundSql;
import com.jayus.smallMyBatis.step06.mapping.MappedStatement;
import com.jayus.smallMyBatis.step06.mapping.transaction.Transaction;
import com.jayus.smallMyBatis.step06.session.Configuration;
import com.jayus.smallMyBatis.step06.session.ResultHandler;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * 简单执行器
 */
public class SimpleExecutor extends BaseExecutor{

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        try {
            Configuration configuration = ms.getConfiguration();
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, resultHandler, boundSql);
            Connection connection = transaction.getConnection();
            Statement stateMent = handler.prepare(connection);
            handler.parameterize(stateMent);
            return handler.query(stateMent,resultHandler);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
