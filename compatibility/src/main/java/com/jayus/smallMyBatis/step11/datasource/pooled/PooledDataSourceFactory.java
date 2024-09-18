package com.jayus.smallMyBatis.step11.datasource.pooled;

import com.jayus.smallMyBatis.step11.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * @ClassName PooledDataSourceFactory
 * @Description: 有连接池的数据源工厂
 * @date: 2024/9/17 22:34
 */
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }
}
