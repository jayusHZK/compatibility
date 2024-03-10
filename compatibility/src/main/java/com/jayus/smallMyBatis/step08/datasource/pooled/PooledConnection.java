package com.jayus.smallMyBatis.step08.datasource.pooled;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class PooledConnection implements InvocationHandler {

    // 关闭方法
    private static final String CLOSE = "close";

    // 被代理的接口
    private static final Class<?>[] IFACES = new Class<?>[]{Connection.class};

    private int hashCode = 0;

    // 池化数据源
    private PooledDataSource dataSource;

    // 真实的连接
    private Connection realConnection;

    // 代理的连接
    private Connection proxyConnection;

    // 检查时间
    private long checkoutTimestamp;

    // 创建时间 毫秒
    private long createdTimestamp;

    // 最后使用时间
    private long lastUsedTimestamp;

    // 连接的 hashcode
    private int connectionTypeCode;

    // 是否有效
    private boolean valid;

    public PooledConnection(Connection connection, PooledDataSource dataSource){
        this.hashCode = connection.hashCode();
        this.realConnection = connection;
        this.dataSource = dataSource;
        this.createdTimestamp = System.currentTimeMillis();
        this.lastUsedTimestamp = System.currentTimeMillis();
        this.valid = true;
        // 实例化代理 被代理的接口是 Connection
        this.proxyConnection = (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(),IFACES,this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        // 如果调用的是 close 关闭连接的方法 就把连接归还到连接池中 并返回 null
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
        if (!valid){
            throw new SQLException("Error accessing PooledConnection. Connection is invalid.");
        }
    }

    public void invalidate(){
        valid = false;
    }

    public boolean isValid(){
        // 是否有效需要满足三个条件
        // 1、valid 的值是 true
        // 2、真实连接 realConnection 不为 null
        // 3、调用侦测连接的方法 返回 true 表示连接有效 没有假死
        return valid &&realConnection != null && dataSource.pingConnection(this);
    }

    public Connection getRealConnection() {
        return realConnection;
    }

    public Connection getProxyConnection() {
        return proxyConnection;
    }

    public int getRealHashCode(){
        return realConnection == null?0:realConnection.hashCode();
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

    public int getConnectionTypeCode() {
        return connectionTypeCode;
    }

    public void setConnectionTypeCode(int connectionTypeCode) {
        this.connectionTypeCode = connectionTypeCode;
    }

    public long getTimeElapsedSingceLastUse(){
        return System.currentTimeMillis() - lastUsedTimestamp;
    }

    public long getAge(){
        return System.currentTimeMillis() - createdTimestamp;
    }

    public long getCheckoutTimestamp(){
        return checkoutTimestamp;
    }

    public void setCheckoutTimestamp(long checkoutTimestamp) {
        this.checkoutTimestamp = checkoutTimestamp;
    }

    public long getCheckoutTime(){
        return System.currentTimeMillis() - checkoutTimestamp;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PooledConnection){
            return realConnection.hashCode() == (((PooledConnection) obj).realConnection.hashCode());
        } else if (obj instanceof Connection){
            return hashCode == obj.hashCode();
        }
        return false;
    }
}
