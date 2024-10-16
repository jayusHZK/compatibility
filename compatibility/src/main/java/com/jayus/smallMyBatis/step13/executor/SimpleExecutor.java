package com.jayus.smallMyBatis.step13.executor;

import com.jayus.smallMyBatis.step13.executor.statement.StatementHandler;
import com.jayus.smallMyBatis.step13.mapping.BoundSql;
import com.jayus.smallMyBatis.step13.mapping.MappedStatement;
import com.jayus.smallMyBatis.step13.session.Configuration;
import com.jayus.smallMyBatis.step13.session.ResultHandler;
import com.jayus.smallMyBatis.step13.session.RowBounds;
import com.jayus.smallMyBatis.step13.transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @ClassName SimpleExecutor
 * @Description: 简单执行器
 * @date: 2024/10/13 10:48
 */
public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuraction, Transaction transaction) {
        super(configuraction, transaction);
    }

    @Override
    protected int doUpdate(MappedStatement ms, Object parameter) throws SQLException {
        Statement stmt = null;
        try {
            Configuration configuraction = ms.getConfiguraction();
            StatementHandler handler = configuraction.newStatementHandler(this,ms,parameter,RowBounds.DEFAULT,null,null);
            stmt = prepareStatement(handler);
            return handler.update(stmt);
        } finally {
            closeStatement(stmt);
        }
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds
            , ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        Statement stmt = null;
        try {
            Configuration configuraction = ms.getConfiguraction();
            StatementHandler handler = configuraction.newStatementHandler(this,ms,parameter,rowBounds,resultHandler,boundSql);
            stmt = prepareStatement(handler);
            return handler.query(stmt,resultHandler);
        } finally {
            closeStatement(stmt);
        }
    }

    private Statement prepareStatement(StatementHandler handler) throws SQLException {
        Statement stmt;
        Connection connection = transaction.getConnection();
        stmt = handler.prepare(connection);
        handler.parameterize(stmt);
        return stmt;
    }

}
