package com.jayus.smallMyBatis.step19.builder;

import com.jayus.smallMyBatis.step19.mapping.BoundSql;
import com.jayus.smallMyBatis.step19.mapping.ParameterMapping;
import com.jayus.smallMyBatis.step19.mapping.SqlSource;
import com.jayus.smallMyBatis.step19.session.Configuration;

import java.util.List;

/**
 * @ClassName StaticSqlSource
 * @Description: 静态 SQL 源码
 * @date: 2024/10/19 19:54
 */
public class StaticSqlSource implements SqlSource {

    private String sql;

    private List<ParameterMapping> parameterMappings;

    private Configuration configuration;

    public StaticSqlSource(Configuration configuration, String sql) {
        this(configuration,sql,null);
    }

    public StaticSqlSource(Configuration configuration,String sql, List<ParameterMapping> parameterMappings ) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.configuration = configuration;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return new BoundSql(configuration,sql,parameterMappings,parameterObject);
    }
}
