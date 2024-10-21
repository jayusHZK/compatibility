package com.jayus.smallMyBatis.step19.executor.statement;

import com.jayus.smallMyBatis.step19.executor.Executor;
import com.jayus.smallMyBatis.step19.mapping.BoundSql;
import com.jayus.smallMyBatis.step19.mapping.MappedStatement;
import com.jayus.smallMyBatis.step19.session.ResultHandler;
import com.jayus.smallMyBatis.step19.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @ClassName SimpleStatementHandler
 * @Description: 简单语句处理器
 * @date: 2024/10/18 23:39
 */
public class SimpleStatementHandler extends BaseStatementHandler{

    public SimpleStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject, RowBounds rowBounds
            ,ResultHandler ResultHandler, BoundSql boundSql) {
        super(executor, mappedStatement, parameterObject, rowBounds,ResultHandler, boundSql);
    }

    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    @Override
    public void parametreize(Statement statement) throws SQLException {

    }

    @Override
    public int update(Statement statement) throws SQLException {
        String sql = boundSql.getSql();
        statement.execute(sql);
        return statement.getUpdateCount();
    }

    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        String sql = boundSql.getSql();
        statement.execute(sql);
        return resultSetHandler.handleResultSets(statement);
    }

}
