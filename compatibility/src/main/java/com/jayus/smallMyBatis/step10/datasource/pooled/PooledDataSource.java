package com.jayus.smallMyBatis.step10.datasource.pooled;

import com.jayus.smallMyBatis.step10.datasource.unpool.UnpooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 有连接池的数据源
 */
public class PooledDataSource implements DataSource {

    private Logger logger = LoggerFactory.getLogger(PooledDataSource.class);

    private final PoolState state = new PoolState(this);

    private UnpooledDataSource dataSource;

    // 活跃连接数
    protected int poolMaximumActiveConnections = 10;

    // 空闲连接数
    protected int poolMaximumIdleConnections = 5;

    // 在被强制返回之前 池中连接被检查的时间
    protected int poolMaximumCheckoutTime = 20000;

    // 这是给连接池一个打印日志状态机会的低层次设置 还有重新尝试获得连接，这些情况下往往需要很长时间，为了避免连接池设置时静默失败
    protected int poolTimeWait = 20000;

    // 发生到数据的侦测查询，用来验证连接是否正常工作，并且准备接手请求。默认是 "NO PING QUERY SET" 。这会引起许多数据库驱动连接由一个错误信息而导致失败
    protected String poolPingQuery = "NO PING QUERY SET";

    // 开启或禁用侦测查询
    protected boolean poolPingEnabled = false;

    // 用来配置 poolPingQuery 多长时间被用一次
    protected int poolPingConnectionsNotUsedFor = 0;

    private int expectedConnectionTypeCode;

    public PooledDataSource(){
        this.dataSource = new UnpooledDataSource();
    }

    protected void pushConnection(PooledConnection connection) throws SQLException {
        synchronized (state) {
            state.activeConnections.remove(connection);
            // 判断链接是否有效
            if (connection.isValid()){
                // 如果空闲链接小于设定数量 也就是太少时
                if (state.idleConnections.size() < poolMaximumIdleConnections && connection.getConnectionTypeCode() == expectedConnectionTypeCode){
                    state.accumulatedCheckoutTime += connection.getCheckTime();
                    if (!connection.getRealConnection().getAutoCommit()){
                        connection.getRealConnection().rollback();
                    }
                    // 实例化一个新的 DB 连接 加入到 idle 列表
                    PooledConnection newConnection = new PooledConnection(connection.getRealConnection(), this);
                    state.idleConnections.add(newConnection);
                    newConnection.setCreatedTimestamp(connection.getCreatedTimestamp());
                    newConnection.setLastUsedTimestamp(connection.getLastUsedTimestamp());
                    connection.invalidate();
                    logger.info("Returned connection " + newConnection.getRealHashCode() + " to pool.");

                    // 通知其他线程可以来抢 DB 连接了
                    state.notifyAll();
                }
            }
        }
    }


}

