package com.jayus.smallMyBatis.step12.datasource.pooled;

import com.jayus.smallMyBatis.step12.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * @ClassName PooledDataSourceFactory
 * @Description: 有连接池的数据源工厂
 * @date: 2024/9/25 07:43
 */
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }

}
