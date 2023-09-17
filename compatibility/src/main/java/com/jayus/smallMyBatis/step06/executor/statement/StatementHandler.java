package com.jayus.smallMyBatis.step06.executor.statement;

import com.jayus.smallMyBatis.step06.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 语句处理器
 */
public interface StatementHandler {

    /**
     * 准备语句
     * @param connection
     * @return
     * @throws SQLException
     */
    Statement prepare(Connection connection) throws SQLException;

    /**
     * 参数化
     * @param statement
     * @throws SQLException
     */
    void parameterize(Statement statement) throws SQLException;

    /**
     * 执行查询
     * @param statement
     * @param resultHandler
     * @return
     * @param <E>
     * @throws SQLException
     */
    <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException;


}
