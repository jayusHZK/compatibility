package com.jayus.smallMyBatis.step12.executor;

import com.jayus.smallMyBatis.step12.executor.statement.StatementHandler;
import com.jayus.smallMyBatis.step12.mapping.BoundSql;
import com.jayus.smallMyBatis.step12.mapping.MappedStatement;
import com.jayus.smallMyBatis.step12.session.Configuration;
import com.jayus.smallMyBatis.step12.session.ResultHandler;
import com.jayus.smallMyBatis.step12.session.RowBounds;
import com.jayus.smallMyBatis.step12.transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @ClassName SimpleExecutor
 * @Description: 简单执行器
 * @date: 2024/9/25 10:22
 */
public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected int doUpdate(MappedStatement ms, Object parameter) throws SQLException {
        Statement stmt = null;
        try {
            Configuration configuration = ms.getConfiguration();
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, RowBounds.DEFAULT, null, null);
            stmt = prepareStatement(handler);
            return handler.update(stmt);
        } finally {
            closeStatement(stmt);
        }
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        Statement stmt = null;
        try {
            Configuration configuration = ms.getConfiguration();
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, rowBounds, resultHandler, boundSql);
            stmt = prepareStatement(handler);
            return handler.query(stmt,resultHandler);
        } finally {
            closeStatement(stmt);
        }
    }

    private Statement prepareStatement(StatementHandler handler) throws SQLException {
        Statement stmt;
        Connection connection = transaction.getConnection();
        // 准备语句
        stmt = handler.prepare(connection);
        handler.parameterize(stmt);
        return stmt;
    }

}
