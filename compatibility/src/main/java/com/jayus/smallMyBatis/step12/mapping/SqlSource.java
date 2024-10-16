package com.jayus.smallMyBatis.step12.mapping;

/**
 * @ClassName SqlSource
 * @Description: SQL 源码
 * @date: 2024/9/25 09:07
 */
public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);

}
