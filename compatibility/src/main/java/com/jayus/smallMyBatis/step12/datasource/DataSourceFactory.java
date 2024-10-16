package com.jayus.smallMyBatis.step12.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @ClassName DataSourceFactory
 * @Description: 数据源工厂
 * @date: 2024/9/20 08:33
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();

}
