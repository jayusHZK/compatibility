package com.jayus.smallMyBatis.step11.mapping;

/**
 * @ClassName SqlSource
 * @Description: SQL 源码
 * @date: 2024/5/13 16:38
 */
public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);

}
