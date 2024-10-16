package com.jayus.smallMyBatis.step13.executor.statement;

import com.jayus.smallMyBatis.step13.executor.Executor;
import com.jayus.smallMyBatis.step13.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step13.executor.resultset.ResultSetHandler;
import com.jayus.smallMyBatis.step13.mapping.BoundSql;
import com.jayus.smallMyBatis.step13.mapping.MappedStatement;
import com.jayus.smallMyBatis.step13.session.Configuration;
import com.jayus.smallMyBatis.step13.session.ResultHandler;
import com.jayus.smallMyBatis.step13.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName BaseStatementHandler
 * @Description: 语句处理器抽象基类
 * @date: 2024/10/14 10:55
 */
public abstract class BaseStatementHandler implements StatementHandler {

    protected final Configuration configuration;
    protected final Executor executor;

    protected final MappedStatement mappedStatement;

    protected final Object parameterObject;

    protected final ResultSetHandler resultSetHandler;

    protected final ParameterHandler parameterHandler;

    protected final RowBounds rowBounds;
    protected BoundSql boundSql;

    public BaseStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject
            , RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        this.configuration = mappedStatement.getConfiguraction();
        this.executor = executor;
        this.mappedStatement = mappedStatement;
        this.rowBounds = rowBounds;
        if (boundSql == null) {
            boundSql = mappedStatement.getBoundSql(parameterObject);
        }
        this.boundSql = boundSql;
        this.parameterObject = parameterObject;
        this.parameterHandler = configuration.newParameterHandler(mappedStatement,parameterObject,boundSql);
        this.resultSetHandler = configuration.newResultHandler(executor,mappedStatement,rowBounds,resultHandler,boundSql);
    }

    @Override
    public Statement prepare(Connection connection) throws SQLException {
        Statement statement = null;
        try {
            statement = instantiateStatement(connection);
            statement.setQueryTimeout(350);
            statement.setFetchSize(10000);
            return statement;
        } catch (SQLException e) {
            throw new RuntimeException("Error preparing statement.  Cause: " + e, e);
        }
    }

    protected abstract Statement instantiateStatement(Connection connection) throws SQLException;

}
