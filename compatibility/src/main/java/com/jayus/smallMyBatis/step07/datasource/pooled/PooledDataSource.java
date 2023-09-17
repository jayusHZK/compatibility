package com.jayus.smallMyBatis.step07.datasource.pooled;

import com.jayus.smallMyBatis.step07.datasource.unpooled.UnpooledDataSource;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Logger;

/**
 * 池化数据源
 */
public class PooledDataSource implements DataSource {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(PooledDataSource.class);

    private final PoolState state = new PoolState(this);

    // 未池化的数据源
    private final UnpooledDataSource dataSource;

    // 活跃连接数
    protected int poolMaximumActiveConnection = 10;

    // 空闲连接数
    protected int poolMaximumIdleConnections = 5;

    // 在被强制返回之前 池中连接被检查的时间(毫秒)
    protected int poolMaximumCheckoutTime = 20000;

    // 这是给连接池一个打印日志状态机会的低层次设置,还有重新尝试获得连接, 这些情况往往需要很长时间 为了避免连接池没有配置时静默失败。（毫秒）
    protected int poolTimeToWait = 20000;

    // 发送到数据的侦测查询,用来验证连接是否正常工作,并且准备 接受请求。默认是“NO PING QUERY SET” ,这会引起许多数据库驱动连接由一个错误信息而导致失败
    protected String poolPingQuery = "NO PING QUERY SET";

    // 开启或禁用侦测查询
    protected boolean poolPingEnabled = false;

    // 用来配置 poolPingQuery用于侦听多久的连接，设置为0，表示会侦听所有的连接
    protected int poolPingConnectionNotUsedFor = 0;

    // 期望的连接类型编码
    private int expectedConnectionTypeCode;

    public PooledDataSource() {
        this.dataSource = new UnpooledDataSource();
    }

    /**
     * 连接池回收连接对象
     *
     * @param connection
     */
    protected void pushConnection(PooledConnection connection) throws SQLException {
        synchronized (state) {
            // 从活跃连接移除该连接
            state.activeConnections.remove(connection);
            // 判断连接是否有效
            if (connection.isValid()) {
                // 判断空闲连接数是否小于最大空闲连接数 以及连接类型是否匹配
                if (state.idleConnections.size() < poolMaximumIdleConnections && connection.getConnectionTypeCode() == expectedConnectionTypeCode) {
                    // 累计连接使用时间
                    state.accumulatedCheckoutTime += connection.getCheckoutTime();
                    // 判断连接是否自动提交 如果不是则回滚
                    // 在 MyBatis 中 如果没有开启自动提交模式 则需要手动提交或者回滚事务。 因此 这段代码是在确保操作完成后 如果没有开启自动提交模式 则执行回滚操作
                    if (!connection.getRealConnection().getAutoCommit()) {
                        connection.getRealConnection().rollback();
                    }
                    // 实例化一个新的 DB 连接对象 并将其添加到空闲连接集合中
                    PooledConnection newConnection = new PooledConnection(connection.getRealConnection(), this);
                    state.idleConnections.add(newConnection);
                    newConnection.setCreatedTimestamp(connection.getCreatedTimestamp());
                    newConnection.setLastUsedTimestamp(connection.getLastUsedTimestamp());
                    // 将原连接设置为无效
                    connection.invalidate();
                    logger.info("Returned connection " + newConnection.getRealHashCode() + " to pool.");
                    // 通知其他线程可以过来抢 DB 连接了
                    state.notifyAll();
                } else {
                    // 如果空闲连接数大于最大空闲连接数或连接类型不匹配
                    state.accumulatedCheckoutTime += connection.getCheckoutTime();
                    if (!connection.getRealConnection().getAutoCommit()) {
                        connection.getRealConnection().rollback();
                    }
                    connection.getRealConnection().close();
                    logger.info("Closed connection " + connection.getRealHashCode() + ".");
                    connection.invalidate();
                }
            } else {
                logger.info("A bad connection (" + connection.getRealHashCode() + ") attempted to return to the pool, discarding connection.");
                // 坏连接数 +1
                state.badConnectionCount++;
            }
        }
    }

    /**
     * 获取连接对象
     *
     * @param username
     * @param password
     * @return
     */
    private PooledConnection popConnection(String username, String password) throws SQLException {
        boolean countedWait = false;
        PooledConnection conn = null;
        long t = System.currentTimeMillis();
        int localBadConnectionCount = 0;

        while (conn == null) {
            synchronized (state) {
                // 如果有空闲连接 则直接从空闲连接中获取一个
                if (!state.idleConnections.isEmpty()) {
                    conn = state.idleConnections.remove(0);
                    logger.info("Checked out connection " + conn.getRealHashCode() + " from pool.");
                } else {
                    // 如果没有空闲连接 则判断活跃连接数是否大于最大活跃连接数
                    if (state.activeConnections.size() < poolMaximumActiveConnection) {
                        conn = new PooledConnection(dataSource.getConnection(), this);
                        logger.info("Created connection " + conn.getRealHashCode() + ".");
                    } else {
                        // 如果大于 则获取活跃连接中的第一个连接对象 也就是最老的一个连接对象
                        PooledConnection oldestActiveConnection = state.activeConnections.get(0);
                        long longestCheckoutTime = oldestActiveConnection.getCheckoutTime();
                        // 判断最老的连接是否超时 如果超时 则将其从活跃连接集合中移除 并将其设置为无效
                        if (longestCheckoutTime > poolMaximumCheckoutTime) {
                            state.claimedOverdueConnectionCount++;
                            state.accumulatedCheckoutTimeOfOverdueConnections += longestCheckoutTime;
                            state.accumulatedCheckoutTime += longestCheckoutTime;
                            state.activeConnections.remove(oldestActiveConnection);
                            // 如果连接不是自动提交 则回滚
                            if (!oldestActiveConnection.getRealConnection().getAutoCommit()) {
                                oldestActiveConnection.getRealConnection().rollback();
                            }
                            // 创建新的连接对象
                            conn = new PooledConnection(oldestActiveConnection.getRealConnection(), this);
                            conn.setCreatedTimestamp(oldestActiveConnection.getCreatedTimestamp());
                            conn.setLastUsedTimestamp(oldestActiveConnection.getLastUsedTimestamp());
                            // 创建一个新的连接对象
                            oldestActiveConnection.invalidate();
                            logger.info("Claimed overdue connection " + conn.getRealHashCode() + ".");
                        } else {
                            // 如果最老的连接没有超时 则等待指定时间 然后重新尝试获取连接
                            try {
                                if (!countedWait) {
                                    state.hadToWaitCount++;
                                    countedWait = true;
                                }
                                logger.info("Waiting as long as " + poolTimeToWait + " milliseconds for connection.");
                                long wt = System.currentTimeMillis();
                                // 等待指定时间
                                state.wait(poolTimeToWait);
                                state.accumulatedWaitTime += System.currentTimeMillis() - wt;
                            } catch (InterruptedException e) {
                                break;
                            }
                        }
                    }
                    // 如果获取到连接对象
                    if (conn != null) {
                        if (conn.isValid()) {
                            if (!conn.getRealConnection().getAutoCommit()) {
                                conn.getRealConnection().rollback();
                            }
                            // 设置连接对象的相关属性
                            conn.setConnectionTypeCode(assembleConnectionTypeCode(dataSource.getUrl(), username, password));
                            conn.setCheckoutTimestamp(System.currentTimeMillis());
                            conn.setLastUsedTimestamp(System.currentTimeMillis());
                            // 将连接添加到活跃集合中
                            state.activeConnections.add(conn);
                            state.requestCount++;
                            state.accumulatedRequestTime += System.currentTimeMillis() - t;
                        } else {
                            // 如果连接对象无效 则坏连接数 +1
                            logger.info("A bad connection (" + conn.getRealHashCode() + ") was returned from the pool, getting another connection.");
                            state.badConnectionCount++;
                            localBadConnectionCount++;
                            conn = null;
                            // 如果坏连接数大于最大坏连接数 则抛出异常
                            if (localBadConnectionCount > (poolMaximumIdleConnections + 3)){
                                logger.info("PooledDataSource: Could not get a good connection to the database.");
                                throw new SQLException("PooledDataSource: Could not get a good connection to the database.");
                            }
                        }
                    }
                }
            }
        }
        // 如果获取不到连接对象 则抛出异常
        if (conn == null){
            logger.info("PooledDataSource: Unknown severe error condition.  The connection pool returned a null connection.");
            throw new SQLException("PooledDataSource: Unknown severe error condition.  The connection pool returned a null connection.");
        }
        return conn;
    }

    /**
     * 组装连接类型编码
     *
     * @param url
     * @param username
     * @param password
     * @return
     */
    private int assembleConnectionTypeCode(String url, String username, String password) {
        return ("" + url + username + password).hashCode();
    }

    /**
     * 关闭所有活跃和空闲的连接
     */
    public void forceCloseAll(){
        synchronized (state){
            // 计算连接类型编码
            expectedConnectionTypeCode = assembleConnectionTypeCode(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
            // 遍历活跃连接集合 将其设置为无效 并从活跃连接中移除
            for (int i = state.activeConnections.size(); i >0 ; i--) {
                try {
                    PooledConnection conn = state.activeConnections.remove(i - 1);
                    conn.invalidate();
                    Connection realConn = conn.getRealConnection();
                    if (!realConn.getAutoCommit()){
                        realConn.rollback();
                    }
                    realConn.close();
                } catch (SQLException e) {

                }
            }
            //关闭空闲连接集合中的连接
            for(int i=state.idleConnections.size();i>0;i--){
                try {
                    PooledConnection conn = state.idleConnections.remove(i - 1);
                    conn.invalidate();
                    Connection realConn = conn.getRealConnection();
                    if(!realConn.getAutoCommit()){
                        realConn.rollback();
                    }
                    realConn.close();
                } catch (Exception e) {
                    // ignore
                }
            }
            logger.info("PooledDataSource forcefully closed/removed all connections.");
        }
    }

    /**
     * 测试指定连接池的连接是否全部已经关闭
     * @param conn
     * @return
     */
    protected boolean pingConnection(PooledConnection conn){
        boolean result = true;
        try {
            result = !conn.getRealConnection().isClosed();
        } catch (Exception e) {
            logger.info("Connection " + conn.getRealHashCode() + " is BAD: " + e.getMessage());
            result = false;
        }

        // 如果真实连接没有关闭 则尝试执行一个查询语句
        if (result){
            if (poolPingEnabled){
                // 如果配置了 poolPingQuery 属性 则执行该查询语句 条件是 poolPingConnectionNotUsedFor 大于 0 并且连接对象的空闲时间大于 poolPingConnectionNotUsedFor
                // 如果 poolPingConnectionNotUsedFor 比较大的话 则可以避免频繁的执行侦测语句 只侦听空闲时间比较长的连接对象
                if (poolPingConnectionNotUsedFor >= 0 &&conn.getTimeElapsedSingceLastUse() > poolPingConnectionNotUsedFor){
                    try {
                        logger.info("Testing connection " + conn.getRealHashCode() + " ...");
                        Connection realConn = conn.getRealConnection();
                        Statement statement = realConn.createStatement();
                        ResultSet resultSet = statement.executeQuery(poolPingQuery);
                        resultSet.close();
                        if (!realConn.getAutoCommit()){
                            realConn.rollback();
                        }
                        result = true;
                        logger.info("Connection " + conn.getRealHashCode() + " is GOOD!");
                    } catch (SQLException e) {
                        //如果是无效的连接，则关闭真实连接，并返回false
                        logger.info("Connection " + conn.getRealHashCode() + " is BAD: " + e.getMessage());
                        try {
                            conn.getRealConnection().close();
                        } catch (SQLException ex) {

                        }
                        result =false;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return popConnection(dataSource.getUsername(), dataSource.getPassword()).getProxyConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return popConnection(username, password).getProxyConnection();
    }

    @Override
    protected void finalize() throws Throwable {
        forceCloseAll();
        super.finalize();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLException(getClass().getName() + " is not a wrapper.");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return DriverManager.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter logWriter) throws SQLException {
        DriverManager.setLogWriter(logWriter);
    }

    @Override
    public void setLoginTimeout(int loginTimeout) throws SQLException {
        DriverManager.setLoginTimeout(loginTimeout);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return DriverManager.getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    public void setDriver(String driver){
        dataSource.setDriver(driver);
        forceCloseAll();
    }

    public void setUrl(String url){
        dataSource.setUrl(url);
        forceCloseAll();
    }

    public void setUsername(String username){
        dataSource.setUsername(username);
        forceCloseAll();
    }

    public void setPassword(String password){
        dataSource.setPassword(password);
        forceCloseAll();
    }

    public void setDefaultAutoCommit(boolean defaultAutoCommit){
        dataSource.setAutoCommit(defaultAutoCommit);
        forceCloseAll();
    }

    public int getPoolMaximumActiveConnection() {
        return poolMaximumActiveConnection;
    }

    public void setPoolMaximumActiveConnection(int poolMaximumActiveConnection) {
        this.poolMaximumActiveConnection = poolMaximumActiveConnection;
    }

    public int getPoolMaximumIdleConnections() {
        return poolMaximumIdleConnections;
    }

    public void setPoolMaximumIdleConnections(int poolMaximumIdleConnections) {
        this.poolMaximumIdleConnections = poolMaximumIdleConnections;
    }

    public int getPoolMaximumCheckoutTime() {
        return poolMaximumCheckoutTime;
    }

    public void setPoolMaximumCheckoutTime(int poolMaximumCheckoutTime) {
        this.poolMaximumCheckoutTime = poolMaximumCheckoutTime;
    }

    public int getPoolTimeToWait() {
        return poolTimeToWait;
    }

    public void setPoolTimeToWait(int poolTimeToWait) {
        this.poolTimeToWait = poolTimeToWait;
    }

    public String getPoolPingQuery() {
        return poolPingQuery;
    }

    public void setPoolPingQuery(String poolPingQuery) {
        this.poolPingQuery = poolPingQuery;
    }

    public boolean isPoolPingEnabled() {
        return poolPingEnabled;
    }

    public void setPoolPingEnabled(boolean poolPingEnabled) {
        this.poolPingEnabled = poolPingEnabled;
    }

    public int getPoolPingConnectionNotUsedFor() {
        return poolPingConnectionNotUsedFor;
    }

    public void setPoolPingConnectionNotUsedFor(int poolPingConnectionNotUsedFor) {
        this.poolPingConnectionNotUsedFor = poolPingConnectionNotUsedFor;
    }

    public int getExpectedConnectionTypeCode() {
        return expectedConnectionTypeCode;
    }

    public void setExpectedConnectionTypeCode(int expectedConnectionTypeCode) {
        this.expectedConnectionTypeCode = expectedConnectionTypeCode;
    }
}
