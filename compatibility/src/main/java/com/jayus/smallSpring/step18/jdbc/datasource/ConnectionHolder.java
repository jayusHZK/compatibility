package com.jayus.smallSpring.step18.jdbc.datasource;

import cn.hutool.core.lang.Assert;

import java.sql.Connection;

public class ConnectionHolder {

    private ConnectionHandler connectionHandler;

    private Connection currentConnection;

    public ConnectionHolder(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public ConnectionHolder(Connection currentConnection) {
        this.currentConnection = currentConnection;
    }

    public ConnectionHandler getConnectionHandler() {
        return connectionHandler;
    }

    protected boolean hasConnection() {
        return this.connectionHandler != null;
    }

    public void setCurrentConnection(Connection connection) {
        if (null != this.currentConnection){
            if (null != connectionHandler){
                this.connectionHandler.releaseConnection(this.currentConnection);
            }
            this.currentConnection = null;
        }
        if (null != connection){
            this.connectionHandler = new SimpleConnectionHandler(connection);
        } else {
            this.connectionHandler = null;
        }
    }

    public Connection getCurrentConnection() {
        Assert.notNull(this.connectionHandler, "Active connection is required.");
        if (null == this.currentConnection){
            this.currentConnection = this.connectionHandler.getConnection();
        }
        return currentConnection;
    }
}
