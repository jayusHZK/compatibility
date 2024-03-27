package com.jayus.smallMyBatis.step10.mapping;

/**
 * sql 源码
 */
public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);

}
