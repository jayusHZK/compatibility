package com.jayus.smallMyBatis.step09.builder;

import com.jayus.smallMyBatis.step09.mapping.BoundSql;
import com.jayus.smallMyBatis.step09.mapping.ParameterMapping;
import com.jayus.smallMyBatis.step09.mapping.SqlSource;
import com.jayus.smallMyBatis.step09.session.Configuration;

import java.util.List;

/**
 * 静态 SQL 源码
 */
public class StaticSqlSource implements SqlSource {

    private String sql;

    private List<ParameterMapping> parameterMappings;

    private Configuration configuration;

    public StaticSqlSource(String sql, Configuration configuration) {
        this(configuration,sql,null);
    }

    public StaticSqlSource( Configuration configuration,String sql,List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.configuration = configuration;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return new BoundSql(configuration,sql,parameterMappings,parameterObject);
    }
}
