package com.jayus.smallMyBatis.step19.mapping;

/**
 * @ClassName SqlSource
 * @Description: SQL 源码
 * @date: 2024/10/18 11:31
 */
public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);

}
