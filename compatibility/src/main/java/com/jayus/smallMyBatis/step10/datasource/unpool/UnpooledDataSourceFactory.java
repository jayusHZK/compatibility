package com.jayus.smallMyBatis.step10.datasource.unpool;

import com.jayus.smallMyBatis.step10.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 无池化数据源工厂
 */
public class UnpooledDataSourceFactory implements DataSourceFactory {

    protected DataSource dataSource;

    public UnpooledDataSourceFactory() {
        this.dataSource = new UnpooledDataSource();
    }

    @Override
    public void setProperties(Properties props) {
        system
    }

    @Override
    public DataSource getDataSource() {
        return null;
    }
}
