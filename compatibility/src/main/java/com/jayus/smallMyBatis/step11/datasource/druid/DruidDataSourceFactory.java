package com.jayus.smallMyBatis.step11.datasource.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.jayus.smallMyBatis.step11.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Druid 数据源工厂
 */
public class DruidDataSourceFactory implements DataSourceFactory {

    private Properties props;

    @Override
    public void setProperties(Properties props) {
        this.props = props;
    }

    @Override
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(props.getProperty("driver"));
        dataSource.setUsername(props.getProperty("name"));
        dataSource.setPassword(props.getProperty("password"));
        dataSource.setUrl(props.getProperty("url"));
        return dataSource;
    }
}
