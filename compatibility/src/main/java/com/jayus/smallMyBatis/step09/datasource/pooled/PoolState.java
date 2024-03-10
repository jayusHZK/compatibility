package com.jayus.smallMyBatis.step09.datasource.pooled;

import java.util.ArrayList;
import java.util.List;

/**
 * 池状态
 */
public class PoolState {

    protected PooledDataSource dataSource;

    // 空闲连接
    protected final List<PooledConnection> idleConnections = new ArrayList<>();

    // 活跃连接
    protected final List<PooledConnection> activeConnections = new ArrayList<>();

    // 请求总数
    protected long requestCount = 0;

    // 总请求时间
    protected long accumulatedRequestTime = 0;

    // 总检出时间
    protected long accumulatedCheckoutTime = 0;

    // 总检入时间
    protected long claimedOverdueConnectionCount = 0;

    // 总检出连接数
    protected long accumulatedCheckoutTimeOfOverdueConnections = 0;

    // 总等待时间
    protected long accumulatedWaitTime = 0;

    // 要等待的次数
    protected long hadToWaitCount = 0;

    // 失败连接次数
    protected long badConnectionCount = 0;

    public PoolState(PooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取请求次数
     * @return
     */
    public synchronized long getRequestCount(){
        return requestCount;
    }

    /**
     * 获取平均请求次数
     * @return
     */
    public synchronized long getAverageRequestTime(){
        return requestCount == 0? 0:accumulatedRequestTime / requestCount;
    }

    /**
     * 获取平均等待时间
     * @return
     */
    public synchronized long getAverageWaitTime(){
        return hadToWaitCount == 0? 0:accumulatedWaitTime / hadToWaitCount;
    }

    public synchronized long getClaimedOverdueConnectionCount() {
        return claimedOverdueConnectionCount;
    }

    public synchronized long getHadToWaitCount() {
        return hadToWaitCount;
    }

    public synchronized long getBadConnectionCount() {
        return badConnectionCount;
    }

    /**
     * 获取平均检入时间
     * @return
     */
    public synchronized  long getAverageOverdueCheckoutTime(){
        return claimedOverdueConnectionCount == 0?0:accumulatedCheckoutTimeOfOverdueConnections / claimedOverdueConnectionCount;
    }

    /**
     * 获取空闲连接数
     * @return
     */
    public synchronized int getIdleConnectionCount(){
        return idleConnections.size();
    }

    /**
     * 获取活跃连接数量
     * @return
     */
    public synchronized int getActiveConnectionCount(){
        return activeConnections.size();
    }
}
