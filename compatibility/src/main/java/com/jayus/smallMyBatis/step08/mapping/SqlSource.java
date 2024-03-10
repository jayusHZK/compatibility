package com.jayus.smallMyBatis.step08.mapping;

/**
 * SQL源码
 */
public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);

}
