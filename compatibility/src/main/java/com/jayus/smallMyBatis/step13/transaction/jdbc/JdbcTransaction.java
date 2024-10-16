package com.jayus.smallMyBatis.step13.transaction.jdbc;

import com.jayus.smallMyBatis.step13.session.TransactionIsolationLevel;
import com.jayus.smallMyBatis.step13.transaction.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName JdbcTransaction
 * @Description: JDBC 事务，直接利用JDBC的commit、rollback。依赖于数据源获得的连接来管理事务范围。
 * @date: 2024/10/12 15:58
 */
public class JdbcTransaction implements Transaction {

    protected Connection connection;
    protected DataSource dataSource;

    protected TransactionIsolationLevel level = TransactionIsolationLevel.NONE;

    protected boolean autoCommit;

    public JdbcTransaction(DataSource dataSource,TransactionIsolationLevel level,boolean autoCommit ) {
        this.dataSource = dataSource;
        this.level = level;
        this.autoCommit = autoCommit;
    }

    public JdbcTransaction(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        connection = dataSource.getConnection();
        connection.setTransactionIsolation(level.getLevel());
        connection.setAutoCommit(autoCommit);
        return connection;
    }

    @Override
    public void commit() throws SQLException {
        if (connection != null && !connection.getAutoCommit()){
            connection.commit();
        }
    }

    @Override
    public void rollback() throws SQLException {
        if (connection!= null && !connection.getAutoCommit()){
            connection.rollback();
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null && !connection.getAutoCommit()){
            connection.close();
        }
    }
}
