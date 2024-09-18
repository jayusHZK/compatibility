package com.jayus.smallMyBatis.step11.scripting.defaults;

import com.jayus.smallMyBatis.step11.build.SqlSourceBuilder;
import com.jayus.smallMyBatis.step11.mapping.BoundSql;
import com.jayus.smallMyBatis.step11.mapping.SqlSource;
import com.jayus.smallMyBatis.step11.scripting.xmltags.DynamicContext;
import com.jayus.smallMyBatis.step11.scripting.xmltags.SqlNode;
import com.jayus.smallMyBatis.step11.session.Configuration;

import java.util.HashMap;

/**
 * @ClassName RawSQLSource
 * @Description: 原始 SQL 源码，比 DunamicSQLSource 动态 SQL 处理快
 * @date: 2024/9/18 00:32
 */
public class RawSQLSource implements SqlSource {

    private final SqlSource sqlSource;

    public RawSQLSource(Configuration configuration,SqlNode rootSqlNode,Class<?> parameterType) {
        this(configuration,getSql(configuration,rootSqlNode),parameterType);
    }

    public RawSQLSource(Configuration configuration,String sql,Class<?> parameterType) {
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
