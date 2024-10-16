package com.jayus.smallMyBatis.step13.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @ClassName DataSourceFactory
 * @Description: 数据源工厂
 * @date: 2024/10/10 18:42
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();

}
