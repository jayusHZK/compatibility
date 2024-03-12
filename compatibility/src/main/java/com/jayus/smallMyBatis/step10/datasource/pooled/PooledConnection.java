package com.jayus.smallMyBatis.step10.datasource.pooled;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * 池化代理的链接
 */
public class PooledConnection implements InvocationHandler {

    private static final String CLOSE = "close";

    private static final Class<?>[] IFACES = new Class<?>[]{Connection.class};

    private int hashCode = 0;

    private PooledDataSource dataSource;

    // 真实链接
    private Connection realConnection;

    // 代理的链接
    private Connection proxyConnection;

    private long checkoutTimestamp;

    private long createdTimestamp;

    private long lastUsedTimestamp;

    private int connectionTypeCode;

    private boolean valid;

    public PooledConnection(Connection connection, PooledDataSource dataSource){
        this.hashCode = connection.hashCode();
        this.realConnection = connection;
        this.dataSource = dataSource;
        this.createdTimestamp = System.currentTimeMillis();
        this.lastUsedTimestamp = System.currentTimeMillis();
        this.valid = true;
        this.proxyConnection = (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(),
                IFACES,this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        // 如果调用的是 CLOSE 方法，则将链接放入连接池中 并返回null
        if (CLOSE.hashCode() == methodName.hashCode() && CLOSE.equals(methodName)){
            dataSource.pushConnection(this);
            return null;
        } else {
            if (!Object.class.equals(method.getDeclaringClass())){
                checkConnection();
            }
        }
        return method.invoke(realConnection,args);
    }

    private void checkConnection() throws SQLException {
        if (!valid) {
            throw new SQLException("Error accessing PooledConnection. Connection is invalid.");
        }
    }

    public void invalidate(){
        valid = false;
    }

    public boolean isValid(){
        return valid && realConnection != null && dataSource.pingc
    }

    public Connection getRealConnection(){
        return realConnection;
    }

    public Connection getProxyConnection(){
        return proxyConnection;
    }

    public int getRealHashCode(){
        return realConnection == null?0:realConnection.hashCode();
    }

    public int getConnectionTypeCode() {
        return connectionTypeCode;
    }

    public void setConnectionTypeCode(int connectionTypeCode) {
        this.connectionTypeCode = connectionTypeCode;
    }

    public long getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public long getLastUsedTimestamp() {
        return lastUsedTimestamp;
    }

    public void setLastUsedTimestamp(long lastUsedTimestamp) {
        this.lastUsedTimestamp = lastUsedTimestamp;
    }

    public long getTimeElapsedSinceLastUse(){
        return System.currentTimeMillis() - lastUsedTimestamp;
    }

    public long getAge(){
        return System.currentTimeMillis() - createdTimestamp;
    }

    public long getCheckoutTimestamp() {
        return checkoutTimestamp;
    }

    public void setCheckoutTimestamp(long checkoutTimestamp) {
        this.checkoutTimestamp = checkoutTimestamp;
    }

    public long getCheckTime(){
        return System.currentTimeMillis() - checkoutTimestamp;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PooledConnection){
            return realConnection.hashCode() == ((PooledConnection)o).realConnection.hashCode();
        } else if (o instanceof Connection){
            return hashCode == o.hashCode();
        } else {
            return false;
        }
    }
}
