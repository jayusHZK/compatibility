package com.jayus.smallMyBatis.step04.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public interface Transaction {

    /**
     * 获取数据库连接对象
     * @return
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;

}
