package com.jayus.smallMyBatis.step19.session;

import java.util.List;

/**
 * @ClassName SqlSession
 * @Description: SqlSession 用来执行 SQL，获取映射器，管理事务
 * @date: 2024/10/18 11:17
 */
public interface SqlSession {

    /*
    根据指定的SqlID获取一条记录的封装对象
     */
    <T> T selectOne(String statement);

    /*
     根据指定的SqlID获取一条记录的封装对象，只不过这个方法容许我们可以给sql传递一些参数
     * 一般在实际使用中，这个参数传递的是pojo，或者Map或者ImmutableMap
     */
    <T> T selectOne(String statement,Object parameter);

    /*
    获取多条记录，这个方法容许我们可以传递一些参数
     */
    <E>List<E> selectList(String statement,Object parameter);

    /*
    插入记录，容许传入参数。
     */
    int insert(String statement,Object parameter);

    /*
    更新记录
     */
    int update(String statement,Object parameter);

    /*
    删除记录
     */
    Object delete(String statement,Object parameter);

    void commit();

    void close();

    void clearCache();

    Configuration getConfiguration();

    <T> T getMapper(Class<T> type);

}
