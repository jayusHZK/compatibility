package com.jayus.smallMyBatis.step07.executor;

import com.jayus.smallMyBatis.step07.executor.statement.StatementHandler;
import com.jayus.smallMyBatis.step07.mapping.BoundSql;
import com.jayus.smallMyBatis.step07.mapping.MappedStatement;
import com.jayus.smallMyBatis.step07.mapping.transaction.Transaction;
import com.jayus.smallMyBatis.step07.session.Configuration;
import com.jayus.smallMyBatis.step07.session.ResultHandler;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        try {
            Configuration configuration = ms.getConfiguration();
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, resultHandler, boundSql);
            Connection connection = transaction.getConnection();
            Statement statement = handler.prepare(connection);
            // 处理参数
            handler.parameterize(statement);
            return handler.query(statement,resultHandler);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
