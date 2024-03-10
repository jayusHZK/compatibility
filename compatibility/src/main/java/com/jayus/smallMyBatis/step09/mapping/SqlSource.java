package com.jayus.smallMyBatis.step09.mapping;

/**
 * SQL 源码
 */
public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);

}
