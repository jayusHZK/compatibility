package com.jayus.smallMyBatis.step10.datasource.pooled;

import com.jayus.smallMyBatis.step10.datasource.unpool.UnpooledDataSourceFactory;

/**
 * 有连接池的数据源
 */
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }
}
