package com.jayus.smallMyBatis.step12.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName Transaction
 * @Description: 事务接口
 * @date: 2024/9/19 02:00
 */
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;

}
