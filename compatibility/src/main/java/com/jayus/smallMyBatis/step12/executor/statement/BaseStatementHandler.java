package com.jayus.smallMyBatis.step12.executor.statement;

import com.jayus.smallMyBatis.step12.executor.Executor;
import com.jayus.smallMyBatis.step12.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step12.executor.resultset.ResultSetHandler;
import com.jayus.smallMyBatis.step12.mapping.BoundSql;
import com.jayus.smallMyBatis.step12.mapping.MappedStatement;
import com.jayus.smallMyBatis.step12.session.Configuration;
import com.jayus.smallMyBatis.step12.session.ResultHandler;
import com.jayus.smallMyBatis.step12.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName BaseStatementHandler
 * @Description: 语句处理器抽象基类
 * @date: 2024/9/25 10:37
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
        this.configuration=mappedStatement.getConfiguration();
        this.executor = executor;
        this.mappedStatement = mappedStatement;
        this.rowBounds = rowBounds;
        if (boundSql == null) {
            boundSql = mappedStatement.getBoundSql(parameterObject);
        }
        this.boundSql = boundSql;
        this.parameterObject = parameterObject;
        this.parameterHandler = configuration.newParameterHandler(mappedStatement,parameterObject,boundSql);
        this.resultSetHandler = configuration.newResultSetHandler(executor,mappedStatement,rowBounds,resultHandler,boundSql);

    }

    @Override
    public Statement prepare(Connection connection) throws SQLException {
        Statement statement = null;
        try {
            // 实例化 Statement
            statement = instantiateStatement(connection);
            // 参数设置，可以被抽取，提供配置
            statement.setQueryTimeout(350);
            statement.setFetchSize(10000);
            return statement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract Statement instantiateStatement(Connection connection) throws SQLException;

}
