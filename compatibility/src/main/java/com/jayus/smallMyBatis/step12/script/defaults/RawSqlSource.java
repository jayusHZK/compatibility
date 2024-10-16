package com.jayus.smallMyBatis.step12.script.defaults;

import com.jayus.smallMyBatis.step12.builder.SqlSourceBuilder;
import com.jayus.smallMyBatis.step12.mapping.BoundSql;
import com.jayus.smallMyBatis.step12.mapping.SqlSource;
import com.jayus.smallMyBatis.step12.script.xmltags.DynamicContext;
import com.jayus.smallMyBatis.step12.script.xmltags.SqlNode;
import com.jayus.smallMyBatis.step12.session.Configuration;

import java.util.HashMap;

/**
 * @ClassName RawSqlSource
 * @Description: 原始sql源码
 * @date: 2024/9/26 00:52
 */
public class RawSqlSource implements SqlSource {

    private final SqlSource sqlSource;

    public RawSqlSource(Configuration configuration, SqlNode rootSqlNode,Class<?> parameterType) {
        this(configuration,getSql(configuration,rootSqlNode),parameterType);
    }

    public RawSqlSource(Configuration configuration,String sql,Class<?> parameterType) {
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> clazz = parameterType == null ? Object.class : parameterType;
        sqlSource = sqlSourceParser.parse(sql,clazz,new HashMap<>());
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return sqlSource.getBoundSql(parameterObject);
    }

    private static String getSql(Configuration configuration, SqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(configuration, null);
        rootSqlNode.apply(context);
        return context.getSql();
    }



}
