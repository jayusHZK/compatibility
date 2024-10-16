package com.jayus.smallMyBatis.step13.mapping;

/**
 * @ClassName SqlSource
 * @Description: SQL 源码
 * @date: 2024/10/13 09:17
 */
public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);

}
