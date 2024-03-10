package com.jayus.smallMyBatis.step08.scripting.defaults;

import com.jayus.smallMyBatis.step08.builder.SqlSourceBuilder;
import com.jayus.smallMyBatis.step08.mapping.BoundSql;
import com.jayus.smallMyBatis.step08.mapping.SqlSource;
import com.jayus.smallMyBatis.step08.scripting.xmltags.DynamicContext;
import com.jayus.smallMyBatis.step08.scripting.xmltags.SqlNode;
import com.jayus.smallMyBatis.step08.session.Configuration;

import java.util.HashMap;

/**
 * 原始 SQL 源码 比 DynamicSqlSource 更快
 */
public class RawSqlSource implements SqlSource {

    private final SqlSource sqlSource;

    public RawSqlSource(Configuration configuration, SqlNode rootSqlNode,Class<?> parameterType) {
        this(configuration,getSql(configuration,rootSqlNode),parameterType);
    }

    public RawSqlSource(Configuration configuration,String sql,Class<?> parameterType) {
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> clazz = parameterType == null?Object.class:parameterType;
        sqlSource = sqlSourceParser.parse(sql,clazz,new HashMap<>());
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return sqlSource.getBoundSql(parameterObject);
    }

    private static String getSql(Configuration configuration,SqlNode rootSqlNode){
        DynamicContext context = new DynamicContext(configuration, null);
        rootSqlNode.apply(context);
        return context.getSql();
    }
}
