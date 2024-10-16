package com.jayus.smallMyBatis.step12.executor.statement;

import com.jayus.smallMyBatis.step12.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @ClassName StatementHandler
 * @Description: 语句处理器
 * @date: 2024/9/25 10:34
 */
public interface StatementHandler {

    /*
    准备语句
     */
    Statement prepare(Connection connection) throws SQLException;

    /*
    参数化
     */
    void parameterize(Statement statement) throws SQLException;

    /*
    执行更新
     */
    int update(Statement statement) throws SQLException;

    /*
    执行查询
     */
    <E>List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException;

}