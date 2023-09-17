package com.jayus.smallMyBatis.step06.executor.statement;

import com.jayus.smallMyBatis.step06.executor.Executor;
import com.jayus.smallMyBatis.step06.executor.resultset.ResultSetHandler;
import com.jayus.smallMyBatis.step06.mapping.BoundSql;
import com.jayus.smallMyBatis.step06.mapping.MappedStatement;
import com.jayus.smallMyBatis.step06.session.Configuration;
import com.jayus.smallMyBatis.step06.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 语句处理器抽象基类
 */
public abstract class BaseStatementHandler implements StatementHandler{

    private final Configuration configuration;

    protected final Executor executor;

    protected final MappedStatement mappedStatement;

    protected final Object parameterObject;

    protected final ResultSetHandler resultSetHandler;

    protected BoundSql boundSql;

    public BaseStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject, ResultHandler resultHandler, BoundSql boundSql) {
        this.executor = executor;
        this.configuration = mappedStatement.getConfiguration();
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
            // 设置查询超时时间
            statement.setQueryTimeout(350);
            // 设置每次获取的记录数
            statement.setFetchSize(1000);;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    /**
     * 初始化 Statement 对象
     * @param connection
     * @return
     * @throws SQLException
     */
    protected abstract Statement instantiateStatement(Connection connection) throws SQLException;
}
