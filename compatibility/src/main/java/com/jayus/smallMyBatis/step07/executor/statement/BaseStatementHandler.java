package com.jayus.smallMyBatis.step07.executor.statement;

import com.jayus.smallMyBatis.step07.executor.Executor;
import com.jayus.smallMyBatis.step07.executor.resultset.ResultSetHandler;
import com.jayus.smallMyBatis.step07.mapping.BoundSql;
import com.jayus.smallMyBatis.step07.mapping.MappedStatement;
import com.jayus.smallMyBatis.step07.session.Configuration;
import com.jayus.smallMyBatis.step07.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseStatementHandler implements StatementHandler {

    protected final Configuration configuration;

    protected final Executor executor;

    protected final MappedStatement mappedStatement;

    protected final Object parameterObject;

    protected final ResultSetHandler resultSetHandler;

    protected BoundSql boundSql;

    public BaseStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject, ResultHandler resultHandler, BoundSql boundSql) {
        this.configuration = mappedStatement.getConfiguration();
        this.executor = executor;
        this.mappedStatement = mappedStatement;
        this.parameterObject = parameterObject;
        this.resultSetHandler = configuration.newResultSetHandler(executor,mappedStatement,boundSql);
        this.boundSql = boundSql;
    }

    @Override
    public Statement prepare(Connection connection) throws SQLException {
        Statement statement = null;
        try {
            statement = instantiateStatement(connection);
            statement.setQueryTimeout(350);
            statement.setFetchSize(1000);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    protected abstract Statement instantiateStatement(Connection connection) throws SQLException;
}
