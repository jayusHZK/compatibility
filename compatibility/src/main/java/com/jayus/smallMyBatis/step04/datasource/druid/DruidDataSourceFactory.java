package com.jayus.smallMyBatis.step04.datasource.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.jayus.smallMyBatis.step04.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class DruidDataSourceFactory implements DataSourceFactory {

    private Properties properties;

    @Override
    public void setProperties(Properties props) {
        this.properties = props;
    }

    @Override
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getProperty("driver"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        return dataSource;
    }
}
