package com.jayus.smallMyBatis.step04.transaction.jdbc;

import com.jayus.smallMyBatis.step04.transaction.Transaction;
import org.apache.ibatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * JDBC 事务实现类
 */
public class JdbcTransaction implements Transaction {

    protected Connection connection;

    private DataSource dataSource;

    // 事务隔离级别
    private TransactionIsolationLevel level = TransactionIsolationLevel.NONE;

    protected boolean autoCommit;

    public JdbcTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        this.dataSource = dataSource;
        this.level = level;
        this.autoCommit = autoCommit;
    }

    public JdbcTransaction(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null){
            connection = dataSource.getConnection();
            connection.setTransactionIsolation(level.getLevel());
            connection.setAutoCommit(autoCommit);
        }
        return connection;
    }

    @Override
    public void commit() throws SQLException {
        if (connection != null &&!connection.getAutoCommit()){
            connection.commit();
        }
    }

    @Override
    public void rollback() throws SQLException {
        if (connection != null &&!connection.getAutoCommit()){
            connection.rollback();
        }
    }

    @Override
    public void close() throws SQLException {
        if(connection != null && !connection.getAutoCommit()){
            connection.close();
        }
    }
}
