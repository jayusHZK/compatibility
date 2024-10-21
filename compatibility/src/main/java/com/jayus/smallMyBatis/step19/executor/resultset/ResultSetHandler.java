package com.jayus.smallMyBatis.step19.executor.resultset;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @ClassName ResultSetHandler
 * @Description: 结果集处理器
 * @date: 2024/10/18 19:42
 */
public interface ResultSetHandler {

    <E> List<E> handleResultSets(Statement stmt) throws SQLException;

}
