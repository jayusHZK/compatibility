package com.jayus.smallSpring.step18.jdbc.datasource;

import com.jayus.smallSpring.step18.jdbc.CannotGetJdbcConnectionException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DataSourceUtils {

    public static Connection getConnection(DataSource dataSource){
        try {
            return doGetConnection(dataSource);
        } catch (SQLException e) {
            throw new CannotGetJdbcConnectionException("Failed to obtain JDBC Connection", e);
        }
    }

    public static Connection doGetConnection(DataSource dataSource) throws SQLException {
        Connection connection = fetchConnection(dataSource);
        ConnectionHolder holderToUse = new ConnectionHolder(connection);
        return connection;
    }

    private static Connection fetchConnection(DataSource dataSource) throws SQLException {
        Connection conn = dataSource.getConnection();
        if (conn == null){
            throw new IllegalArgumentException("DataSource return null from getConnection():" + dataSource);
        }
        return conn;
    }

    public static void releaseConnection(Connection con,DataSource dataSource) {
        try {
            doReleaseConnection(con,dataSource);
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        } catch (Throwable e) {
            //throw new RuntimeException(e);
        }
    }

    public static void doReleaseConnection(Connection con,DataSource dataSource) throws SQLException {
        if (con == null){
            return;
        }
        doCloseConnection(con,dataSource);
    }

    public static void doCloseConnection(Connection con,DataSource dataSource) throws SQLException {
        con.close();
    }

}
