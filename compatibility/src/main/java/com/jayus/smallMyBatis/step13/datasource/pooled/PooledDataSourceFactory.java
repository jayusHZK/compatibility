package com.jayus.smallMyBatis.step13.datasource.pooled;

import com.jayus.smallMyBatis.step13.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * @ClassName PooledDataSourceFactory
 * @Description: 有连接池的数据源工厂
 * @date: 2024/10/12 15:52
 */
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }
}
