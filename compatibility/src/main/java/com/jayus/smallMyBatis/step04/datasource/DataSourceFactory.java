package com.jayus.smallMyBatis.step04.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据源工厂
 */
public interface DataSourceFactory {

    // 设置属性
    void setProperties(Properties props);

    // 获取数据源
    DataSource getDataSource();

}
