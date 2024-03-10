package com.jayus.smallMyBatis.step09.executor;

import com.jayus.smallMyBatis.step09.executor.stamtent.StatementHandler;
import com.jayus.smallMyBatis.step09.mapping.BoundSql;
import com.jayus.smallMyBatis.step09.mapping.MappedStatement;
import com.jayus.smallMyBatis.step09.session.Configuration;
import com.jayus.smallMyBatis.step09.session.ResultHandler;
import com.jayus.smallMyBatis.step09.transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 简单执行器
 */
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
            Statement stmt = handler.prepare(connection);
            handler.parameterize(stmt);
            return handler.query(stmt,resultHandler);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
