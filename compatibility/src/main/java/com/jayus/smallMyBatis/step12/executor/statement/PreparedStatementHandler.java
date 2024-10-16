package com.jayus.smallMyBatis.step12.executor.statement;

import com.jayus.smallMyBatis.step12.executor.Executor;
import com.jayus.smallMyBatis.step12.mapping.BoundSql;
import com.jayus.smallMyBatis.step12.mapping.MappedStatement;
import com.jayus.smallMyBatis.step12.session.ResultHandler;
import com.jayus.smallMyBatis.step12.session.RowBounds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @ClassName PreparedStatementHandler
 * @Description: 预处理语句处理器
 * @date: 2024/9/26 00:45
 */
public class PreparedStatementHandler extends BaseStatementHandler {

    public PreparedStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject
            , RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        super(executor, mappedStatement, parameterObject, rowBounds, resultHandler, boundSql);
    }

    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        String sql = boundSql.getSql();
        return connection.prepareStatement(sql);
    }

    @Override
    public void parameterize(Statement statement) throws SQLException {
        parameterHandler.setParameters((PreparedStatement) statement);
    }

    @Override
    public int update(Statement statement) throws SQLException {
        PreparedStatement ps = (PreparedStatement) statement;
        ps.execute();
        return ps.getUpdateCount();
    }

    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        PreparedStatement ps = (PreparedStatement) statement;
        ps.execute();
        return resultSetHandler.<E>handleResultSets(ps);
    }

}
