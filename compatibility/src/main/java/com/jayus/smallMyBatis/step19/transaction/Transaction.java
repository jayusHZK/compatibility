package com.jayus.smallMyBatis.step19.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName Transaction
 * @Description: 事务接口
 * @date: 2024/10/16 21:16
 */
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;

}
