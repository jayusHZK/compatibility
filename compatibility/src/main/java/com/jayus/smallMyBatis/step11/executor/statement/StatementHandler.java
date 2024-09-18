package com.jayus.smallMyBatis.step11.executor.statement;

import com.jayus.smallMyBatis.step11.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @ClassName StatementHandler
 * @Description: 语句处理器
 * @date: 2024/9/18 03:28
 */
public interface StatementHandler {

    /**
     * 准备语句
     * @param connection
     * @return
     * @throws SQLException
     */
    Statement prepare(Connection connection) throws SQLException;

    // 参数化
    void parameterize(Statement statement) throws SQLException;

    // 执行查询
    <E>List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException;

}
