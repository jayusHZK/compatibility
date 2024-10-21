package com.jayus.smallMyBatis.step19.scripting.xmltags;

import com.jayus.smallMyBatis.step19.builder.SqlSourceBuilder;
import com.jayus.smallMyBatis.step19.mapping.BoundSql;
import com.jayus.smallMyBatis.step19.mapping.SqlSource;
import com.jayus.smallMyBatis.step19.session.Configuration;

import java.util.Map;

/**
 * @ClassName DynamicSqlSource
 * @Description: 动态 SQL 源码
 * @date: 2024/10/19 18:48
 */
public class DynamicSqlSource implements SqlSource {

    private Configuration configuration;

    private SqlNode rootSqlNode;

    public DynamicSqlSource(Configuration configuration, SqlNode rootSqlNode) {
        this.configuration = configuration;
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        DynamicContext context = new DynamicContext(configuration, parameterObject);
        rootSqlNode.apply(context);
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> parameterType = parameterObject == null ? Object.class:parameterObject.getClass();
        SqlSource sqlSource = sqlSourceParser.parse(context.getSql(), parameterType, context.getBindings());
        BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
        for (Map.Entry<String, Object> entry : context.getBindings().entrySet()) {
            boundSql.setAdditionalParameters(entry.getKey(),entry.getValue());
        }
        return boundSql;
    }
}
