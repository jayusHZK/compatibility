package com.jayus.smallMyBatis.step10.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据源工厂
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();

}
