package com.jayus.smallMyBatis.step19.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @ClassName DataSourceFactory
 * @Description: 数据源工厂
 * @date: 2024/10/17 23:30
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();

}
