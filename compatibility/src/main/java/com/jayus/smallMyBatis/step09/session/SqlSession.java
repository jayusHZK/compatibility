package com.jayus.smallMyBatis.step09.session;

/**
 * 用来执行 SQL 获取映射器 管理事务
 * 通常情况下 我们在应用程序中使用的 MyBatis 的 API 就是这个接口定义的方法
 */
public interface SqlSession {

    <T> T selectOne(String statement);

    <T> T selectOne(String statement,Object parameter);

    Configuration getConfiguration();

    <T> T getMapper(Class<T> type);

}
