package com.jayus.smallMyBatis.step11.executor.statement;

import com.jayus.smallMyBatis.step11.executor.Executor;
import com.jayus.smallMyBatis.step11.mapping.BoundSql;
import com.jayus.smallMyBatis.step11.mapping.MappedStatement;
import com.jayus.smallMyBatis.step11.session.ResultHandler;
import com.jayus.smallMyBatis.step11.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @ClassName SimpleStatementHandler
 * @Description: 简单语句处理器
 * @date: 2024/9/18 21:04
 */
public class SimpleStatementHandler extends BaseStatementHandler {

    public SimpleStatementHandler(Executor executor, MappedStatement mappedStatement
            , Object parameterObject, RowBounds rowBounds, ResultHandler resultHandler
            , BoundSql boundSql) {
        super(executor, mappedStatement, parameterObject, rowBounds, resultHandler, boundSql);
    }

    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
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
