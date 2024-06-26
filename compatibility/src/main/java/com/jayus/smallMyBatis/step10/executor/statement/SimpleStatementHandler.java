package com.jayus.smallMyBatis.step10.executor.statement;

import com.jayus.smallMyBatis.step10.executor.Executor;
import com.jayus.smallMyBatis.step10.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step10.executor.resultset.ResultSetHandler;
import com.jayus.smallMyBatis.step10.mapping.BoundSql;
import com.jayus.smallMyBatis.step10.mapping.MappedStatement;
import com.jayus.smallMyBatis.step10.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 简单语句处理器
 */
public class SimpleStatementHandler extends BaseStatementHandler {

    public SimpleStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject,
                                  ResultHandler resultHandler,  BoundSql boundSql) {
        super(executor, mappedStatement, parameterObject, resultHandler, boundSql);
    }

    @Override
    protected Statement instaniateStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    @Override
    public void parameterize(Statement statement) throws SQLException {

    }

    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        String sql = boundSql.getSql();
        statement.execute(sql);
        return resultSetHandler.handleResultSets(statement);
    }
}
