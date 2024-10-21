package com.jayus.smallMyBatis.step19.datasource.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.jayus.smallMyBatis.step19.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @ClassName DruidDataSourceFactory
 * @Description: Druid 数据源工厂
 * @date: 2024/10/17 23:31
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
        dataSource.setUrl(props.getProperty("url"));
        dataSource.setUsername(props.getProperty("username"));
        dataSource.setPassword(props.getProperty("password"));
        return dataSource;
    }

}
