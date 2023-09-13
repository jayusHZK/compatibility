package com.jayus.smallMyBatis.step03.session;

public interface SqlSession {

    <T> T selectOne(String statement);

    <T> T selectOne(String statement,Object parameter);

    Configuration getConfiguration();

    <T> T getMapper(Class<T> type);

}
