package com.jayus.smallMyBatis.step08.executor.statement;

import com.jayus.smallMyBatis.step08.executor.Executor;
import com.jayus.smallMyBatis.step08.executor.resultset.ResultSetHandler;
import com.jayus.smallMyBatis.step08.mapping.BoundSql;
import com.jayus.smallMyBatis.step08.mapping.MappedStatement;
import com.jayus.smallMyBatis.step08.session.Configuration;
import com.jayus.smallMyBatis.step08.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 语句处理器抽象基类
 */
public abstract class BaseStatementHandler implements StatementHandler{

    protected final Configuration configuration;

    protected final Executor executor;

    protected final MappedStatement mappedStatement;

    protected final Object parameterObject;

    protected final ResultSetHandler resultSetHandler;

    protected BoundSql boundSql;

    public BaseStatementHandler(Executor executor, MappedStatement mappedStatement,
                                Object parameterObject, ResultHandler resultHandler, BoundSql boundSql) {
        this.configuration = mappedStatement.getConfiguration();
        this.executor = executor;
        this.mappedStatement = mappedStatement;
        this.parameterObject = parameterObject;
        this.boundSql = boundSql;
        this.resultSetHandler = configuration.newResultSetHandler(executor,mappedStatement,boundSql);
    }

    @Override
    public Statement prepare(Connection connection) throws SQLException {
        Statement statement = null;
        try {
            statement = instantiateStatement(connection);
            // 参数设置 可以被抽取 提供配置
            statement.setQueryTimeout(350);
            statement.setFetchSize(10000);
            return statement;
        } catch (SQLException e) {
            throw new RuntimeException("Error preparing statement.  Cause: " + e, e);
        }
    }

    protected abstract Statement instantiateStatement(Connection connection) throws SQLException;
}
