package com.jayus.smallMyBatis.step13.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName Transaction
 * @Description: 事务接口
 * @date: 2024/10/12 15:55
 */
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit()throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;

}
